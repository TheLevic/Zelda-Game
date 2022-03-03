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
	BufferedImage brick;
	static BufferedImage[] linkImages;
	



	//Position Variables
	int scrollPositonX = 0;
	int scrollPositonY = 0;
	//If I have trouble scrolling, might need to change these back to not final.
	final int windowXSize = 700;
	final int windowYSize = 500;

	View(Controller c, Model m){
		model = m;
		linkImages = new BufferedImage[24];

		//Loading the brick image
		brick = loadImage("brick.jpg");
		//Loading Links image
		for (int i = 1; i < 25; i++){
			String tmp = Integer.toString(i) + ".png";
			linkImages[i - 1] = View.loadImage("linkPictures/" + tmp);
			System.out.println("Loading image" + i);
		}
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
		// Link.draw(g); //Why is this not working?
		g.drawImage(linkImages[Link.AnimationNum], Link.x, Link.y,null);
	}
}
