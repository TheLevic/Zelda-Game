/*
 * Levi Crider
 * 2/9/22
 * Map editor
 * Create a game that allows the turtle to move across the screen with arrow keys or mouse clicks
 * 
 * 
 */

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;

//JPanel is the content that is inside of the application (Creates the users view)
class View extends JPanel{
	//Member variables
	JButton b1; 
	Model model;
	BufferedImage brick;



	//Position Variables
	int scrollPositonX = 0;
	int scrollPositonY = 0;
	int windowXSize = 700;
	int windowYSize = 500;




	View(Controller c, Model m){
		model = m;
		try {
			brick = ImageIO.read(new File("brick.jpg"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
		c.setView(this); //Setting the view for the controller
	}


	
	//Method that adds the image and color to the view
	public void paintComponent(Graphics g){
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Adds the bricks to the screen
		for(int i = 0; i < Brick.bricks.size(); i++){
			Brick b = Brick.bricks.get(i);
			g.drawImage(this.brick, b.x - scrollPositonX, b.y - scrollPositonY, null);
		}
	}
}
