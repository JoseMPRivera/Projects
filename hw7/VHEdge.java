import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class VHEdge extends LineEdge {

    /**
     * it draws a vertical-horizontal connection
     * @param g2 the graphics context
     */

    public void draw(Graphics2D g2)
    {
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(super.getLineStyle().getStroke());

        Line2D line = getConnectionPoints();

        Point2D.Double r1 = new Point2D.Double(line.getX1(), line.getY1());
        Point2D.Double r2 = new Point2D.Double(line.getX2(), line.getY1());
        Point2D.Double r3 = new Point2D.Double(line.getX2(), line.getY2());

        Line2D.Double h = new Line2D.Double(r1, r2);
        Line2D.Double v = new Line2D.Double(r2, r3);

        g2.draw(h);
        g2.draw(v);

        g2.setStroke(oldStroke);
    }
}
