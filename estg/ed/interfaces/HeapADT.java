package estg.ed.interfaces;

import estg.ed.exceptions.NotComparableException;

/**
 * Contract for heaps.
 *
 * @param <T> generic
 */
public interface HeapADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified object to this heap.
     *
     * @param element the element to added to this head
     * @throws estg.ed.exceptions.NotComparableException if element is not
     * comparable
     */
    public void addElement(T element) throws NotComparableException;
}
