/* 
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 */
import java.util.ArrayList;
import java.util.Iterator;



public class Model {
	//Member variables
	Link link;
	ArrayList<Sprite> sprites;

	//Constructor
	Model(){
		link = new Link();
		sprites = new ArrayList<Sprite>();
		sprites.add(link);
	}


	/*
	* Our update funciton is going to take care of any updates that are needed within our world or our sprites
	*/
	public void update(){
		for (int i = 0; i < sprites.size(); i++){
			//Collison
			for (int j = 0; j < sprites.size(); j++){
				if ( i != j){
					boolean collide = isThereACollision(sprites.get(i), sprites.get(j));
					if (collide){
						//Collision detection for Link
						if (sprites.get(i).isLink() && !sprites.get(j).isPot()){
							link.getOutOfSprite(sprites.get(j));
						}
						//Collision for boomerang hitting anything (besides link)
						else if (sprites.get(i).isBoomerang() && !sprites.get(j).isLink()){
							sprites.get(i).Collided();
							
						}
						//Collision for pots with anything (besides link)
						else if (sprites.get(i).isPot() && !sprites.get(j).isLink()){
							Pot n = (Pot)sprites.get(i);
							n.Collided();
						}
						//Collision for Link hitting pot
						else if (sprites.get(i).isLink() && sprites.get(j).isPot()){
							if (link.getDirection() == 1){
								Pot n = (Pot)sprites.get(j);
								n.moveUp = true;
								n.moveLeft = false;
								n.moveDown = false;
								n.moveRight = false;
							}
							else if (link.getDirection() == 2){
								//Need to move the pot right
								Pot n = (Pot)sprites.get(j);
								n.moveRight = true;
								n.moveLeft = false;
								n.moveDown = false;
								n.moveUp = false;
							}
							else if (link.getDirection() == 3){
								//need to move the pot down
								Pot n = (Pot)sprites.get(j);
								n.moveDown = true;
								n.moveLeft = false;
								n.moveRight = false;
								n.moveUp = false;
							}
							else if (link.getDirection() == 4){
								//Need to move the pot left
								Pot n = (Pot)sprites.get(j);
								n.moveLeft = true;
								n.moveDown = false;
								n.moveUp = false;
								n.moveRight = false;
							}
						}
					}
				}
			}
			//Update all sprites
			sprites.get(i).update();
			if (sprites.get(i).update() == false){
				sprites.remove(i);
			}
		}
	}


	//Boomerang Stuff
	public void addBoomerang(){
		Boomerang boom = new Boomerang();
		if (link.getDirection() == 1){
			boom.xdirection = 0;
			boom.ydirection = -1;
			boom.x = link.x + (link.w * 1/2);
			boom.y = link.y;
		}
		else if (link.getDirection() == 2){
			boom.xdirection = 1;
			boom.ydirection = 0;
			boom.x = link.x + link.w;
			boom.y = link.y + (link.h * 1/2);
		}
		else if (link.getDirection() == 3){
			boom.xdirection = 0;
			boom.ydirection = 1;
			boom.x = link.x + (link.w * 1/2);
			boom.y = link.y + link.h;
		}
		else if (link.getDirection() == 4){
			boom.xdirection = -1;
			boom.ydirection = 0;
			boom.x = link.x;
			boom.y = link.y + (link.h * 1/2);
		}
		sprites.add(boom);
	}

	//Pot Stuff
	void addPotToScreen(int X, int Y) {
		int x = X - X % 50;
		int y = Y - Y % 50;
		Pot p = new Pot(x, y);
		sprites.add(p);
	}

	

	//Brick stuff

	void addBrickToScreen(int X, int Y) {
		int x = X - X % 50;
		int y = Y - Y % 50;
		Brick n = new Brick(x,y);
		//Brick detection/deletion
		if (!collideBrick(x,y)) {
			sprites.add(n);
		}
		else {
			n = null;
			System.out.println("There is already a brick there!");
		}
	}

	/*
	Makes sure we aren't placing two bricks in the same location (USING ITERATOR HERE)
	Location x and y are the snap to grid locations of the brick we are trying to place 
	*/
	boolean collideBrick(int locationx, int locationy) { 
		if(sprites.size() == 0) {
			return false;
		}
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()){
				Sprite testing = it.next(); //Testing our new brick vs the existing bricks
				if (testing.isBrick()){
					if ((locationx >= testing.x && locationx < testing.x + testing.w && locationy >= testing.y && locationy < testing.y + testing.h)) {
						it.remove();
						return true;
					}
				}
		}
		return false;
	}
	

	//Marshalling methods
	Json Marshal(){
		Json ob1 = Json.newObject();

		Json tmpListBricks = Json.newList(); //Creating our list
		Json tmpListPots = Json.newList();

		//Adding our bricks to list and pots to list
        for(int i = 0; i < sprites.size(); i++){
			if (sprites.get(i).isBrick()){
				ob1.add("brick", tmpListBricks);
				tmpListBricks.add(sprites.get(i).Marshal()); //This is where it actually saves
			}
			if (sprites.get(i).isPot()){
				ob1.add("pot", tmpListPots);
				tmpListPots.add(sprites.get(i).Marshal());
			}
		}
		return ob1;
	}

	void Unmarshal(Json ob){
		sprites = new ArrayList<Sprite>(); //Making a new list for our sprites
		sprites.add(link); //Adding link to that list
		Json tmplistBricks = ob.get("brick"); //Getting bricks from our file
		Json tmpListPots = ob.get("pot");
		for (int i = 0; i < tmplistBricks.size(); i++){
			try {
				sprites.add(new Brick(tmplistBricks.get(i)));
				
			} catch (Exception e) {
				//Do nothing
			}

			try {
					sprites.add(new Pot(tmpListPots.get(i)));
			} catch (Exception e) {
				//Do nothing
			}
		}
	}
	
	//Loading the map
	public void loadFile(){
		Json loadObject = Json.load("brickLocation.json");
		Unmarshal(loadObject);
		// Json loadObject2 = Json.load("potLocation.json");
		// Unmarshal(loadObject2);
	}

	//Checking collisions between sprites
	static boolean isThereACollision(Sprite l, Sprite b){
		if (l.x + l.w < b.x){
			return false;
		}
		if (l.x > b.x + b.w){
			return false;
		}
		if(l.y + l.h < b.y){
			return false;
		}
		if (l.y > b.y + b.h){
			return false;
		}
		return true;
	}
	
	
}
