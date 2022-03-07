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
    final int w = 55;
    final int h = 70;
    static double speed = 8;
    static int AnimationNum = 0;
    static BufferedImage[] linkImages;

    Link(){
        x = 100;
        y = 100;
        loadImage();
    }

    public static void update(){
        
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

    

    static void draw(Graphics g){
        g.drawImage(linkImages[AnimationNum], x, y, null);
    }

    @Override 
    public String toString()
    {
        return "Link (x,y) = (" + x + ", " + y + ")";
    }
}
