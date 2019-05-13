import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * move strategy.
 */

public class BoundedMoveStrategy implements MoveStrategy {

    Rectangle rec;

    /**
     * constrcutor to hold the bounds.
     * @param r
     */

    BoundedMoveStrategy(Rectangle r){

        rec = r;
    }

    /**
     * override the process.
     * @param shapes shapes to stop
     */

    @Override
    public void process(List<MoveableShape> shapes) {

        MoveableShape shape;
        final JLabel label;

        JFrame frame = new JFrame();

        shape = shapes.get(0);

        ShapeIcon icon = new ShapeIcon(shape, 400, 400);

        label = new JLabel(icon);


        frame.setLayout(new FlowLayout());

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        final int DELAY = 100;
        // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, event ->
        {
            shape.move();
            label.repaint();

        });
        t.start();

//        ArrayList<JLabel> labels = new ArrayList<>();
//
//        JFrame frame = new JFrame();
//
//        for (MoveableShape a: shapes) {
//
////             new ShapeIcon(a, width, height);
//
//            ShapeIcon icon = new ShapeIcon(a, 20, 20);
//
//            final JLabel label = new JLabel(icon);
//
//            labels.add(label);
//        }
//
//        frame.setLayout(new FlowLayout());
//
//        for(int i = 0; i < shapes.size(); i++){
//
//            frame.add(labels.get(i));
//        }
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//
//        final int DELAY = 100;
//        // Milliseconds between timer ticks
//        Timer t = new Timer(DELAY, event ->
//        {
//            for (MoveableShape a : shapes) {
//
//                a.move();
//            }
//            for (JLabel a : labels) {
//
//                a.repaint();
//            }
//
//        });
//        t.start();
    }
}
