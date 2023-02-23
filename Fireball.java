import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Fireball extends Sprite{

    BufferedImage fireBallImage;
    int speed = 20;
    int direction = 1;

    public Fireball(int x, int y)
    {
        this.x = x;
        this.y = y;   
        
        if(fireBallImage == null)
            fireBallImage = View.loadImage("fireball.png");

            this.w = fireBallImage.getWidth();
            this.h = fireBallImage.getHeight();
    }

    void draw(Graphics g, int scrollPosition)
    {
        if(rightFacing)
            g.drawImage(fireBallImage, x - scrollPosition, y, null);

        if(!rightFacing)
        g.drawImage(fireBallImage, x - scrollPosition + w, y, -w, h, null);

    }

    void update()
    {
        setPrevious();
        vertVelocity += 9.8;
        y += vertVelocity;
        x += speed * direction;

        if(y > 395 - h)
        {
            vertVelocity = 0;
            y = 300 - h;
        }

    }

    void setPrevious()
    {
        prevx = x;
        prevy = y;
    }

    @Override
    boolean isGoomba()
    {
        return false;
    }

    @Override
    boolean isPipe()
    {
        return false;
    }

    @Override
    boolean isFireball(){
        return true;
    }

    @Override
    boolean isMario(){
        return false;
    }


    
}
