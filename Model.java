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
	

	//Constructor
	Model(){
		
	}

	public void update(){
		Link.update();
	}
	

	//Marshalling methods
	Json Marshal(){
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
        ob.add("brick", tmpList);
        for(int i = 0; i < Brick.bricks.size(); i++)
            tmpList.add(Brick.bricks.get(i).Marshal());
        return ob;
	}

	void Unmarshal(Json ob){
		Brick.bricks = new ArrayList<Brick>(); //Making a new list for our bricks
		Json tmplist = ob.get("brick"); //Getting bricks from our file
		for(int i = 0; i < tmplist.size(); i++)
            Brick.bricks.add(new Brick(tmplist.get(i)));
    }

	//Loading the map
	public void loadFile(){
		Json loadObject = Json.load("brickLocation.json");
		Unmarshal(loadObject);
	}
	
	
}
