import java.util.ArrayList;

/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 *
 * 
 * 
 */

public class Brick {
	public static ArrayList<Brick> bricks = new ArrayList<Brick>();
	//Location of the brick
	int x;
	int y;
	//Width and height of the brick
	int w = 50;
	int h = 50;
	
	
	//Constructors
	public Brick() {
		x = 50;
		y = 50;
	}
	public Brick(int locationx, int locationy) {
		x = locationx;
		y = locationy;
	}



	//Adds brick to the screen on mouseclick
	public static void addBrickToScreen(int X, int Y) {
		int x = X - X % 50;
		int y = Y - Y % 50;
		Brick n = new Brick(x,y);
		//Brick detection/deletion
		if (!detectBrick(x,y)) {
			Brick.bricks.add(n);
		}
		else {
			n = null;
			System.out.println("There is already a brick there!");
		}
	}




	//Makes sure we aren't placing two bricks in the same location
	public static boolean detectBrick(int locationx, int locationy) { //Location x and y are the snap to grid locations of the brick we are trying to place
		if(bricks.size() == 0) {
			return false;
		}
		for (int i = 0; i < bricks.size(); i++) {
			Brick testing = bricks.get(i); //Testing our new brick vs the existing bricks
			if ((locationx >= testing.x && locationx < testing.x + testing.w && locationy >= testing.y && locationy < testing.y + testing.h)) {
				bricks.remove(i);
				return true;
			}
		}
		return false;
	}



	Brick(Json ob){
		x = (int)ob.getLong("brickx");
		y = (int)ob.getLong("bricky");
	}

	//Marshalling Methods
	Json Marshal(){
		Json ob = Json.newObject();
		ob.add("brickx", x);
		ob.add("bricky", y);
		return ob;
	}
	
}
