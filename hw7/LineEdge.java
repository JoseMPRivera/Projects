import java.awt.*;
import java.awt.geom.*;

/**
   An edge that is shaped like a straight line.
*/
public class LineEdge extends AbstractEdge
{
   /**
    * creates a new lineEdge
    */

   public LineEdge()
   {
      lineStyle = LineStyle.SOLID;
   }

   /**
    * it draws the line
    * @param g2 the graphics context
    */

   public void draw(Graphics2D g2)
   {
      Stroke oldStroke = g2.getStroke();
      g2.setStroke(lineStyle.getStroke());
      g2.draw(getConnectionPoints());
      g2.setStroke(oldStroke);
   }

   /**
    * it returns if the line are contained
    * @param aPoint the point to test
    * @return
    */

   public boolean contains(Point2D aPoint)
   {
      final double MAX_DIST = 2;
      return getConnectionPoints().ptSegDist(aPoint) 
         < MAX_DIST;
   }

   /**
      Sets the line style property.
      @param newValue the new value
   */
   public void setLineStyle(LineStyle newValue) { lineStyle = newValue; }

   /**
      Gets the line style property.
      @return the line style
   */
   public LineStyle getLineStyle() { return lineStyle; }
   
   private LineStyle lineStyle;
}
