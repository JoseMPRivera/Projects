import static org.junit.Assert.*;
import org.junit.Test;

/**
 * tests to check arraySet and bitSet
 */

public class SetTest
{
    /**
     * test to check the array.
     */

    @Test public void setAndClear()
    {
        IntSet set = new ArraySet();
        set.set(100);
        set.set(150);
        set.clear(100);
        assertEquals(150, set.min());
        assertEquals(150, set.max());
        assertEquals(1, set.size());
        assertTrue(set.test(150));
        assertFalse(set.test(100));

    }

    /**
     * check the right order of elements.
     */

    @Test public void elementOrderRemove()
    {
        ArraySet set = new ArraySet();
        set.set(40);
        set.set(110);
        set.set(90);
        set.set(70);
        set.set(100);
        set.set(80);
        set.clear(110);
        assertEquals(40, set.elements[0]);
        assertEquals(80, set.elements[1]);
        assertEquals(90, set.elements[2]);
        assertEquals(100, set.largest);
    }

    /**
     * check if the array can handle one element.
     */

    @Test public void testOneElement()
    {
        IntSet set = new BitSet();
        set.set(100);
        assertEquals(100, set.min());
        assertEquals(100, set.max());
        assertEquals(1, set.size());
        assertTrue(set.test(100));
        assertFalse(set.test(99));
    }

    /**
     * test if the array gets the correct value when using two numbers.
     */

    @Test public void testBits()
    {
        BitSet set = new BitSet();
        set.set(100);
        set.set(102);
        set.clear(100);
        assertEquals(4, set.elements[0]);
        assertEquals(100, set.start);
    }

    /**
     * Test if the second smallest element creates a new array.
     */

    @Test public void testSecondFirst()
    {
        BitSet set = new BitSet();
        set.set(100);
        set.set(102);
        set.clear(100);
        assertEquals(4, set.elements[0]);
        assertEquals(100, set.start);
        set.set(90);
        assertEquals(90, set.start);



    }

    /**
     * test addition of elements in the array
     */

    @Test public void testAdd()
    {
        IntSet set = new ArraySet();
        set.set(100);
        set.set(150);
    }
}
