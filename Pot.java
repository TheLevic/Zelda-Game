import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pot extends Sprite {

    //Member variables
    static BufferedImage image;

    Pot(int locationX, int locationY){
        this.x = locationX;
        this.y = locationY;
    }
    
    @Override
    void draw(Graphics g){

    }

    @Override
    void loadImage(){

    }

    @Override
    boolean update(){

        return true;
    }

    @Override
    Json Marshal(){
        Json ob = Json.newObject();
        return ob;
    }

    @Override
    boolean isPot(){return true;}
}
