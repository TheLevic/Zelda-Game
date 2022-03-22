/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 * 
 * 
 */
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


//This class is capable of handling ActionEvents such as when someone pushes a button
class Controller implements ActionListener, MouseListener, KeyListener{
	//Member variables
	View view;
	Model model;
	
	//Controls
		//Quit
		boolean esc;
		boolean q;
		//Save Load
		boolean s;
		boolean l;
		//Toggle map
		boolean mapEdit = false;
		//Movement
		boolean up;
		boolean down;
		boolean left;
		boolean right;
		//Boomerang
		boolean ctrl;
	
	
	Controller(Model m) {
		model = m;
	}



	public void actionPerformed(ActionEvent e) { //method that handles the event
	}


	
	void setView(View v) { //Setter that sets the view
		view = v;
	}


	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			//Quitting
			case KeyEvent.VK_ESCAPE: esc = true; break;
			case KeyEvent.VK_Q: q = true; break;

			//Saving
			case KeyEvent.VK_S:
				Json saveObject = model.Marshal();
				saveObject.save("brickLocation.json");
				System.out.println("Saved");
				break;
			//Loading
			case KeyEvent.VK_L:
				Json loadObject = Json.load("brickLocation.json");
				model.Unmarshal(loadObject);
				System.out.println("Loaded");
				break;
			
			//Toggling map edit
			case KeyEvent.VK_E:
				if(mapEdit){
					mapEdit = false;
					System.out.println("Map editing turned off!");
				}
				else{
					mapEdit = true;
					System.out.println("Map editing turned on!");
				}
				break;

			//Movement cases
			case KeyEvent.VK_RIGHT: 
				right = true; 
				break;
			case KeyEvent.VK_LEFT: 
				left = true; 
				break;
			case KeyEvent.VK_UP: 
				up = true; 
				break;
			case KeyEvent.VK_DOWN: 
				down = true;
				break;
			case KeyEvent.VK_CONTROL:
				ctrl = true;
				break;
				
		}

	}



	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			//Movement
			case KeyEvent.VK_RIGHT: right = false; break;
			case KeyEvent.VK_LEFT: left = false; break;
			case KeyEvent.VK_UP: up = false; break;
			case KeyEvent.VK_DOWN: down = false; break;

			//Boomerang
			case KeyEvent.VK_CONTROL: ctrl = false; break;
			
		}
	}



	public void keyTyped(KeyEvent e)
	{
	}


	
	void update() {
		model.link.savePrev();
		//Exit
		if(esc) System.exit(0);
		if (q) System.exit(0);

		//Moving Link
		//0-4 is runnning away, 5-9 is running forwards, 10-14 is running left, 15-19 is running right
		if (right){
			model.link.x += model.link.speed;
			//Moving the window when we goes through a right door
			viewIncreaseX();
			//Animating the character
			if (model.link.AnimationNum >= 19 || model.link.AnimationNum < 15){
				model.link.AnimationNum = 15;	
			}
			model.link.AnimationNum++;
		}

		if (left){
			model.link.x -= model.link.speed;

			//Moving the window when we goes through a left door
			viewDecreaseX();

			//Animating the character
			if (model.link.AnimationNum >= 14 || model.link.AnimationNum < 10){
				model.link.AnimationNum = 10;	
			}
			model.link.AnimationNum++;
		}

		if (up){
			model.link.y -= model.link.speed;

			//Moving the window when we goes through an up door
			viewDecreaseY();

			//Animating the character
			if (model.link.AnimationNum >= 4){
				model.link.AnimationNum = 0;	
			}
			model.link.AnimationNum++;
		}

		if (down){
			model.link.y += model.link.speed;

			//Moving the window when we goes through an down door
			viewIncraseY();

			//Animating the character
			if (model.link.AnimationNum >= 9 || model.link.AnimationNum < 5){
				model.link.AnimationNum = 5;	
			}
			model.link.AnimationNum++;
		}


		//Boomerang controls
		if (ctrl){
			for (int i = 0; i < Model.sprites.size(); i++){
				if (Model.sprites.get(i).isBoomerang()){
					//Need to check which direction we're facing, and then increment x or y by the correct direction and speed
					if (model.link.prevX < model.link.x){
						
					}
					else if (model.link.prevX > model.link.x){

					}
					else if (model.link.prevY < model.link.y){

					}
					else{

					}
					//The above should cover our directions, I just don't know what to actually update. Obviously the Boomerange x or y, but I don't know where to actually add it into the sprite list.
				}
			}
		}
	}


	
	public void mousePressed(MouseEvent e)
	{		
		//Gets the location of where user clicks
		int locationx = e.getX();
		int locationy = e.getY();
		if(mapEdit){
			Brick.addBrickToScreen(locationx + View.scrollPositonX, locationy + View.scrollPositonY); //Adds the brick to the screen
		}
		
	}


	//Making sure that the view stays in bounds
	void viewIncreaseX(){
		if (model.link.x == View.windowXSize){
			View.scrollPositonX += View.windowXSize;
		}
	}
	void viewDecreaseX(){
		if (model.link.x == View.windowXSize){
			View.scrollPositonX -= View.windowXSize;
		}

	}
	void viewIncraseY(){
		if (model.link.y == View.windowYSize){
			View.scrollPositonY += View.windowYSize;
		}
	}
	void viewDecreaseY(){
		if (model.link.y == View.windowYSize){
			View.scrollPositonY -= View.windowYSize;
		}
	}





	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

}
