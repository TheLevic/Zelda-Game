import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Boomerang extends Sprite {
    static BufferedImage[] images;

    public Boomerang(){
        
    }

    //Overridden methods
    @Override
    void draw(Graphics g){

    }

    @Override
    void update(){

    }
    
    @Override
    Json Marshal(){
        Json ob = Json.newObject();
        return ob;
    }
}
