import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * emulates an array ser.
 */

public class ArraySet implements IntSet
{
   /**
    * test checks is a number is represented in the array.
    * @param n is the number
    * @return true if the value is in the array else returns false
    */

   public boolean test(int n)
   {
      if (n < smallest || n > largest) return false;
      for (int i = 0; i < elementCount; i++)
         if (elements[i] == n) return true;
      return false;
   }


   /**
    * clear to clear a number from the array.
    * @param n is the value to be clear
    */
   
   public void clear(int n) 
   {
      for (int i = 0; i < elementCount; i++)
         if (elements[i] == n) 
         {
            elements[i] = elements[elementCount - 1];
            elementCount--;
            if (n == smallest)
            {
               smallest = Integer.MAX_VALUE;
               for (int k = 0; k < elementCount; k++) 
                  smallest = Math.min(elements[k], smallest);
            }
            if (n == largest)
            {
               largest = Integer.MIN_VALUE;
               for (int k = 0; k < elementCount; k++) 
                  largest = Math.max(elements[k], largest);
            }
         }
   }

   /**
    * adds a new value to the array.
    * @param n is la value
    */
   
   public void set(int n)
   {
      if (!test(n))
      {
         if (elements == null)
         {
            elements = new int[10];
            current = 0;
         }
         else if (elements.length == elementCount)
         {
            elements = Arrays.copyOf(elements, 2 * elements.length);
         }
         elements[elementCount] = n;
         smallest = Math.min(smallest, n);
         largest = Math.max(largest, n);
         elementCount++;
      }
   }

   /**
    * return the lowest value.
    * @return return an int
    */
   
   public int min()
   {
      return smallest;
   }

   /**
    * return the max vale.
    * @return return the max
    */

   public int max()
   {
      return largest;
   }

   /**
    * return the size of the array.
    * @return return the size
    */

   public int size()
   {
      return elementCount;
   }
   
   int smallest = Integer.MAX_VALUE;
   int largest = Integer.MIN_VALUE;
   int[] elements;
   int elementCount;
   int current;
   int iteratorCount;

   /**
    * Iterator implementation
    */

   class ArraySetIterator implements Iterator<Integer>
   {
      /**
       * return true if there is a next element
       * @return true or false
       */

      public boolean hasNext() {

         return (current < elementCount);
      }

      /**
       * gets the next element
       * @return the integer
       */

      public Integer next(){


         if(current == elementCount) current = 0;

         if(iteratorCount != elementCount){
            throw new ConcurrentModificationException();
         }

         if(!this.hasNext()){
            throw new NoSuchElementException();
         }

         return elements[current++];
      }

      /**
       * removes the element in the bitset
       */

      public void remove(){

         if(current < 1){
            throw new IllegalStateException();
         }

         elements[current - 1] = 0;

         int temp = current;

         for(int i = temp - 1; i < elementCount - 1; i++ ){

            elements[i] = elements[i + 1];
         }

         --elementCount;
         --current;
         iteratorCount = elementCount;
      }

   }

   /**
    * constructor to an iterator
    * @return an iterator
    */

   public Iterator<Integer> iterator()
   {
      iteratorCount = elementCount;
      return new ArraySetIterator();
   }
}
