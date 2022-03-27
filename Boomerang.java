import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Boomerang extends Sprite {
    //Member variables
    int speed = 5;
    int AnimationNum = 0;
    static BufferedImage[] images;

    public Boomerang(){
        loadImage();
        w = 8;
        h = 12;
    }

    //Overridden methods
    @Override
    void draw(Graphics g){
        g.drawImage(images[AnimationNum], x - View.scrollPositonX, y - View.scrollPositonY, null);
    }

    //Uses lazy loading to load the pictures
    @Override
    void loadImage(){
        if (images == null){
            images = new BufferedImage[4];
            for (int i = 1; i < 5; i++){
                String tmp = "images/boomerang" + Integer.toString(i) + ".png";
                images[i - 1] = View.loadImage(tmp);
            }
        }
    }

    //Using this method to make the boomerange move in controller
    @Override
    public void update(){
        
    }
    
    @Override
    Json Marshal(){
        Json ob = Json.newObject();
        return ob;
    }
    @Override
    boolean isBoomerang(){return true;}
}
