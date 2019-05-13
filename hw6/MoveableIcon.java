import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * simulates an object
 */

public class MoveableIcon extends ImageIcon implements MoveableShape
{
    /**
     * gets a file name
     * @param filename
     * @param x
     * @param y
     */

    public MoveableIcon(String filename, int x, int y)
    {
        super(filename);
        this.x = x;
        this.y = y;
        moving = true;
    }

    /**
     * return the bounds.
     * @return
     */

    public Rectangle getBounds(){

        Rectangle rec = new Rectangle(x, y, this.getIconWidth(), this.getIconHeight());

        return rec;
    }

    /**
     * simulates movement.
     */

    public void move()
    {
        int mov = new Random().nextInt(10);

        if(moving){
            x += mov;
            moving = false;
        }
        else {
            x -= mov;
            moving = true;
        }
    }


    /**
     * draws the icon.
     * @param g2 the graphics context
     */

    public void draw(Graphics2D g2){

        g2.drawImage(this.getImage(), x, y, null);

    }

    private int x;
    private int y;
    private boolean moving;
}