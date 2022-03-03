import java.awt.image.BufferedImage;
import java.awt.Graphics;
/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 * 
 * 
 * 
 */
public class Link {
    static int x = 100;
    static int y = 100;
    final int w = 55;
    final int h = 70;
    static double speed = 4;
    static int AnimationNum = 0;
    static BufferedImage[] link_images = new BufferedImage[24];
    static boolean filled = false;

    Link(){
        x = 100;
        y = 100;
        //Loading the link image
        for (int i = 0; i < 24; i++){
            String tmp = Integer.toString(i + 1) + ".png";
            link_images[i] = View.loadImage("Link_pictures/" + tmp);
        }
    }

    public static void update(){
        
    }

    public static void fillImageArray(){
        for (int i = 0; i < 24; i++){
            String tmp = Integer.toString(i + 1) + ".png";
            link_images[i] = View.loadImage("linkPictures/" + tmp);
            System.out.println("Loading image" + i);
        }
        filled = true;
    }

    static void draw(Graphics g){
        if(!filled){
            fillImageArray();
            
        }
        g.drawImage(link_images[AnimationNum], x, y, null);
    }
}
