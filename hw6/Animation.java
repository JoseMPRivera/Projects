import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

/**
 * simulates animation.
 */

public class Animation {

    /**
     * process to display the objects.
     * @param shape
     * @param strategy
     * @param width
     * @param height
     */

    public static void show(List<MoveableShape> shape, MoveStrategy strategy, int width, int height) {

        strategy.process(shape);




    }


}
