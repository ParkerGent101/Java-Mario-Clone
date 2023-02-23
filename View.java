//Parker Gent
//9/30/22
//To create an array of pipes and to scroll left
//and right on the screen. Also able to save and 
//load an array of the pipes using .json marshalling.


import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	int scrollPosition;
	BufferedImage ground;

	View(Controller c, Model m)
	{
		scrollPosition = 0;
		model = m;
		c.setView(this);
		loadImage("pipe.png");
		ground = loadImage("ground.png");
	}

	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}

		return im;
	}

	public void paintComponent(Graphics g)
	{ 
		scrollPosition = model.mario.x - 100;
		g.setColor(new Color(128, 255, 255));
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(ground, 0, 200, null);
		for(int i = 0; i < model.sprites.size(); i++)
		{
			model.sprites.get(i).draw(g, scrollPosition);
		}
	}

}
