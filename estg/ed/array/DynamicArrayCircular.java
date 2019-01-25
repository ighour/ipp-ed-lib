package estg.ed.array;

import estg.ed.interfaces.DynamicArrayContract;

/**
 * Implements a circular dynamic array which expands when needed. Also,
 * reorganize itself when an element is removed from the middle.
 *
 * @param <T> generic
 */
public class DynamicArrayCircular<T> implements DynamicArrayContract<T> {

    /**
     * Array default size.
     */
    private static final int DEFAULT_SIZE = 20;

    /**
     * Array to store data.
     */
    T[] collection;

    /**
     * First element on array
     */
    private int front;

    /**
     * First empty position on array
     */
    private int rear;

    /**
     * Generates a circular dynamic array with default size.
     */
    public DynamicArrayCircular() {
        this(DEFAULT_SIZE);
    }

    /**
     * Generates a dynamic array with desired size.
     *
     * @param size integer for initial size of array
     */
    public DynamicArrayCircular(int size) {
        this.collection = (T[]) new Object[size];
        this.front = 0;
        this.rear = 0;
    }

    /**
     * Add an element to desired index. Index need to be between 0 and array
     * size. Increases array size if needed. Push elements in array if is not
     * the rear index.
     *
     * @param element element to add
     * @param virtualIndex external index
     * @throws IndexOutOfBoundsException virtualIndex is out of bounds of array
     */
    @Override
    public void add(T element, int virtualIndex) throws IndexOutOfBoundsException {
        //Check if virtualIndex is valid (0 to new position at end)
        if (virtualIndex < 0 || virtualIndex > this.size()) {
            throw new IndexOutOfBoundsException("Index " + virtualIndex + " is out of bounds!");
        }

        //Get internal index
        int index = this.convertIndex(virtualIndex);

        //Push elements after desired index
        int currentIndex = this.rear;
        int previousIndex;

        while (currentIndex != index) {
            //Push previous element in array
            previousIndex = this.getPreviousIndex(currentIndex);
            this.collection[currentIndex] = this.collection[previousIndex];

            currentIndex = previousIndex;
        }

        //Store new element in desired index
        this.collection[index] = element;

        //Increment rear
        this.incrementRear();
    }

    /**
     * Remove an element from desired index. Index need to be between 0 and
     * array size. Pull elements in array if is not the last index.
     *
     * @param virtualIndex external index
     * @return element on external index position
     * @throws IndexOutOfBoundsException virtualIndex is invalid
     */
    @Override
    public T remove(int virtualIndex) throws IndexOutOfBoundsException {
        //Check if virtualIndex is valid (0 to last position at end)
        if (virtualIndex < 0 || virtualIndex > this.size() - 1) {
            throw new IndexOutOfBoundsException("Index " + virtualIndex + " is out of bounds!");
        }

        //Get internal index
        int index = this.convertIndex(virtualIndex);

        //Retrieve element
        T element = this.collection[index];

        //Pull elements after index
        int currentIndex = index;
        int nextIndex;

        int lastIndex = this.getPreviousIndex(this.rear);

        while (currentIndex != lastIndex) {
            //Pull next element in array
            nextIndex = this.getNextIndex(currentIndex);
            this.collection[currentIndex] = this.collection[nextIndex];

            currentIndex = nextIndex;
        }

        //Decrement rear
        this.decrementRear();

        return element;
    }

    /**
     * Change content of desired index. Index need to be between 0 and last
     * position.
     *
     * @param element new content
     * @param virtualIndex array position to put new content
     * @throws IndexOutOfBoundsException virtualIndex is invalid
     */
    @Override
    public void change(T element, int virtualIndex) throws IndexOutOfBoundsException {
        //Check if virtualIndex is valid (0 to last position at end)
        if (virtualIndex < 0 || virtualIndex > this.size() - 1) {
            throw new IndexOutOfBoundsException("Index " + virtualIndex + " is out of bounds!");
        }

        //Get internal index
        int index = this.convertIndex(virtualIndex);

        //Change content
        this.collection[index] = element;
    }

    /**
     * Get an element from desired index. Index need to be between 0 and last
     * position.
     *
     * @param virtualIndex external index
     * @return element in array position
     * @throws IndexOutOfBoundsException virtualIndex is invalid
     */
    @Override
    public T get(int virtualIndex) throws IndexOutOfBoundsException {
        //Check if virtualIndex is valid (0 to last position at end)
        if (virtualIndex < 0 || virtualIndex > this.size() - 1) {
            throw new IndexOutOfBoundsException("Index " + virtualIndex + " is out of bounds!");
        }

        //Get internal index
        int index = this.convertIndex(virtualIndex);

        //Retrieve element
        return this.collection[index];
    }

    /**
     * Get length of array. Only counts valid indexes between front and rear-1.
     *
     * @return integer size of array
     */
    @Override
    public int size() {
        return (this.rear - this.front + this.collection.length) % this.collection.length;
    }

    /**
     * Check if array is empty. Only counts valid indexes between front and
     * rear-1.
     *
     * @return true if array is empty
     */
    @Override
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    /**
     * Returns the string representation of the dynamic array.
     *
     * @return a string representation of the dynamic array
     */
    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();

        stb.append("[");

        int currentIndex = this.front;

        while (currentIndex != this.rear) {
            stb.append("[").append(this.collection[currentIndex].toString()).append("]");

            currentIndex = this.getNextIndex(currentIndex);

            if (currentIndex != this.rear) {
                stb.append(",");
            }
        }

        stb.append("]");

        return stb.toString();
    }

    private void checkNeedToIncrease() {
        //Need to Increase
        if (this.getNextIndex(this.rear) == this.front) {
            T[] newCollection = (T[]) new Object[this.collection.length * 2];

            System.arraycopy(this.collection, 0, newCollection, 0, this.collection.length);

            this.collection = newCollection;
        }
    }

    private int getPreviousIndex(int index) {
        return (index - 1 + this.front) % this.collection.length;
    }

    private int getNextIndex(int index) {
        return (index + 1 + this.front) % this.collection.length;
    }

    private void incrementRear() {
        //Check if need to increase array
        this.checkNeedToIncrease();

        this.rear = this.getNextIndex(this.rear);
    }

    private void decrementRear() {
        this.rear = this.getPreviousIndex(this.rear);
    }

    private int convertIndex(int index) {
        return (index + this.front) % this.collection.length;
    }
}
