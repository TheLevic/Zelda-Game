/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
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
	// BufferedImage brick;
	



	//Position Variables
	static int scrollPositonX = 0;
	static int scrollPositonY = 0;
	//If I have trouble scrolling, might need to change these back to not final.
	final static int windowXSize = 700;
	final static int windowYSize = 500;

	View(Controller c, Model m){
		model = m;

		//Loading the brick image
		// brick = loadImage("brick.jpg");
		
		//Setting the view for the controller
		c.setView(this); 
	}

	static BufferedImage loadImage(String imageLocation){
		BufferedImage tmp = null;
		try {
			tmp = ImageIO.read(new File(imageLocation));
		} catch (Exception e) {
			System.out.println("Couldn't load images");
			e.printStackTrace();
			System.exit(1);
		}
		return tmp;
	}
	
	//Method that adds the image and color to the view
	public void paintComponent(Graphics g){
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		//Adds the bricks to the screen
		for (int i = 0; i < Model.bricks.size(); i++){
			Model.bricks.get(i).draw(g);
		}

		//Drawing link to screen
		model.link.draw(g);
	}
}
