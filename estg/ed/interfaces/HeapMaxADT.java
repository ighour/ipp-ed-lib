package estg.ed.interfaces;

import estg.ed.exceptions.EmptyCollectionException;

/**
 * Contract for max heaps.
 *
 * @param <T> generic
 */
public interface HeapMaxADT<T> extends HeapADT<T> {

    /**
     * Removes element with the highest value from this heap.
     *
     * @return the element with the highest value from this heap
     * @throws estg.ed.exceptions.EmptyCollectionException if heap is empty
     */
    public T removeMax() throws EmptyCollectionException;

    /**
     * Returns a reference to the element with the highest value in this heap.
     *
     * @return a reference to the element with the highest value in this heap
     * @throws estg.ed.exceptions.EmptyCollectionException if heap is empty
     */
    public T findMax() throws EmptyCollectionException;
}
