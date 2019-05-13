import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this class simulates a game
 */

public class SimpleMoveStrategy {

    /**
     * main method that hold the program
     * @param args
     */

    public static void main(String[] args)
    {
        final int CAR_WIDTH = 100;
        List<MoveableShape> shapes = new ArrayList<>();
        shapes.add(new BoxedShape(new CompoundShape(new CarShape(200, 20, CAR_WIDTH),
                new MoveableIcon("dog.png", 100, 10),
                new MoveableIcon("dog.png", 150, 100)), 0));
        Animation.show(shapes,
                new BoundedMoveStrategy(new Rectangle(0, 0, 500, 200)),
                600, 200);
    }
}

