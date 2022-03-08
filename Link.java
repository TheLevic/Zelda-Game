import java.awt.image.BufferedImage;
import java.awt.Graphics;
/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 * 
 * 
 */
public class Link {

    //Variables
    static int x;
    static int y;
    static int prevX;
    static int prevY;
    final static int w = 55;
    final static int h = 70;
    static double speed = 8;
    static int AnimationNum = 0;
    static BufferedImage[] linkImages;

    Link(){
        x = 100;
        y = 100;
        prevX = x;
        prevY = y;
        loadImage();
    }

    public void update(){
        getOutOfBrick();
    }

        void getOutOfBrick(){
        for (int i = 0; i < Model.bricks.size(); i++){
            if (x + w >= Model.bricks.get(i).x && x <= Model.bricks.get(i).x + Model.bricks.get(i).w){
                x = prevX;
            }
            //If his feet are in a brick, or his head is in a brick, get him out
            if (y + h >= Model.bricks.get(i).y && y <= Model.bricks.get(i).y + Model.bricks.get(i).h){
                y = prevY;
            }
        }
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

    

    void draw(Graphics g){
        g.drawImage(linkImages[AnimationNum], x, y, null);
    }

    @Override 
    public String toString()
    {
        return "Link (x,y) = (" + x + ", " + y + ")";
    }
}
