import java.awt.image.BufferedImage;
import java.awt.Graphics;
/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
* 0-4 is runnning away, 5-9 is running forwards, 10-14 is running left, 15-19 is running right
 * 
 * 
 */
public class Link {
    static int x = 100;
    static int y = 100;
    final int w = 55;
    final int h = 70;
    static double speed = 4;
    final int maxAnimationNum = 23;
    static int AnimationNum = 0;
    //Make sure that our array of sprites is populated

    Link(){
        x = 100;
        y = 100;
        
    }

    public static void update(){
        
    }

    

    // static void draw(Graphics g){
    //     g.drawImage(link_images[AnimationNum], x, y, null);
    // }

    @Override 
    public String toString()
    {
        return "Link (x,y) = (" + x + ", " + y + ")";
    }
}
