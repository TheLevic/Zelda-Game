/*
 * Levi Crider
 * 2/9/22
 * Turtle Game!
 * Create a game that allows the turtle to move across the screen with arrow keys or mouse clicks
 * 
 * 
 */

import javax.swing.JFrame;
import java.awt.Toolkit;

//JFrame is the box that surrounds the internal application
public class Game extends JFrame
{
	//Member variables
	Model model;
	View view;
	Controller controller;
	
	
	//Constructor
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model); //Created two new objects. A controller and the view
		view.addMouseListener(controller);
		this.setTitle("A3 - Map Editor");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);
	}
	
	//Animates the turtle
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 40 miliseconds (25 fps)
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	

	public static void main(String[] args)
	{
		Game g = new Game(); //Creating the game object
		g.run();
	}
}
