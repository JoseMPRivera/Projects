/**
 * emulates an array set.
 */

public interface IntSet {
    // Add javadoc

    /** test checks is a number is represented in the array.
     * @param n is the number
     * @return true if the value is in the array else returns false
     */

    boolean test(int n);

    /** adds a new value to the array.
     * @param n is la value
     */

    void set(int n);

    /** clear to clear a number from the array.
     * @param n is the value to be clear
     */

    void clear(int n);

    /** return the min value.
     * @return the lowest value int the array
     */
    int min();

    /** return the max value.
     * @return return an int
     */

    int max();

    /** return the size of the array.
     * @return the size
     */

    int size();
}