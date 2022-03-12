import java.awt.Graphics;

public abstract class Sprite{
    int x, y, w, h;


    abstract void draw(Graphics g);
    abstract void update();
    abstract Json Marshal();
}