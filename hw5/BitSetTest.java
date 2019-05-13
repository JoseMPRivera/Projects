import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
   Tests the BitSet implementation.
 */
public class BitSetTest
{
    /**
     Tests IntSequence
     */

   @Test public void doNotCopy1() {
      List<Integer> lst = new ArrayList<>();
       lst.add(1);
       lst.add(16);
       lst.add(20);
       lst.add(25);

      IntSequence seq = IntSequence.fromIterator(lst.iterator());
      assertEquals((Integer) 1, seq.next());
      lst.remove(0);
   }

   /**
    Tests Iterator
    */
   @Test public void SequenceTest()
   {
      IntSet set = new BitSet();
      set.set(10);
      set.set(100);
      set.set(102);
      set.set(-106);
      set.set(109);
      set.set(200);
      set.set(150);

      Iterator<Integer>  letsDelete = set.iterator();
      letsDelete.hasNext();
      System.out.println(letsDelete.next());

      letsDelete.hasNext();
      System.out.println(letsDelete.next());

      letsDelete.hasNext();
      System.out.println(letsDelete.next() + " bye");
      letsDelete.remove();

      letsDelete.remove();

      letsDelete.hasNext();
      System.out.println(letsDelete.next());

      letsDelete.hasNext();
      System.out.println(letsDelete.next());

      letsDelete.hasNext();
      System.out.println(letsDelete.next());

      letsDelete.hasNext();
      System.out.println(letsDelete.next());

      System.out.println("66666666666666666666666666666666666666666666666666666");

      for(Iterator<Integer> it = set.iterator(); it.hasNext();){

         System.out.println(it.next());

      }




   }


   /**
      Tests setting a value.
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
      Tests that the bits are set in the elements array
      (i.e. that this is actually a bit array).
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
      Tests setting and clearing a value.
    */
   @Test public void setAndClear()
   {
      BitSet set = new BitSet();
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
      Tests a set with 500 entries going up to a million.
    */
   @Test public void growSet()
   {
      IntSet set = new ArraySet();
      for (int i = 1; i <= 1000; i++)
      {
         set.set(i * i);
      }
      for (int i = 1; i <= 1000; i += 2)
      {
         set.clear(i * i);
      }
      for (int i = 1; i <= 1000; i++)
      {
         if (i % 2 == 0) assertTrue(set.test(i * i));
         else assertFalse(set.test(i * i));
      }
      assertEquals(4, set.min());
      assertEquals(1000000, set.max());
      assertEquals(500, set.size());
   }

   /**
      Tests a set with values between -999 and 1000.
    */
   @Test public void posAndNeg()
   {
      BitSet set = new BitSet();
      int sign = -1;
      for (int i = 1; i <= 1000; i++)
      {
         set.set(sign * i);
         sign *= -1;
      }
      for (int i = 1; i <= 1000; i++)
      {
         if (i % 2 == 0) assertTrue(set.test(i));
         else assertTrue(set.test(-i));
      }
      assertEquals(-999, set.min());
      assertEquals(1000, set.max());
      assertEquals(1000, set.size());      
   }

   /**
      Tests the growth of the bit array, as described in
      the problem specification.
    */
   @Test public void arrayTest()
   {
      BitSet set = new BitSet();
      set.set(100); 
      assertEquals(10, set.elements.length);
      assertEquals(100, set.start);
      set.set(500);
      assertEquals(20, set.elements.length);
      assertEquals(100, set.start);
      set.set(4000);
      assertEquals(122, set.elements.length);
      assertEquals(100, set.start);
   }
   
   /**
      Tests the growth of the bit array, as described in
      the problem specification, when elements to the left
      of the current minimum are added.
    */
   @Test public void arrayTestNeg()
   {
      BitSet set = new BitSet();
      set.set(100);
      set.set(-1);
      assertEquals(20, set.elements.length);
      assertEquals(-220, set.start);
      set.set(-1000);
      assertEquals(45, set.elements.length);
      assertEquals(-1020, set.start);
   }
}
