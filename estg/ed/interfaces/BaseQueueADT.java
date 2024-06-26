package estg.ed.interfaces;

import estg.ed.exceptions.EmptyCollectionException;

/**
 * Contract for queue base. Has all operations, except enqueue(), which depends
 * if is a common queue or priority queue.
 *
 * @param <T> generic
 */
public interface BaseQueueADT<T> {

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws estg.ed.exceptions.EmptyCollectionException queue is empty
     */
    public T dequeue() throws EmptyCollectionException;

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in this queue
     * @throws estg.ed.exceptions.EmptyCollectionException queue is empty
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns true if this queue contains no elements.
     *
     * @return boolean whether or not this queue is empty
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this queue.
     *
     * @return int number of elements in this queue
     */
    public int size();

    /**
     * Returns a string representation of this queue.
     *
     * @return String representation of this queue
     */
    @Override
    public String toString();
}
