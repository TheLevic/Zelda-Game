/*
 * Levi Crider
 * 2/9/22
 * Turtle Game!
 * Create a game that allows the turtle to move across the screen with arrow keys or mouse clicks
 * 
 * 
 */

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;

//JPanel is the content that is inside of the application (Creates the users view)
class View extends JPanel
{
	//Member variables
	JButton b1; 
	BufferedImage turtle_image;
	BufferedImage brick;
	Model model;

	View(Controller c, Model m)
	{
		model = m;
//		Adding the turtle image to our view
		try
		{
			this.turtle_image =
				ImageIO.read(new File("turtle.png"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
		try {
			this.brick = ImageIO.read(new File("brick.jpg"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
		
//		b1 = new JButton("Don't push me");
//		b1.addActionListener(c); //Tells the button which object will handle the event that occurs
//		this.add(b1); //Actually adding it to the window
		c.setView(this); //Setting the view for the controller
	}
	
	//Method that removes the button
	void removeButton(){
		this.remove(b1);
		this.repaint();
	}
	
	//Method that adds the image and color to the view
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
//		g.drawImage(this.turtle_image, model.turtle_x, model.turtle_y, null);
//		g.drawImage(this.brick, Brick.x,Brick.y, null);
		for(int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			g.drawImage(this.brick, b.x, b.y, null);
		}
	}
}
