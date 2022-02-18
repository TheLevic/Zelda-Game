/*
 * Levi Crider
 * 2/9/22
 * Map editor
 * Create a game that allows the turtle to move across the screen with arrow keys or mouse clicks
 * 
 * 
 */
import java.util.ArrayList;


public class Model {
	//Member variables
	ArrayList<Brick> bricks = new ArrayList<Brick>();

	//Constructor
	Model()
	{
		
	}

	public void update()
	{
		
	}

	
	
	//Adds brick to the screen on mouseclick
	public void addBrickToScreen(int X, int Y) {
		int x = X - X % 50;
		int y = Y - Y % 50;
		Brick n = new Brick(x,y);
		//Brick detection/deletion
		if (!detectBrick(x,y)) {
			bricks.add(n);
		}
		else {
			n = null;
			System.out.println("There is already a brick there!");
		}
		
		
	}
	
	//Makes sure we aren't placing two bricks in the same location
	public boolean detectBrick(int locationx, int locationy) { //Location x and y are the snap to grid locations of the brick we are trying to place
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
	

	//Marshalling methods
	Json Marshal(){
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
        ob.add("brick", tmpList);
        for(int i = 0; i < bricks.size(); i++)
            tmpList.add(bricks.get(i).Marshal());
        return ob;
	}

	void Unmarshal(Json ob){
		bricks = new ArrayList<Brick>(); //Making a new list for our bricks
		Json tmplist = ob.get("brick"); //Getting bricks from our file
		for(int i = 0; i < tmplist.size(); i++)
            bricks.add(new Brick(tmplist.get(i)));
    }
	
	
}
