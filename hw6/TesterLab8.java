import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * tester.
 */

public class TesterLab8 {

    /**
     * tester frame
     * @param args
     */

    public static void main(String[] args) {

        JFrame frame = new JFrame();
//        frame.setPreferredSize(new Dimension(1000, 1000));

        for(int i = 0; i < numObjects; i++){

            MoveableShape shape
                    = new BoxedShape( new CompoundShape( new CarShape(0, 2, CAR_WIDTH),
                    new CarShape(0, 60, CAR_WIDTH), new MoveableIcon("dog.png", 80, 100)), 9);

//            MoveableShape shape
//                    = new BoxedShape( new CarShape(0, 0, CAR_WIDTH), 15);

//            MoveableShape shape
//                    = new MoveableIcon("dog.png", 0, 100);

            ShapeIcon icon = new ShapeIcon(shape,
                    ICON_WIDTH, ICON_HEIGHT);

            final JLabel label = new JLabel(icon);

            cars.add(shape);
            labels.add(label);

        }


        frame.setLayout(new FlowLayout());

        for(int i = 0; i < cars.size(); i++){

            frame.add(labels.get(i));
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        final int DELAY = 100;
        // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, event ->
        {
            for (MoveableShape a : cars) {

                a.move();
            }
            for (JLabel a : labels) {

                a.repaint();
            }

        });
        t.start();
    }

    private static final int ICON_WIDTH = 400;
    private static final int ICON_HEIGHT = 400;
    private static final int CAR_WIDTH = 100;

    private static ArrayList<MoveableShape> cars = new ArrayList<>();
    private static  ArrayList<JLabel> labels = new ArrayList<>();
    private static final int numObjects = 1;

}