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
        this.x = 100;
        this.y = 100;
        w = 55;
        h = 70;
        prevX = x;
        prevY = y;
        loadImage();
    }

    @Override
    public void update(){
        for (int i = 0; i < Model.sprites.size(); i++){
            if (!Model.sprites.get(i).isLink()){
                boolean collision = Model.isThereACollision(this, Model.sprites.get(i));
                if (collision){
                    this.getOutOfBrick(Model.sprites.get(i));
                }
            }
		}
    }

    void getOutOfBrick(Sprite b){
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

    @Override
    void loadImage(){
        if (linkImages == null){
            linkImages = new BufferedImage[25];
            for (int i = 1; i < 25; i++){
                String tmp = Integer.toString(i) + ".png";
                linkImages[i - 1] = View.loadImage("linkPictures/" + tmp);
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
    @Override 
    Json Marshal(){
        Json ob = Json.newObject();
        return ob;
    }
    @Override
    boolean isLink(){
        return true;
    }

}
