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
	BufferedImage link;
	BufferedImage[] link_images;



	//Position Variables
	int scrollPositonX = 0;
	int scrollPositonY = 0;
	int windowXSize = 700;
	int windowYSize = 500;

	View(Controller c, Model m){
		model = m;

		//Loading the brick image
		try {
			brick = ImageIO.read(new File("brick.jpg"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		//Need to load the image of link
		link_images = new BufferedImage[25];
		for (int i = 0; i < 20; i++){
			String tmp = Integer.toString(i + 1) + ".png";
			try {
				link_images[i] = ImageIO.read(new File("Link_Pictures/" + tmp));
			} catch (Exception e) {
				System.out.println("Couldn't load image");
				e.printStackTrace(System.err);
			}
			
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
