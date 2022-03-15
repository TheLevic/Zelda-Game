import java.awt.Graphics;

public abstract class Sprite{
    int x, y, w, h;

    public Sprite(){
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }


    abstract void draw(Graphics g);
    abstract void update();
    abstract Json Marshal();

    boolean isBrick(){return false;}
    boolean isLink(){return false;}
}