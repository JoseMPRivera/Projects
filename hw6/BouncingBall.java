import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * emulates a bouncing ball.
 */

public class BouncingBall implements MoveableShape {

    /**
     * constructor for the ball
     * @param x
     * @param y
     * @param width
     */

    public BouncingBall(int x, int y, int width)
    {
        moving = false;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    /**
     * gets the bounds of the ball.
     * @return
     */

    public Rectangle getBounds(){

        Rectangle rec = new Rectangle(x, y, width, width);

        return rec;

    }

    /**
     * move the object.
     */

    public void move()
    {
        if(moving){
            y += 7;
            moving = false;
        }
        else {
            y -= 7;
            moving = true;
        }
    }

    /**
     * draws the object.
     * @param g2 the graphics context
     */

    public void draw(Graphics2D g2)
    {

        Ellipse2D.Double ball
                = new Ellipse2D.Double(0, 0, width, width);


        g2.draw(ball);
    }

    private int x;
    private int y;
    private int width;
    private boolean moving;

}
