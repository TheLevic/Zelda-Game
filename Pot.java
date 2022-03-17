import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pot extends Sprite {

    //Member variables
    int speed = 5;
    static BufferedImage image;

    Pot(){
        this.x = 50;
        this.y = 50;

    }
    
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
