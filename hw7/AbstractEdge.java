import java.awt.*;
import java.awt.geom.*;

/**
   A class that supplies convenience implementations for
   a number of methods in the Edge interface type.
*/
public abstract class AbstractEdge implements Edge
{
   /**
    * overrides the clon method
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
    * conencts two nodes
    * @param s
    * @param e
    */

   public void connect(Node s, Node e)
   {
      start = s;
      end = e;
   }

   /**
    * returns the first connection
    * @return
    */

   public Node getStart()
   {
      return start;
   }

   /**
    * returns the second connection node
    * @return
    */

   public Node getEnd()
   {
      return end;
   }

   /**
    * returns the bounds of the shape
    * @param g2
    * @return
    */

   public Rectangle2D getBounds(Graphics2D g2)
   {
      Line2D conn = getConnectionPoints();
      Rectangle2D r = new Rectangle2D.Double();
      r.setFrameFromDiagonal(conn.getX1(), conn.getY1(),
         conn.getX2(), conn.getY2());
      return r;
   }

   /**
    * returns the conection points of the nodes
    * @return
    */

   public Line2D getConnectionPoints()
   {
      Rectangle2D startBounds = start.getBounds();
      Rectangle2D endBounds = end.getBounds();
      Point2D startCenter = new Point2D.Double(
         startBounds.getCenterX(), startBounds.getCenterY());
      Point2D endCenter = new Point2D.Double(
         endBounds.getCenterX(), endBounds.getCenterY());
      return new Line2D.Double(
         start.getConnectionPoint(endCenter),
         end.getConnectionPoint(startCenter));
   }

   private Node start;
   private Node end;
}
