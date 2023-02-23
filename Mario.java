import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Mario extends Sprite {

    boolean touchingGround = false;
    int numFramesInAir = 0;

    int currentImage;
    BufferedImage[] images;

    public Mario(int x, int y)
    {
        this.x = x;
        this.y = y;
        currentImage = 0;

        images = new BufferedImage[5];

          for(int i = 0; i < 5; i++)
        {
            images[i] = View.loadImage("mario" + (i+1) + ".png");
        }

        this.h = images[0].getHeight();
         this.w = images[0].getWidth();
    }

    @Override
    public String toString()
    {
        return "Mario (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
    }

    void update()
    {
        vertVelocity += 9.8;
        y += vertVelocity;
        numFramesInAir++;

        if(y + h >= 395)
        {
            numFramesInAir = 0;
            vertVelocity = 0.0;
            y = 300;
        }
    }


    public void setPreviousPosition()
    {
        prevx = x;
        prevy = y;
    }

    void changeImageState()
    {
        currentImage++;
        if(currentImage > 4)
            currentImage = 0;
    }

    public void draw(Graphics g, int scrollPosition)
    {
        if(rightFacing)
            g.drawImage(images[currentImage], x - scrollPosition, y, null);

        if(!rightFacing)
            g.drawImage(images[currentImage], x - scrollPosition + w, y, -w, h, null);
    }

    @Override
    boolean isPipe() {
        return false;
    }

    @Override
    boolean isGoomba(){
        return false;
    }


    @Override
    boolean isFireball(){
        return false;
    }

    @Override
    boolean isMario(){
        return true;
    }
}
