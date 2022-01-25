import java.util.Arrays;

/**
 * Class: ArrayList
 * File: ZArrayList.java
 * Author: Zack Sai
 *
 * This file demonstrates the Array List data structure
 * Best Case Efficiency:
 *      get: O(1) – retrieves at one location (currentSize)
 *      set: O(1) – stores at one location (currentSize)
 *      remove: O(1) – removes from one location (currentSize)
 *
 * Worst Case Efficiency:
 *      get: O(n) – retrieves at position passed (n)
 *      set: O(1) – stores at position passed (n)
 *      remove: O(1) – removes from position passed (n)
 *
 * Reallocate() method is used to expand the array when it is full and an
 * element must be added
 *
 * @param <E> generic data type of the list created
 */
public class ZArrayList<E> {

    // PROPERTIES: capacity, underlying array, current size & capacity

    private static final int INITIAL_CAPACITY = 10;
    private E[] entries;
    private int currentSize = 0;
    private int currentCapacity = 0;

    // BEHAVIORS:
    /**
     * Constructor method initializes current capacity to initial value
     * Ensure proper data type is stored using generic type cast
     */
    public ZArrayList() {

        // Initialize capacity to initial capacity and cast data type
        currentCapacity = INITIAL_CAPACITY;
        entries = (E[]) new Object[currentCapacity];
    }

    /**
     * 1 param add method adds an entry at the end of the list
     *
     * @param anEntry is the entry to be added
     * @return true upon successful add, never false
     */
    public boolean add(E anEntry) {

        // Handle case of full array
        if (currentSize == currentCapacity) {
            reallocate();
        }

        // Add entry to list at the latest position and update position
        entries[currentSize] = anEntry;
        currentSize++;
        return true;
    }

    /**
     * 3-param add method adds an entry at the index indicated
     *
     * @param index   is the position to add the entry
     * @param anEntry is the entry to be added
     */
    public void add(int index, E anEntry) {

        // Handle out-of-bounds index
        if (index < 0 || index > currentSize) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        // Handle case of full array
        if (currentSize == currentCapacity) {
            reallocate();
        }

        // Iterate backwards through array until you reach index
        for (int i = currentSize; i > index; i--) {
            // Shift each element to the previous position
            entries[i] = entries[i - 1];
        }

        // Insert the item at the index
        entries[index] = anEntry;
        currentSize++;
    }

    /**
     * get method iterates through list and returns entry at given index
     *
     * @param index position of entry to be found
     * @return the entry at the index
     */
    public E get(int index) {

        // Handle out-of-bounds index
        if (index < 0 || index > currentSize) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return entries[index];  // Return the value
    }

    /**
     * @param index is the position of the value to be removed
     * @return the value removed
     */
    public E remove(int index) {
        // Handle out-of-bounds index
        if (index < 0 || index > currentSize) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        // Store the value being removed to return it later
        E returnValue = entries[index];

        // Iterate through the array from the position just after the item to be removed
        for (int i = index + 1; i < currentSize; i++) {

            // Change its index to the one right before it
            entries[i - 1] = entries[i];
        }

        currentSize--;                 // Update size
        return returnValue;     // return the value stored
    }

    /**
     * Reallocate method copies array into a new one of double the size
     * Use case: when array is full, reallocate()
     */
    private void reallocate() {

        // Double the capacity
        currentCapacity = 2 * currentCapacity;

        // Copy the array into a new array with updated capacity
        entries = Arrays.copyOf(entries, currentCapacity);

    }


}