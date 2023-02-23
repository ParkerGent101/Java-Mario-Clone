import java.awt.Graphics;

abstract class Sprite {
    int x, y, w, h, prevx, prevy;
    double vertVelocity;
    boolean rightFacing = true;


    abstract void update();
    abstract void draw(Graphics g, int scrollPosition);

    abstract boolean isPipe();
    abstract boolean isGoomba();
    abstract boolean isFireball();
    abstract boolean isMario();

}
