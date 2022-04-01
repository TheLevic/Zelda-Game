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
		boolean potOrBrick = false; //false is for brick, true is for pots.
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
				
				model.Marshal();
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
					if (potOrBrick){
						System.out.println("Editing Pots right now!");
					}
					else if (!potOrBrick){
						System.out.println("Editing Bricks right now!");
					}
				}
				break;
			case KeyEvent.VK_P:
				if (potOrBrick){
					potOrBrick = false;
					if (mapEdit && !potOrBrick){
						System.out.println("Editing Bricks");
					}
				}
				else{
					potOrBrick = true;
					if (mapEdit && potOrBrick){
						System.out.println("Editing Pots");
					}
					
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
			case KeyEvent.VK_CONTROL: model.addBoomerang(); break;
			
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
		if (right){
			viewIncreaseX();
			model.link.moveRight();
		}

		if (left){
			//Moving the window when we goes through a left door
			viewDecreaseX();
			model.link.moveLeft();
		}

		if (up){
			model.link.moveUp();
			//Moving the window when we goes through an up door
			viewDecreaseY();
			
		}

		if (down){
			model.link.moveDown();
			//Moving the window when we goes through an down door
			viewIncraseY();
		}
	}


	
	public void mousePressed(MouseEvent e)
	{		
		//Gets the location of where user clicks
		int locationx = e.getX();
		int locationy = e.getY();
		//Adding bricks or pots to the map
		if(mapEdit && !potOrBrick){
			model.addBrickToScreen(locationx + View.scrollPositonX, locationy + View.scrollPositonY); //Adds the brick to the screen
		}
		if (mapEdit && potOrBrick){
			model.addPotToScreen(locationx + View.scrollPositonX, locationy + View.scrollPositonY);
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
