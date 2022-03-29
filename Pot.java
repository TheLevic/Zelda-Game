import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pot extends Sprite {

    //Member variables
    static BufferedImage images[];
    int maxImageNum = 2;
    int animationNum;
    int speed = 5;
    boolean inOnePiece;

    Pot(int locationX, int locationY){
        this.x = locationX;
        this.y = locationY;
        isActive = true;
        loadImage();
        w = 38;
        h = 38;
        inOnePiece = true;
    }

    void cycleImages(){
        if(inOnePiece){
            animationNum = maxImageNum - maxImageNum;
        }
        else{
            animationNum = maxImageNum - 1;
        }
    }

    //Checking collision with pots
    @Override
    void Collided(){
        inOnePiece = false;
    }

    @Override
    void draw(Graphics g){
        g.drawImage(images[animationNum], x - View.scrollPositonX, y - View.scrollPositonY, null);
    }

    //Going to use lazy loading
    @Override
    void loadImage(){
        if (images == null){
            images = new BufferedImage[maxImageNum];
            for (int i = 0; i < maxImageNum; i++){
                String tmp = "images/pot" + (i + 1) + ".png";
                images[i] = View.loadImage(tmp);
            }
        }
    }

    @Override
    boolean update(){
        cycleImages();
        return isActive;
    }

    @Override
    Json Marshal(){
        Json ob = Json.newObject();
        ob.add("potx", x);
		ob.add("poty", y);
        return ob;
    }

    Pot(Json ob){
		w = 38;
		h = 38;
		x = (int)ob.getLong("potx");
		y = (int)ob.getLong("poty");
		loadImage();
        inOnePiece = true;
	}

    @Override
    boolean isPot(){return true;}

    @Override 
    public String toString()
    {
        return "Pot (x,y) = (" + x + ", " + y + ")";
    }
}
