import java.awt.Graphics;

public abstract class Sprite{
    int x, y, w, h;
    boolean isActive;

    public Sprite(){
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }


    abstract void draw(Graphics g);
    abstract boolean update();
    abstract Json Marshal();
    abstract void loadImage();
    @Override 
    public String toString()
    {
        return "Sprites (x,y) = (" + x + ", " + y + ")";
    }

    boolean isBrick(){return false;}
    boolean isLink(){return false;}
    boolean isBoomerang(){return false;}
    boolean isPot(){return false;}
}