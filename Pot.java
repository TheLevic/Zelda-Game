import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pot extends Sprite {

    //Member variables
    static BufferedImage images[];
    int maxImageNum = 2;
    int animationNum;

    Pot(int locationX, int locationY){
        this.x = locationX;
        this.y = locationY;
        isActive = true;
        loadImage();
        w = 38;
        h = 38;
    }
    
    public static void addPotToScreen(int X, int Y) {
		int x = X - X % 50;
		int y = Y - Y % 50;
		Pot p = new Pot(x, y);
		//Brick detection/deletion
		Model.sprites.add(p);
		
	}

    @Override
    void draw(Graphics g){
        g.drawImage(images[animationNum], x - View.scrollPositonX, y - View.scrollPositonY, null);
    }

    //Going to use lazy loading
    @Override
    void loadImage(){
        if (images == null){
            images = new BufferedImage[maxImageNum];
            for (int i = 0; i < maxImageNum; i++){
                String tmp = "images/pot" + (i + 1) + ".png";
                images[i] = View.loadImage(tmp);
            }
        }
    }

    @Override
    boolean update(){

        
        return isActive;
    }

    @Override
    Json Marshal(){
        Json ob = Json.newObject();
        return ob;
    }

    @Override
    boolean isPot(){return true;}
}
