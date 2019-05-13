import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * This class implements a bit set.
 */

public class BitSet implements IntSet
{
    /**
     * Contructor to the class.
     */

    BitSet(){
        elementCount = 0;
        elements = new int[10];
        fillWithZero(elements);
    }

    /**
     * fills the array with zeros.
     * @param array is the array
     */

    private void fillWithZero(int[] array){

        for(int i = 0; i < array.length; i++){
            array[i] = 0;
        }
    }

    /**
     * It test if the value is in the array.
     * @param n is the number
     * @return true if the value is in the array
     */

    public boolean test(int n) {

        int temp = 0;
        int index = n - start;

        index = abs(index / 32);

        temp = n - start - (index * 32);

        if(temp < 0) return false;
        if(temp > elements.length - 1) return false;

        return test(elements[index], temp);

    }

    /**
     * inserts a new value.
     * @param n is la value
     */

    public void set(int n)
    {
        if(start == 0) start = n;
        if(start > n) setNewStart(n);

        int temp = 0;
        int index = n - start;

        index = abs(index / 32);

        if(index > elements.length){

            int maxSize = 2 * elements.length;
            elements = Arrays.copyOf(elements, maxSize);
        }

        temp = n - start - (index * 32);

        elements[index] = set(elements[index], temp);

        if (n > largest) largest = n;
        if (n < smallest) smallest = n;

        ++elementCount;

    }

    /**
     * creates a new array with a new value for the smallest element in the array.
     * @param n is the value to be added
     */

    private void setNewStart(int n){

        int[] tempArray = new int[2 * elements.length];
        int tempStart = n;
        int temp = 0;
        int index;
        int number;

        fillWithZero(tempArray);

        tempArray[0] = 1;
        ++elementCount;

        for(int i = 0; i < elements.length; i++){

            for(int j = 0; j < 32; j++){

                if((elements[i] & (1 << j)) != 0){

                    number = i * 32 + j + start;

                    index = number - tempStart;
                    index = abs(index / 32);

                    temp = number - tempStart - (index * 32);

                    tempArray[index] = set(tempArray[index], temp);

                }
            }
        }

        elements = new int[2 * elements.length];
        start = n;
        smallest = n;
    }

    /**
     * it clear the bit.
     * @param n is the value to be clear
     */

    public void clear(int n)
    {
        int temp = 0;
        int index = n - start;

        index = abs(index / 32);

        temp = n - start - (index * 32);

        elements[index] = clear(elements[index], temp);
        --elementCount;


    }

    /**
     * return the min value.
     * @return the smallest number
     */

    public int min()
    {
      return smallest;
    }

    /**
     * return the max.
     * @return returns an int
     */

    public int max()
    {
      return largest;
    }

    // Don't change any of these (but add javadoc)

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

    // These are left package visible so they can be accessed in a unit test

    int[] elements;
    int start;
    int elementCount;
    int smallest = Integer.MAX_VALUE;
    int largest = Integer.MIN_VALUE;
}