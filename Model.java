/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 *
 * 
 * 
 */
import java.util.ArrayList;


public class Model {
	//Member variables
	Link link;
	static ArrayList<Brick> bricks;

	//Constructor
	Model(){
		link = new Link();
		bricks = new ArrayList<Brick>();
	}

	public void update(){
		link.update();
		for (int i = 0; i < bricks.size(); i++){
			boolean collision = isThereACollision(link, bricks.get(i));
			if (collision){
				link.getOutOfBrick(bricks.get(i));
			}
		}
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
		for(int i = 0; i < tmplist.size(); i++){
            bricks.add(new Brick(tmplist.get(i)));
		}
	}
	//Loading the map
	public void loadFile(){
		Json loadObject = Json.load("brickLocation.json");
		Unmarshal(loadObject);
	}

	//Checking collisions between bricks and Link
	public boolean isThereACollision(Link l, Brick b){
		if (Link.x + Link.w < b.x){
			return false;
		}
		if (Link.x > b.x + b.w){
			return false;
		}
		if(Link.y + Link.h < b.y){
			return false;
		}
		if (Link.y > b.y + b.h){
			return false;
		}
		return true;
	}
	
	
}
