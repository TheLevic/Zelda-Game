import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Boomerang extends Sprite {
    //Member variables
    int speed = 5;
    int animationNum = 0;
    static BufferedImage[] images;
    int maxImageNum = 4; //How many images are in the array above.
    int xdirection;
    int ydirection;

    public Boomerang(){
        loadImage();
        w = 8;
        h = 12;
        isActive = true;
    }

    void cycleImages(){
        if (animationNum == maxImageNum - 1){
            animationNum = 0;
        }
        animationNum++;
    } 

    
    @Override
    void Collided(){
        isActive = false;
    }

    //Overridden methods
    @Override
    void draw(Graphics g){
        g.drawImage(images[animationNum], x - View.scrollPositonX, y - View.scrollPositonY, null);
    }

    //Uses lazy loading to load the pictures
    @Override
    void loadImage(){
        if (images == null){
            images = new BufferedImage[maxImageNum];
            for (int i = 0; i < maxImageNum; i++){
                int tmpNum = i + 1; //To load the images
                String tmp = "images/boomerang" + tmpNum + ".png";
                images[i] = View.loadImage(tmp);
            }
        }
    }
    

    //Using this method to make the boomerange move in controller
    @Override
    public boolean update(){
        //Moving the boomerang
        x += speed * xdirection;
        y += speed * ydirection;
        cycleImages();
        return isActive;
    }
    
    @Override
    Json Marshal(){
        Json ob = Json.newObject();
        return ob;
    }
    @Override
    boolean isBoomerang(){return true;}

    @Override 
    public String toString()
    {
        return "Boomerang (x,y) = (" + x + ", " + y + ")";
    }
}
