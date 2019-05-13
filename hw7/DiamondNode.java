import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Creates a diamon figure.
 */

public class DiamondNode implements Node {

    /**
     Construct a circle node with a given size and color.
     @param aColor the fill color
     */
    public DiamondNode(Color aColor)
    {
        size = DEFAULT_SIZE;
        x = 0;
        y = 0;
        color = aColor;
    }

    /**
     * set the color of the diamond.
     * @param aColor
     */

    public void setColor(Color aColor)
    {
        color = aColor;
    }

    /**
     * returns the color.
     * @return
     */

    public Color getColor()
    {
        return color;
    }

    /**
     * ir clones the node.
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
     * it draws a diamond.
     * @param g2 the graphics context
     */

    public void draw(Graphics2D g2)
    {
        Point2D.Double r1 = new Point2D.Double(x , y + size / 2);
        Point2D.Double r2 = new Point2D.Double(x + size/2, y);
        Point2D.Double r3 = new Point2D.Double(x + size, y + size / 2);
        Point2D.Double r4 = new Point2D.Double(x + size/2 , y + size);

        Line2D.Double diagonal1 = new Line2D.Double(r1, r2);
        Line2D.Double diagonal2 = new Line2D.Double(r2, r3);
        Line2D.Double diagonal3 = new Line2D.Double(r1, r4);
        Line2D.Double diagonal4 = new Line2D.Double(r3, r4);

        g2.setColor(color);
        g2.draw(diagonal1);
        g2.draw(diagonal2);
        g2.draw(diagonal3);
        g2.draw(diagonal4);

//        int xpoints[] = {(int)x, (int)(x + size/2), (int)(x + size), (int)(x + size/2)};
//        int ypoints[] = {(int)(y + size / 2), (int)(y), (int)(y + size / 2), (int)(y + size)};
//        int npoints = 4;
//
//        Polygon x = new Polygon(xpoints, ypoints, npoints);
//

//        Font myFont = new Font ("Courier New", 1, 17);
//        g2.setFont (myFont);
//        g2.drawString(dx + "                        " + dy, 50, 50);
//
//        g2.setColor(Color.black);
//        g2.drawPolygon(xpoints, ypoints, 4);
//        g2.setColor(Color.white);
//        g2.fillPolygon(xpoints, ypoints, 4);
//        g2.setColor(Color.black);
    }

    /**
     * translates the figure to x and y corrdinates.
     * @param dx the amount to translate in the x-direction
     * @param dy the amount to translate in the y-direction
     */

    public void translate(double dx, double dy)
    {
        x += dx;
        y += dy;
    }

    /**
     * it retuns if the shape is inside two points
     * @param p
     * @return
     */

    public boolean contains(Point2D p)     //modify the contaians - right outside the diamond
    {
        int xpoints[] = {(int)x, (int)(x + size/2), (int)(x + size), (int)(x + size/2)};
        int ypoints[] = {(int)(y + size / 2), (int)(y), (int)(y + size / 2), (int)(y + size)};
        int npoints = 4;

        Polygon x = new Polygon(xpoints, ypoints, npoints);

        return x.contains(p);
    }

    /**
     * returns the bounds of the diamond
     * @return
     */

    public Rectangle2D getBounds()
    {
        return new Rectangle2D.Double(
                x, y, size, size);
    }

    /**
     * returns the conection points of the two shapes
     * @param other
     * @return
     */

    public Point2D getConnectionPoint(Point2D other)
    {
        double centerX = x + size / 2;
        double centerY = y + size / 2;
        dx = other.getX() - centerX;
        dy = other.getY() - centerY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) return other;
        else{

            Point2D.Double r1 = new Point2D.Double(x , y + size / 2);               //r
            Point2D.Double r2 = new Point2D.Double(x + size/2, y);                  //u
            Point2D.Double r3 = new Point2D.Double(x + size, y + size / 2);      //l
            Point2D.Double r4 = new Point2D.Double(x + size/2 , y + size);      //d

            if(dx >= dy){

                if(dx >= -dy) return r3;
                return r2;
            }
            else  if(dx < -dy) return r1;

            else return r4;
        }

//        double centerX = x + size / 2;
//        double centerY = y + size / 2;
//        dx = other.getX() - centerX;
//        dy = other.getY() - centerY;
//        double distance = Math.sqrt(dx * dx + dy * dy);
//        if (distance == 0) return other;
//
//
//        return new Point2D.Double(
//                centerX + dx * (size / 2) / distance,
//                centerY + dy * (size / 2) / distance);
////                centerX, centerY );

    }

    private double x;
    private double y;
    private double dx;
    private double dy;
    private double size;
    private Color color;
    private static final int DEFAULT_SIZE = 26;

}
