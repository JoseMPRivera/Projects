import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
   This program implements an animation that moves
   a car shape.
*/
public class AnimationTester
{
   /**
    * main
    * @param args
    */

   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      MoveableShape carObj;

      for(int i = 0 ; i < numObjects; i++){

         carObj = new CarShape(0,0, CAR_WIDTH);
         cars.add(carObj);
      }

//      final MoveableShape shape
//            = new CarShape(0, 0, CAR_WIDTH);


      JLabel lab;


//      ShapeIcon icon = new ShapeIcon(shape,
//            ICON_WIDTH, ICON_HEIGHT);
//
//      final JLabel label = new JLabel(icon);


      frame.setPreferredSize(new Dimension(1000, 1000));

      frame.setLayout(new FlowLayout());


      for(int i = 0 ; i < numObjects; i++){

         lab = new JLabel(new ShapeIcon(cars.get(i), ICON_WIDTH, ICON_HEIGHT));
         frame.add(lab);


      }

//      frame.add(label);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);

      final int DELAY = 100;
      // Milliseconds between timer ticks
      Timer t = new Timer(DELAY, event ->
         {

            for(int i = 0; i < numObjects; i++){

               cars.get(i).move();

            }

            for(int i = 0; i < numObjects; i++){

            labels.get(i).repaint();

         }
//            shape.move();
//            label.repaint();
         });
      t.start();
   }


   private static final ArrayList<MoveableShape> cars = new ArrayList<>();
   private static final ArrayList<JLabel> labels = new ArrayList<>();
   private static final int ICON_WIDTH = 400;
   private static final int ICON_HEIGHT =500;
   private static final int CAR_WIDTH = 100;
   private static final int numObjects = 7;

}
