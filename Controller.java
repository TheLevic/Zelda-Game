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
		//Move view
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
				
		}

	}



	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: right = false; break;
			case KeyEvent.VK_LEFT: left = false; break;
			case KeyEvent.VK_UP: up = false; break;
			case KeyEvent.VK_DOWN: down = false; break;
			
		}
	}



	public void keyTyped(KeyEvent e)
	{
	}


	
	void update() {
		//Exit
		if(esc) System.exit(0);
		if (q) System.exit(0);

		//Moving our character
		if (right){
			Link.x += Link.speed;
			//Need to cycle 15-19
			if (Link.AnimationNum >= 19 || Link.AnimationNum < 15){
				Link.AnimationNum = 15;	
			}
			Link.AnimationNum++;
		}
		if (left){
			Link.x -= Link.speed;
			if (Link.AnimationNum >= 14 || Link.AnimationNum < 10){
				Link.AnimationNum = 10;	
			}
			Link.AnimationNum++;
		}
		if (up){
			Link.y -= Link.speed;
			if (Link.AnimationNum >= 4){
				Link.AnimationNum = 0;	
			}
			Link.AnimationNum++;
		}
		if (down){
			Link.y += Link.speed;
			if (Link.AnimationNum >= 9 || Link.AnimationNum < 5){
				Link.AnimationNum = 5;	
			}
			Link.AnimationNum++;
		}
	}


	
	public void mousePressed(MouseEvent e)
	{		
		//Gets the location of where user clicks
		int locationx = e.getX();
		int locationy = e.getY();
		if(mapEdit){
			Brick.addBrickToScreen(locationx + view.scrollPositonX, locationy + view.scrollPositonY); //Adds the brick to the screen
		}
		
	}





	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

}
