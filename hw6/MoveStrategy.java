import java.util.List;

/**
 * stop shapes that reach the boundary.
 */

public interface MoveStrategy {

    /**
     * stop shapes that reach the boundary.
     * @param shapes shapes to stop
     */

    void process(List<MoveableShape> shapes);
}
