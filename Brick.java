import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.awt.Graphics;

/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 *
 * 
 * 
 */

public class Brick {

	//Variables
	
	//Location of the brick
	int x, y, w = 50, h = 50;
	static BufferedImage image;
	
	
	//Constructors
	public Brick(int locationx, int locationy) {
		this.x = locationx;
		this.y = locationy;
		image = View.loadImage("brick.jpg");
	}



	//Adds brick to the screen on mouseclick
	public static void addBrickToScreen(int X, int Y) {
		int x = X - X % 50;
		int y = Y - Y % 50;
		Brick n = new Brick(x,y);
		//Brick detection/deletion
		if (!detectBrick(x,y)) {
			Model.bricks.add(n);
		}
		else {
			n = null;
			System.out.println("There is already a brick there!");
		}
	}




	//Makes sure we aren't placing two bricks in the same location (USING ITERATOR HERE)
	public static boolean detectBrick(int locationx, int locationy) { //Location x and y are the snap to grid locations of the brick we are trying to place
		if(Model.bricks.size() == 0) {
			return false;
		}
		Iterator<Brick> it = Model.bricks.iterator();
		while (it.hasNext()){
			Brick testing = it.next(); //Testing our new brick vs the existing bricks
			if ((locationx >= testing.x && locationx < testing.x + testing.w && locationy >= testing.y && locationy < testing.y + testing.h)) {
				it.remove();
				return true;
			}
		}
		return false;
	}



	Brick(Json ob){
		x = (int)ob.getLong("brickx");
		y = (int)ob.getLong("bricky");
		image = View.loadImage("brick.jpg");
	}

	//Marshalling Methods
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

	void draw(Graphics g){
		g.drawImage(image, x - View.scrollPositonX, y - View.scrollPositonY, null);
	}
	
}
