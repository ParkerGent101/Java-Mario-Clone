import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Goomba extends Sprite{

    BufferedImage goombaImage;
    BufferedImage fireImage;
    int speed = 6;
    int direction = 1;
    int numFramesDead = 0;
    boolean dead = false;


    public Goomba(int x, int y)
    {
        this.x = x;
        this.y = y;   
        
        if(goombaImage == null)
            goombaImage = View.loadImage("goomba.png");

            this.w = goombaImage.getWidth();
            this.h = goombaImage.getHeight();
    }

    void draw(Graphics g, int scrollPosition)
    {
        if(rightFacing)
            g.drawImage(goombaImage, x - scrollPosition, y, w, h, null);
        else
        {
            g.drawImage(fireImage, x - scrollPosition + w, y, w, h, null);
        }
    }

    void loadFire()
    {
        if(fireImage == null)
        {
            fireImage = View.loadImage("goomba_fire.png");
        }
        rightFacing = false;
        speed = 0;
    }
    
    void update()
    {
        setPrevious();
        vertVelocity += 9.8;
        y += vertVelocity;
        x += speed * direction;

        if(y > 375 - h)
        {
            vertVelocity = 0;
            y = 375 - h;
        }

        if(dead == true)
        {
            numFramesDead++;
        }
    }

    void setPrevious(){

        prevx = x;
        prevy = y;

    }

    public boolean detectGoomba(int mx, int my)
    {
        if((mx >= this.x) && 
		   (mx <= this.x + w) &&
		   (my >= this.y) &&
		   (my <= this.y + h))
           return true;
        else
            return false;
        
    }

    public Goomba(Json ob)
    {
        this((int)ob.getLong("x"), (int)ob.getLong("y"));
        
        if(goombaImage == null)
            goombaImage = View.loadImage("goomba.png");

            this.w = goombaImage.getWidth();
            this.h = goombaImage.getHeight();
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
    boolean isGoomba()
    {
        return true;
    }

    @Override
    boolean isPipe()
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

    @Override
    public String toString()
    {
        return "Goomba (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
    }
    
}
