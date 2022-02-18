
public class Brick {
	Model model;
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
	
	public boolean detectBrick(int locationx, int locationy) {
		for (int i = 0; i < model.bricks.size(); i++) {
			Brick testing = model.bricks.get(i);
			if ((locationx >= testing.x && locationx <= testing.x + testing.w) && (locationy >= testing.y && locationy <= testing.y + testing.h)) {
				return true;
			}
		}
		return false;
	}
}
