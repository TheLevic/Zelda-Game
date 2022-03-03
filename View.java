/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 * 
 * 1-5 is runnning away, 6-10 is running forwards, 11-15 is running left, 16-20 is running right
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
	//If I have trouble scrolling, might need to change these back to not final.
	final int windowXSize = 700;
	final int windowYSize = 500;

	View(Controller c, Model m){
		model = m;
		

		//Loading the brick image
		brick = loadImage("brick.jpg");
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
		for(int i = 0; i < Brick.bricks.size(); i++){
			Brick b = Brick.bricks.get(i);
			g.drawImage(this.brick, b.x - scrollPositonX, b.y - scrollPositonY, null);
		}
		//Drawing link to screen
		Link.draw(g); //Why is this not working?
	}
}
