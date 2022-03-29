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
    
    public static void addPotToScreen(int X, int Y) {
		int x = X - X % 50;
		int y = Y - Y % 50;
		Pot p = new Pot(x, y);
		Model.sprites.add(p);
		
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
    void collided(){
        for (int i = 0; i < Model.sprites.size(); i++){
            if (Model.sprites.get(i).isLink()){
                boolean colliding = Model.isThereACollision(this, Model.sprites.get(i));
                if (colliding){
                    //Not sure what to do here to get links direction to move the pot properly
                }
            }
            else if (!Model.sprites.get(i).isLink() && !Model.sprites.get(i).isPot()){
                boolean colliding = Model.isThereACollision(this, Model.sprites.get(i));
                if (colliding){
                    inOnePiece = false;
                }
            }
        }
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
        collided();
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
