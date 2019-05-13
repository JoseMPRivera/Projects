import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 An icon that contains a moveable shape.
 */
public class ShapeIcon implements Icon
{
   /**
    * creates a new moveableshape.
    * @param shape
    * @param width
    * @param height
    */

   public ShapeIcon(MoveableShape shape,
                    int width, int height)
   {
      this.shape = shape;
      this.width = width;
      this.height = height;
   }

   /**
    * returns width.
    * @return
    */

   public int getIconWidth()
   {
      return width;
   }

   /**
    * returns height.
    * @return
    */

   public int getIconHeight()
   {
      return height;
   }

   /**
    * paints the shape.
    * @param c
    * @param g
    * @param x
    * @param y
    */

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      shape.draw(g2);
   }

   private int width;
   private int height;
   private MoveableShape shape;
}


