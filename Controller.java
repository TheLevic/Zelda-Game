/*
 * Levi Crider
 * 2/9/22
 * Turtle Game!
 * Create a game that allows the turtle to move across the screen with arrow keys or mouse clicks
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
class Controller implements ActionListener, MouseListener, KeyListener
{
	//Member variables
	View view;
	Model model;
	
	//Controls
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean esc;
	boolean q;
	
	
	Controller(Model m) {
		model = m;
	}

	public void actionPerformed(ActionEvent e) { //method that handles the event
	}
	
	void setView(View v) { //Setter that sets the view
		view = v;
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_ESCAPE: esc = true; break;
			case KeyEvent.VK_Q: q = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}
	
	void update() {
//		if(keyRight) model.dest_x+=4;
//		if(keyLeft) model.dest_x-=4;
//		if(keyDown) model.dest_y+=4;
//		if(keyUp) model.dest_y-=4;
		if(esc) System.exit(0);
		if (q) System.exit(0);
	}
	
	public void mousePressed(MouseEvent e)
	{		
		//Gets the location of where user clicks
		int locationx = e.getX();
		int locationy = e.getY();
		model.addBrickToScreen(locationx, locationy); //Adds the brick to the screen
		
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }
}
