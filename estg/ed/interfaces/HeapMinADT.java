package estg.ed.interfaces;

import estg.ed.exceptions.EmptyCollectionException;

/**
 * Contract for min heaps.
 *
 * @param <T> generic
 */
public interface HeapMinADT<T> extends HeapADT<T> {

    /**
     * Removes element with the lowest value from this heap.
     *
     * @return the element with the lowest value from this heap
     * @throws estg.ed.exceptions.EmptyCollectionException heap is empty
     */
    public T removeMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the element with the lowest value in this heap.
     *
     * @return a reference to the element with the lowest value in this heap
     * @throws estg.ed.exceptions.EmptyCollectionException heap is empty
     */
    public T findMin() throws EmptyCollectionException;
}
