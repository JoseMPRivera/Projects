import java.awt.*;
import java.util.*;

/**
   A simple graph with round nodes and straight edges.
*/
public class SimpleGraph extends Graph
{
   /**
    * it creates the menu node
    * @return
    */

   public Node[] getNodePrototypes()
   {
      Node[] nodeTypes =
         {
            new CircleNode(Color.BLACK),
            new CircleNode(Color.WHITE),
            new DiamondNode(Color.BLACK)
         };
      return nodeTypes;
   }

   /**
    * it creates the edges node
    * @return
    */

   public Edge[] getEdgePrototypes()
   {
      Edge[] edgeTypes = 
         {
            new LineEdge(),
            new HVEdge(),
            new VHEdge()
         };
      return edgeTypes;
   }
}





