package estg.ed.tree.binary;

import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.exceptions.NotComparableException;
import estg.ed.interfaces.HeapADT;

/**
 * Heap implementation with circular dynamic array. Using already implemented
 * circular dynamic array.
 *
 * @param <T> generic
 */
public abstract class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {

    /**
     * Adds the specified object to this heap. Result depends on implementation
     * of abstract method heapifyUp().
     *
     * @param element the element to added to this head
     * @throws estg.ed.exceptions.NotComparableException element is not
     * comparable
     */
    @Override
    public void addElement(T element) throws NotComparableException {
        if (!(element instanceof Comparable)) {
            throw new NotComparableException("Element is not comparable!");
        }

        //Next position in array
        int position = this.array.size();

        //Add element to position
        this.array.add(element, position);

        //Heapify up an element
        this.heapifyUp(position);
    }

    /**
     * Heapify up an element.
     *
     * @param elementIndex index of element to heapifyUp
     */
    protected abstract void heapifyUp(int elementIndex);

    /**
     * Removes root element of heap.
     *
     * @return root element
     * @throws estg.ed.exceptions.EmptyCollectionException heap is empty
     */
    protected T removeRoot() throws EmptyCollectionException {
        if (this.array.isEmpty()) {
            throw new EmptyCollectionException("Heap is empty!");
        }

        //Save removed
        T removedElem = this.array.get(0);

        //Set last added element as root
        int lastIndex = this.array.size() - 1;
        T lastElem = this.array.get(lastIndex);
        this.array.change(lastElem, 0);

        //Remove last added position
        this.array.remove(lastIndex);

        //Heapify down root    
        this.heapifyDown(0);

        //Return removed element
        return removedElem;
    }

    /**
     * Heapify down an element.
     *
     * @param parentIndex index of element to heapifyDown
     */
    protected abstract void heapifyDown(int parentIndex);

    /**
     * Returns a reference to the element at root.
     *
     * @return a reference to the element at root.
     * @throws estg.ed.exceptions.EmptyCollectionException heap is empty
     */
    protected T findRoot() throws EmptyCollectionException {
        if (this.array.isEmpty()) {
            throw new EmptyCollectionException("Heap is empty!");
        }

        return this.array.get(0);
    }

    /**
     * Get parent index
     *
     * @param index index of child element
     * @return index of parent element
     */
    protected int getParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Get left child index
     *
     * @param index index of parent element
     * @return index of left child
     */
    protected int getLeftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * Get right child index
     *
     * @param index index of parent element
     * @return index of right child
     */
    protected int getRightChild(int index) {
        return index * 2 + 2;
    }
}
