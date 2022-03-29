/* 
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 */
import java.util.ArrayList;


public class Model {
	//Member variables
	Link link;
	static ArrayList<Sprite> sprites;

	//Constructor
	Model(){
		link = new Link();
		sprites = new ArrayList<Sprite>();
		sprites.add(link);
	}

	public void update(){
		for (int i = 0; i < sprites.size(); i++){
			sprites.get(i).update();
			if (sprites.get(i).update() == false){
				sprites.remove(i);
			}
		}
	}

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

	

	
	

	//Marshalling methods
	Json Marshal(){
		Json ob1 = Json.newObject();

		Json tmpList = Json.newList(); //Creating our list
		//Adding our bricks and pots to list
        ob1.add("sprites", tmpList);
        for(int i = 0; i < sprites.size(); i++){
			tmpList.add(sprites.get(i).Marshal()); //This is where it actually saves
		}
		return ob1;
	}

	void Unmarshal(Json ob){
		sprites = new ArrayList<Sprite>(); //Making a new list for our sprites
		sprites.add(link); //Adding link to that list
		Json tmplist = ob.get("sprites"); //Getting bricks from our file
		for (int i = 0; i < tmplist.size(); i++){
			try {
				sprites.add(new Brick(tmplist.get(i)));
			} catch (Exception e) {
				//Do nothing
			}

			try {
				sprites.add(new Pot(tmplist.get(i)));
			} catch (Exception e) {
				//Do nothing
			}
		}
	}
	
	//Loading the map
	public void loadFile(){
		Json loadObject = Json.load("spriteLocation.json");
		Unmarshal(loadObject);
	}

	//Checking collisions between sprites and Link
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
