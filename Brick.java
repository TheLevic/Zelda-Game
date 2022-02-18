
public class Brick {
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
