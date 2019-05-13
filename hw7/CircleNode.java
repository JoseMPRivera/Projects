import java.awt.*;
import java.awt.geom.*;

/**
   A circular node that is filled with a color.
*/
public class CircleNode implements Node
{
   /**
      Construct a circle node with a given size and color.
      @param aColor the fill color
   */
   public CircleNode(Color aColor)
   {
      size = DEFAULT_SIZE;
      x = 0;
      y = 0;
      color = aColor;
   }

   /**
    * sets the color
    * @param aColor
    */

   public void setColor(Color aColor)
   {
      color = aColor;
   }

   /**
    * returns the color of the nodes
    * @return
    */

   public Color getColor()
   {
      return color;
   }

   /**
    * returns a clone object
    * @return
    */

   public Object clone()
   {
      try
      {
         return super.clone();
      }
      catch (CloneNotSupportedException exception)
      {
         return null;
      }
   }

   /**
    * it draws a circle node
    * @param g2 the graphics context
    */

   public void draw(Graphics2D g2)
   {
      Ellipse2D circle = new Ellipse2D.Double(
            x, y, size, size);
      Color oldColor = g2.getColor();
      g2.setColor(color);
      g2.fill(circle);
      g2.setColor(oldColor);
      g2.draw(circle);
   }

   /**
    * it translates the figure to new coordinates
    * @param dx the amount to translate in the x-direction
    * @param dy the amount to translate in the y-direction
    */

   public void translate(double dx, double dy)
   {
      x += dx;
      y += dy;
   }

   /**
    * return the contains
    * @param p
    * @return
    */

   public boolean contains(Point2D p)
   {
      Ellipse2D circle = new Ellipse2D.Double(
            x, y, size, size);
      return circle.contains(p);
   }

   /**
    * return the bounds of the cirlce
    * @return
    */

   public Rectangle2D getBounds()
   {
      return new Rectangle2D.Double(
            x, y, size, size);
   }

   /**
    * return the connection points
    * @param other
    * @return
    */

   public Point2D getConnectionPoint(Point2D other)
   {
      double centerX = x + size / 2;
      double centerY = y + size / 2;
      double dx = other.getX() - centerX;
      double dy = other.getY() - centerY;
      double distance = Math.sqrt(dx * dx + dy * dy);
      if (distance == 0) return other;
      else return new Point2D.Double(
            centerX + dx * (size / 2) / distance,
            centerY + dy * (size / 2) / distance);
   }

   private double x;
   private double y;
   private double size;
   private Color color;  
   private static final int DEFAULT_SIZE = 20;
}
