import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pot extends Sprite {

    //Member variables
    static BufferedImage images[];
    int maxImageNum = 2;
    int animationNum;
    int speed = 5;
    boolean inOnePiece;
    int xdirection, ydirection;
    boolean moveUp;
    boolean moveRight;
    boolean moveDown;
    boolean moveLeft;
    int countdown = 15;

    Pot(int locationX, int locationY){
        this.x = locationX;
        this.y = locationY;
        isActive = true;
        loadImage();
        w = 35;
        h = 35;
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

    //Pot movement
    void movePotUp(){
            xdirection = 0;
            ydirection = -1;
            this.y += speed * ydirection;
    }
    void movePotRight(){
        
        xdirection = 1;
        ydirection = 0;
        this.x += speed * xdirection;
        
    }
    void movePotDown(){
        xdirection = 0;
        ydirection = 1;
        this.y += speed * ydirection;
    }
    void movePotLeft(){
        xdirection = -1;
        ydirection = 0;
        this.x += speed * xdirection;
    }



    //Checking collision with pots
    @Override
    void Collided(){
        inOnePiece = false;
        speed = 0;
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
        //Pot movement with link
        if (moveUp){
            movePotUp();
        }
        if (moveDown){
            movePotDown();
        }
        if (moveLeft){
            movePotLeft();
        }
        if (moveRight){
            movePotRight();
        }
        cycleImages();
        if (!inOnePiece){
            countdown--;
            if (countdown == 0){
                isActive = false;
            }
        }
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
		w = 35;
		h = 35;
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
