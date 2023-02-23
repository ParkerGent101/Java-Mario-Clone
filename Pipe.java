//Parker Gent
//9/30/22
//To create an array of pipes and to scroll left
//and right on the screen. Also able to save and 
//load an array of the pipes using .json marshalling.

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pipe extends Sprite{

    static BufferedImage pipeImage;

    public Pipe(int x, int y)
    {
        this.x = x;
        this.y = y;   
        
        if(pipeImage == null)
            pipeImage = View.loadImage("pipe.png");

            this.w = pipeImage.getWidth();
            this.h = pipeImage.getHeight();
    }

    @Override
    public String toString()
    {
        return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
    }

    public boolean detectPipe(int mx, int my)
    {
        if((mx >= this.x) && 
		   (mx <= this.x + w) &&
		   (my >= this.y) &&
		   (my <= this.y + h))
           return true;
        else
            return false;
        
    }

    public void draw(Graphics g, int scrollPosition)
    {
        g.drawImage(pipeImage, x - scrollPosition, y, null);
    }
    
    public void update()
    {    
    }
    
    public Pipe(Json ob)
    {
        this((int)ob.getLong("x"), (int)ob.getLong("y"));
    }


    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }

    @Override
    boolean isPipe()
    {
        return true;
    }

    @Override
    boolean isGoomba()
    {
        return false;
    }

    @Override
    boolean isFireball(){
        return false;
    }

    @Override
    boolean isMario(){
        return false;
    }
    
}
