import java.util.Arrays;

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
      for(int i = 0; i < elementCount; i++){
          if(elements[i] == n) return true;
      }
      return false;
    }

    /**
     * clear to clear a number from the array.
     * @param n is the value to be clear
     */

    public void clear(int n)
    {
        boolean found = true;
        for(int i = 0; i < elementCount && found; i++){
            if(elements[i] == n) {
                n = i;
                found = false;
            }
        }

        //print();

        if(!found) swap(n, elementCount - 1);

        --elementCount;

        //print();

        if (elements[elementCount] == min()) findMin();
        else if (elements[elementCount] == max()) findMAx();

        //print();

    }

    /**
     * using to debug the program.
     */

    public void print(){

        for(int i = 0; i < elementCount; i++){
            System.out.print(elements[i] + " ");
        }

        System.out.print(" Largest: " + largest + " Smallest: " + smallest        );
        System.out.println();

    }

    /**
     * finds the max value in the array.
     */

    private void findMAx(){

        largest = Integer.MIN_VALUE;

        for(int i = 0; i < elementCount; i++){
            if(elements[i] > largest) {
                largest = elements[i];
            }
        }

    }

    /**
     * finds the min value in the array.
     */

    private void findMin(){

        smallest = Integer.MAX_VALUE;

        for(int i = 0; i < elementCount; i++){
            if(elements[i] < smallest) {
                smallest = elements[i];
            }
        }

    }

    /**
     * swap two numbers.
     * @param x first value
     * @param y second value to be swap
     */

    private void swap(int x, int y){

        int temp = elements[x];
        elements[x] = elements[y];
        elements[y] = temp;
    }

    /**
     * adds a new value to the array.
     * @param n is la value
     */

    public void set(int n)
    {
        if(maxSize == elementCount) {

            maxSize = 2 * maxSize;
            elements = Arrays.copyOf(elements, maxSize);
        }

        elements[elementCount] = n;
        elementCount++;

        if (n > largest) largest = n;
        if (n < smallest) smallest = n;

    }

//    private void sort(int index){
//
//        if(index == 1) return;
//        else if(elements[index] < elements[index-1]){
//            swap(index, index -1);
//            sort(index-1);
//        }
//    }

    // Don't change any of these (but add javadoc)

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

    /**
     * constructor to initialize the variables.
     */

    ArraySet(){
        maxSize = 10;
        elementCount = 0;
        elements = new int[10];
    }

    /**
     * @param smallest is the smallest value in the array
     */

    // These are left package visible so they can be accessed in a unit test
    int smallest = Integer.MAX_VALUE;

    /**
     * @param largest is the largest number in the array
     */

    int largest = Integer.MIN_VALUE;

    /**
     * @param elements is the array
     */

    int[] elements;

    /**
     * @param elementCount is the number of elements in the array
     */

    int elementCount;

    /**
     * @param maxSize is the maximum size
     */

    private int maxSize;
}