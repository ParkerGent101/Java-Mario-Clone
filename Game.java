


import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	//MEMBER VARIABLES
	Model model;
	Controller controller;
	View view;


	public Game()
	{
		//Local Variables 
		//Always use new instance (object)
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);

		//this specifies the object within itself( i.e. game)
		this.addKeyListener(controller);
		this.setTitle("Assignment5");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run()
	{

		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 40 milliseconds
			try
			{
				Thread.sleep(40);
			} 
			catch(Exception e) //if there is input besides e quit
							  // program ends
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args)
	{
		//create game instance 
		//run method on game instance
		Game g = new Game();
		g.run();
	}


}
