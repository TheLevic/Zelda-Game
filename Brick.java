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

public class Brick extends Sprite{
	//Location of the brick
	static BufferedImage image;
	
	
	//Constructors
	public Brick(int locationx, int locationy) {
		this.x = locationx;
		this.y = locationy;
		loadImage();
		w = 50;
		h = 50;
	}

	//Marshalling Methods
	Brick(Json ob){
		w = 50;
		h = 50;
		x = (int)ob.getLong("brickx");
		y = (int)ob.getLong("bricky");
		loadImage();
	}

	
	Json Marshal(){
		Json ob = Json.newObject();
		ob.add("brickx", x);
		ob.add("bricky", y);
		return ob;
	}

	@Override 
	public String toString()
	{
		return "Brick (x,y) = (" + x + ", " + y + ")";
	}

	@Override
	void draw(Graphics g){
		g.drawImage(image, x - View.scrollPositonX, y - View.scrollPositonY, null);
	}
	@Override
    boolean update(){
		return true;
    }
	@Override
	boolean isBrick(){
		return true;
	}

	@Override
	void loadImage(){
		if (image == null){
			image = View.loadImage("brick.jpg");
		}
	}
	@Override
	void Collided(){
		
	}

	
}
