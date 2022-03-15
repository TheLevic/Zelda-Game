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
	static ArrayList<Sprite> sprites;

	//Constructor
	Model(){
		link = new Link();
		sprites = new ArrayList<Sprite>();
		sprites.add(link);
	}

	public void update(){
		// link.update();
		for (int i = 0; i < sprites.size(); i++){
			sprites.get(i).update();
		}
	}

	
	

	//Marshalling methods
	Json Marshal(){
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
	
        ob.add("brick", tmpList);
        for(int i = 0; i < sprites.size(); i++)
            tmpList.add(sprites.get(i).Marshal());
        return ob;
	}

	void Unmarshal(Json ob){
		sprites = new ArrayList<Sprite>(); //Making a new list for our sprites
		sprites.add(link);
		Json tmplist = ob.get("brick"); //Getting sprites from our file
		for(int i = 0; i < tmplist.size(); i++){
            sprites.add(new Brick(tmplist.get(i)));
		}
	}
	//Loading the map
	public void loadFile(){
		Json loadObject = Json.load("brickLocation.json");
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
