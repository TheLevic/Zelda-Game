import java.awt.image.BufferedImage;
import java.awt.Graphics;
/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 * 
 * 
 */
public class Link extends Sprite{

    //Variables
    int prevX;
    int prevY;
    double speed = 8;
    int AnimationNum = 0;
    static BufferedImage[] linkImages;

    Link(){
        x = 100;
        y = 100;
        w = 55;
        h = 70;
        prevX = x;
        prevY = y;
        loadImage();
    }

    @Override
    public void update(){
    }

    void getOutOfBrick(Brick b){
        //If he's going right into a brick
        if (x + w >= b.x && prevX + w <= b.x){
            x = prevX;
        }
        //If he's going left into a brick
        if (x <= b.x + b.w && prevX >= b.x + b.w){
            x = prevX;
        }

        //If his head is in a brick
        if(y <= b.y + b.h && prevY >= b.y + b.h){
            y = prevY;
        }

        //If his feet are in a brick
        if (y + h >= b.y && prevY + h <= b.y){
            y = prevY;
        }
    }

    void savePrev(){
        prevX = x;
        prevY = y;
    }

    private void loadImage(){
        if (linkImages == null){
            linkImages = new BufferedImage[25];
            for (int i = 1; i < 25; i++){
                String tmp = Integer.toString(i) + ".png";
                linkImages[i - 1] = View.loadImage("linkPictures/" + tmp);
                System.out.println("Loading image" + i);
            }
        }
    }

    
    @Override
    void draw(Graphics g){
        g.drawImage(linkImages[AnimationNum], x - View.scrollPositonX, y - View.scrollPositonY, null);
    }

    @Override 
    public String toString()
    {
        return "Link (x,y) = (" + x + ", " + y + ")";
    }
}
