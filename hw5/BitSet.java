import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a bit set.
 */

public class BitSet implements IntSet
{

   /**
    * It test if the value is in the array.
    * @param n is the number
    * @return true if the value is in the array
    */

   public boolean test(int n)
   {
      if (elements == null || n < start || n >= start + 32 * elements.length)
         return false;
      int p = (n - start) / 32;
      int i = (n - start) % 32;
      return test(elements[p], i);
   }

   /**
    * inserts a new value.
    * @param n is la value
    */

   public void set(int n)
   {
      if (elements == null) 
      {
         elements = new int[10];
         start = n;
      }
      else if (n < start)
      {
         int intsNeeded = (start + 32 * elements.length - n) / 32 + 1;
         int[] newElements = new int[Math.max(intsNeeded, 2 * elements.length)];
         System.arraycopy(elements, 0, 
               newElements, newElements.length - elements.length, 
               elements.length);
         start -= 32 * (newElements.length - elements.length);
         elements = newElements;
      }
      else if (n >= start + 32 * elements.length)
      {
         int intsNeeded = (n - start) / 32 + 1; 
         elements = Arrays.copyOf(elements, 
               Math.max(intsNeeded, 2 * elements.length));         
      }
      int p = (n - start) / 32;
      int i = (n - start) % 32;
      if (!test(elements[p], i)) 
      {
         elementCount++;
         elements[p] = set(elements[p], i);      
      }
   }

   /**
    * it clear the bit.
    * @param n is the value to be clear
    */

   public void clear(int n)
   {
      if (elements != null && n >= start || n < start + 32 * elements.length)
      {
         int p = (n - start) / 32;
         int i = (n - start) % 32;
         if (test(elements[p], i)) 
         {
            elementCount--;
            elements[p] = clear(elements[p], i);
         }
      }
   }

   /**
    * return the min value.
    * @return the smallest number
    */

   public int min()
   {
      if (elements != null)
         for (int p = 0; p < elements.length; p++)
            for (int i = 0; i < 32; i++)
               if (test(elements[p], i)) return 32 * p + i + start;      
      return Integer.MAX_VALUE;
   }

   /**
    * return the max.
    * @return returns an int
    */

   public int max()
   {
      if (elements!= null)
         for (int p = elements.length - 1; p >= 0; p--)
            for (int i = 31; i >= 0; i--)
               if (test(elements[p], i)) return 32 * p + i + start;
      return Integer.MIN_VALUE;
   }

   /**
    * returns the size of the array.
    * @return the size
    */

   public int size()
   {
      return elementCount;
   }

   /**
    * checks if n is in the array.
    * @param n is the value to be checked
    * @param i is the index in the int
    * @return return true if the value is in the array
    */

   private static boolean test(int n, int i)
   {
      assert 0 <= i && i < 32;
      return (n & (1 << i)) != 0;
   }

   /**
    * adds n to the array
    * @param n is the number to be added
    * @param i is the index of n
    * @return return the new number
    */

   private static int set(int n, int i)
   {
      assert 0 <= i && i < 32;
      return n | (1 << i);
   }


   /**
    * clears the bits
    * @param n is the value to be clear
    * @param i is the index of n
    * @return return the new number
    */

   private static int clear(int n, int i)
   {
      assert 0 <= i && i < 32;
      return n & ~(1 << i);
   }

   int[] elements;
   int start;
   int elementCount;

   /**
    * Iterator implementation
    */

   private class BitSetIterator implements Iterator<Integer>
   {
      /**
       * return true if there is a next element
       * @return true or false
       */

      public boolean hasNext() { return iterElementCount < elementCount; }

      /**
       * gets the next element
       * @return the integer
       */

      public Integer next(){

         if(iterElementCount == elementCount) iterElementCount = 0;

         if(iteratorCount != elementCount){
            throw new ConcurrentModificationException();
         }

         if(!this.hasNext()){
            throw new NoSuchElementException();
         }

         int temp;

         for(int i = indexIteratorArray; (i < (elements.length -1)) && this.hasNext(); i++){

            temp = elements[i];

            for(int j = nextIndex; j < 31; j++){

               if((temp & (1 << j)) != 0){

                  ++iterElementCount;
                  indexIteratorArray = i;
                  nextIndex = j;

                  if(nextIndex > 30){

                     nextIndex = 0;
                     ++indexIteratorArray;
                  }
                  else{

                     ++nextIndex;
                  }

                  return j + (i*32) + start;

               }
            }
            nextIndex = 0;
         }
         return 0;
      }

      /**
       * removes the element in the bitset
       */

      public void remove(){

         if(iterElementCount < 1){
            throw new IllegalStateException();
         }

//         int temp = nextIndex + ((indexIteratorArray)*32) + start - 1;

         elements[indexIteratorArray] = clear(elements[indexIteratorArray], nextIndex-1);

         --elementCount;
         --iterElementCount;
         ++modCount;
         iteratorCount = elementCount;
      }
   }

   /**
    * constructor to an iterator
    * @return an iterator
    */

   public Iterator<Integer> iterator() {

      modCount = 0;
      indexIteratorArray = 0;
      nextIndex = 0;
      iteratorCount = elementCount;
      iterElementCount = 0;
      return new BitSetIterator();
   }

   /**
    * variables to keep track of the changes in the array and iterator
    */

   private int iterElementCount;
   private int nextIndex;
   private boolean afterNext;
   private int iteratorCount;
   private int indexIteratorArray;
   private int modCount;

}
