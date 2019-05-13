import java.util.Iterator;

/**
 * Interface
 */

public interface IntSequence {

    /**
     * emulates next for iterator
     * @return the next integer
     */

    Integer next();

    /**
     * iterator implementation
     * @param iter gets the arrayList
     * @return return the element
     */

    static IntSequence fromIterator(Iterator<Integer> iter){

        return ()-> {

            if(iter.hasNext()){ return iter.next();}
            return null;
        };
    }

    /**
     * alternate two sequences
     * @param other second sequence
     * @return return the two sequences
     */

    default IntSequence alternate(IntSequence other){

        return other;
    }
}
