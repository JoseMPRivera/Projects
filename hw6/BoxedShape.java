import javax.swing.*;
import java.awt.*;

/**
 * creates a shape.
 */

public class BoxedShape implements MoveableShape {

    /**
     * gets an object an generates a shape.
     * @param shape
     * @param gap
     */

    public BoxedShape(MoveableShape shape, int gap){

        this.shape = shape;
        this.gap = gap;
    }

    /**
     * draws the rectangle.
     * @param g2 the graphics context
     */

    public void draw(Graphics2D g2){

        Rectangle r = shape.getBounds();

        r.x = r.x - gap/2;
//        r.y = r.y - 1;

        r.width = r.width + gap;
        r.height = r.height + gap;

        shape.draw(g2);
        g2.draw(r);
    }

    /**
     * return the bounds.
     * @return
     */

    public Rectangle getBounds(){

        Rectangle rec = this.getBounds();
        return rec;
    }

    /**
     * simulates movement.
     */

    public void move(){

        shape.move();
    }

    private int gap;
    private MoveableShape shape;
}
