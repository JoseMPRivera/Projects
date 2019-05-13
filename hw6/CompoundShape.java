import java.awt.*;
import java.util.ArrayList;

/**
 * group multiple shapes into one
 */

public class CompoundShape implements MoveableShape {

    /**
     * gets the shapes
     * @param shapes
     */

    public CompoundShape(MoveableShape... shapes){

        w = Integer.MAX_VALUE;
        z = Integer.MAX_VALUE;
        wMax = Integer.MIN_VALUE;
        zMax = Integer.MIN_VALUE;

        for(MoveableShape i: shapes){

            shape.add(i);
            if(w > i.getBounds().x){ w = i.getBounds().x;}
            if(z > i.getBounds().y){ z = i.getBounds().y;}
            if(wMax < i.getBounds().width){ wMax = i.getBounds().width;}
            if(zMax < i.getBounds().height){ zMax = i.getBounds().height;}

        }

        w += 3;
        z--;
    }

    /**
     * draws each shape.
     * @param g2 the graphics context
     */

    public void draw(Graphics2D g2){


        for (MoveableShape a: shape) {

            a.draw(g2);
        }
    }

    /**
     * return the bounds.
     * @return
     */

    public Rectangle getBounds(){

        Rectangle rec = new Rectangle(w + 1, z, 295, 200);

        return rec;
    }

    /**
     * simulates movement.
     */

    public void move(){

        for (MoveableShape a: shape) {

            a.move();
        }
    }

    ArrayList<MoveableShape> shape = new  ArrayList<>();
    private int w;
    private int z;
    private int wMax;
    private int zMax;

}
