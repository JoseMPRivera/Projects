import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Iterator;

/**
   Tests the ArraySet implementation.
 */
public class ArraySetTest
{
   /**
    Tests arraySet with an iter.
    */
   @Test public void iterTestArraySet()
   {
      ArraySet set = new ArraySet();
      set.set(100);
      set.set(40);
      set.set(110);
      set.set(90);
      set.set(70);
      set.set(100);
      set.set(80);

      for(Iterator<Integer> it = set.iterator(); it.hasNext();){

         System.out.println(it.next());

      }

//      System.out.println("----------------REMOVE-------------------");

//      Iterator<Integer> iter2 = set.iterator();
//
//      iter2.hasNext();
//
//      System.out.println(iter2.next());
//      iter2.remove();
//
//      iter2.hasNext();
//
//      System.out.println(iter2.next());
//      iter2.hasNext();
//
//      System.out.println(iter2.next());
//      iter2.hasNext();
//
//      System.out.println(iter2.next());
//      iter2.hasNext();
//
//      System.out.println(iter2.next());
//      iter2.hasNext();
//
//      System.out.println(iter2.next());
//      iter2.hasNext();


//      System.out.println("----------------REMOVE-------------------");

//      Iterator<Integer> iter3 = set.iterator();
//
//      iter3.hasNext();
//
//      System.out.println(iter3.next());
//      iter3.hasNext();
//
//      System.out.println(iter3.next());
//      iter3.hasNext();
//
//      System.out.println(iter3.next());
//      iter3.hasNext();



//      for(Iterator<Integer> it = set.iterator(); it.hasNext();){
//
//         int x = it.next();
//
//         if (x == 70) it.remove();
//      }

//      for(Iterator<Integer> it1 = set.iterator(); it1.hasNext();){
//
//         System.out.println(it1.next());
//
//      }




      //ArraySet.ArraySetIterator iter = new ArraySet().new ArraySetIterator();
      //iter = set.ArraySetIterator;
//
//      while (iter.hasNext()){
//
//         System.out.println(iter.next());
//      }


      assertTrue(set.test(100));
      assertFalse(set.test(99));
   }


   /**
      Tests setting a value.
    */
   @Test public void testOneElement()
   {      
      IntSet set = new ArraySet();
      set.set(100);
      assertEquals(100, set.min());
      assertEquals(100, set.max());
      assertEquals(1, set.size());
      assertTrue(set.test(100));
      assertFalse(set.test(99));
   }
   /**
      Tests setting and clearing a value.
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
      Tests that elements are added in insertion order.
    */
   @Test public void elementOrderInsert()
   {
      ArraySet set = new ArraySet();
      set.set(100);
      set.set(90);
      set.set(80);
      set.set(110);
      assertEquals(100, set.elements[0]);
      assertEquals(90, set.elements[1]);
      assertEquals(80, set.elements[2]);
      assertEquals(110, set.elements[3]);
   }
   /**
      Tests that a removed element is 
      replaced with the last one. (O(1) removal)
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
      IntSet set = new ArraySet();
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
      Tests that a set with 10 elements doesn't unnecessarily
      double in size.
    */
   @Test public void arraySize()
   {
      ArraySet set = new ArraySet();
      set.set(100);
      assertEquals(10, set.elements.length);
      for (int i = 1; i <= 10; i++)
         set.set(i * i);
      assertEquals(10, set.elements.length);
      set.set(121);
      assertEquals(20, set.elements.length);
   }
}
