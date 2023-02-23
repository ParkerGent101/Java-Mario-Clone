//Parker Gent
//9/30/22
//To create an array of pipes and to scroll left
//and right on the screen. Also able to save and 
//load an array of the pipes using .json marshalling.


//various libraies in java framework self-explanitory
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	//class member varibaels
	View view;
	Model model;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spaceBar;
	boolean editMode = false;
	boolean goombaMode = false;
	boolean pipeMode = false;

	Controller(Model m)
	{
		model = m;
	}

	
	void setView(View v)
	{
		view = v;
	}


	public void actionPerformed(ActionEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
		if((editMode == true || pipeMode == true) 
			&& goombaMode == false)
			model.addPipe((e.getX() + view.scrollPosition), e.getY());

		if(editMode == true && goombaMode == true
			&& pipeMode == false)
			model.addGoomba((e.getX() + view.scrollPosition), e.getY());
		
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: spaceBar = true; break;
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
			case KeyEvent.VK_SPACE: spaceBar = false; break;
		}

		char c = Character.toLowerCase(e.getKeyChar());
		if(c == 'q')
			System.exit(0);
			
		if(c == 's')
		{
			Json saveObject = model.marshal();
			saveObject.save("Map.json");
			System.out.println("Map is saved");

		}

		if(c == 'l')
		{
			Json j = Json.load("Map.json");
			model.unmarshal(j);
			System.out.println("File is loaded");
		}

		if(c == 'e')
		{
			editMode = !editMode;
			System.out.println("Edit mode: " + editMode); 
		}

		if(c == 'g')
		{
			if(editMode)
			{
				goombaMode = !goombaMode;
				System.out.println("Goomba mode: " + goombaMode);
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}

		if(e.getKeyCode() == KeyEvent.VK_CONTROL)
		{
			model.sprites.add(new Fireball(model.mario.x, model.mario.y));
		}
	}


	
	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.mario.setPreviousPosition();
		if(keyRight) 
		{
			model.mario.rightFacing = true;
			model.mario.changeImageState();
			model.mario.x += 5;
		}
		if(keyLeft)
		{
			model.mario.rightFacing = false;
			model.mario.changeImageState();
			model.mario.x -= 5;
		}
		if(spaceBar)
		{
			//if(model.mario.touchingGround == true)
				if(model.mario.numFramesInAir < 15)
					model.mario.y -=65;
		}		
	}
	
}


