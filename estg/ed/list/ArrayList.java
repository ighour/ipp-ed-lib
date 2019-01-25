package estg.ed.list;

import estg.ed.array.DynamicArrayCircular;
import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.DynamicArrayContract;
import estg.ed.interfaces.ListADT;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * List implemented with circular dynamic array. Using already implemented
 * circular dynamic array.
 *
 * @param <T> generic
 */
public abstract class ArrayList<T> implements ListADT<T> {

    /**
     * Circular dynamic array to store data.
     */
    protected DynamicArrayContract<T> array;

    /**
     * Count modifications in list.
     */
    protected int modcount;

    /**
     * Instantiate the list using a circular dynamic array.
     */
    public ArrayList() {
        this.array = new DynamicArrayCircular<>();
        this.modcount = 0;
    }

    /**
     * Removes and returns the first element from this list. Removes from
     * internal circular dynamic array from first index.
     *
     * @return the first element from this list
     * @throws estg.ed.exceptions.EmptyCollectionException list is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (this.array.size() == 0) {
            throw new EmptyCollectionException("List is empty!");
        }

        this.modcount++;

        return this.array.remove(0);
    }

    /**
     * Removes and returns the last element from this list. Removes from
     * internal circular dynamic array from last index.
     *
     * @return the last element from this list
     * @throws estg.ed.exceptions.EmptyCollectionException list is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (this.array.size() == 0) {
            throw new EmptyCollectionException("List is empty!");
        }

        this.modcount++;

        return this.array.remove(this.array.size() - 1);
    }

    /**
     * Removes and returns the specified element from this list. Removes from
     * internal circular dynamic array from desired index.
     *
     * @param element the element to be removed from the list
     * @return the last element of list
     * @throws estg.ed.exceptions.EmptyCollectionException list is empty
     * @throws estg.ed.exceptions.ElementNotFoundException element was not found
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (this.array.size() == 0) {
            throw new EmptyCollectionException("List is empty!");
        }

        //Search for element
        for (int i = 0; i < this.array.size(); i++) {
            T current = this.array.get(i);

            //Element was found
            if (current.equals(element)) {
                return this.array.remove(i);
            }
        }

        this.modcount++;

        throw new ElementNotFoundException("Element was not found!");
    }

    /**
     * Returns a reference to the first element in this list. Returns from
     * internal circular dynamic array first index.
     *
     * @return a reference to the first element in this list
     * @throws estg.ed.exceptions.EmptyCollectionException list is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (this.array.size() == 0) {
            throw new EmptyCollectionException("List is empty!");
        }

        return this.array.get(0);
    }

    /**
     * Returns a reference to the last element in this list. Returns from
     * internal circular dynamic array last index.
     *
     * @return a reference to the last element in this list
     * @throws estg.ed.exceptions.EmptyCollectionException list is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (this.array.size() == 0) {
            throw new EmptyCollectionException("List is empty!");
        }

        return this.array.get(this.array.size() - 1);
    }

    /**
     * Returns true if this list contains the specified target element. Returns
     * from internal circular dynamic array desired index.
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     */
    @Override
    public boolean contains(T target) {
        //Search for element
        for (int i = 0; i < this.array.size(); i++) {
            T current = this.array.get(i);

            //Target was found
            if (current.equals(target)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if this list contains no elements. Returns result from
     * internal circular dynamic array.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    /**
     * Returns the number of elements in this list. Returns result from internal
     * circular dynamic array.
     *
     * @return the integer representation of number of elements in this list
     */
    @Override
    public int size() {
        return this.array.size();
    }

    /**
     * Returns an iterator for the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    /**
     * Returns a string representation of this list. Returns result from
     * internal circular dynamic array.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        return this.array.toString();
    }

    /**
     * Inner iterator for list.
     */
    class InnerIterator implements Iterator<T> {

        /**
         * Points to first element of list.
         */
        private int pointer;

        /**
         * Expected modcount at operations.
         */
        private final int expectedModcount;

        /**
         * Reference to last next index.
         */
        private int lastNext;

        /**
         * Flag to check if is ok to remove.
         */
        private boolean okToRemove;

        /**
         * Instantiates iterator. Points to index 0. Store expectedModcount same
         * as list. Set lastNext as -1 (there is no lastNext). Set not ok to
         * remove.
         */
        public InnerIterator() {
            this.pointer = 0;
            this.expectedModcount = modcount;
            this.lastNext = -1;
            this.okToRemove = false;
        }

        /**
         * Check if there is next.
         *
         * @throws ConcurrentModificationException list has changed
         * @return true if there is next element in iterator
         */
        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            //List has changed
            if (this.expectedModcount != modcount) {
                throw new ConcurrentModificationException("List has changed while iterating.");
            }

            //List is empty
            if (isEmpty()) {
                return false;
            }

            //Check if there is next
            try {
                array.get(this.pointer);
            } catch (IndexOutOfBoundsException e) {
                //There is no next
                return false;
            }

            return true;
        }

        /**
         * Get next element.
         *
         * @throws ConcurrentModificationException list has changed
         * @throws NoSuchElementException there is no next element
         * @return next element in iterator
         */
        @Override
        public T next() throws ConcurrentModificationException, NoSuchElementException {
            //List has changed
            if (this.expectedModcount != modcount) {
                throw new ConcurrentModificationException("List has changed while iterating.");
            }

            //Check if there is next
            if (!this.hasNext()) {
                throw new NoSuchElementException("There is no more elements.");
            }

            //Set pointer as last next
            this.lastNext = this.pointer;

            //Increment pointer
            this.pointer++;

            //Set as removable (next is set)
            this.okToRemove = true;

            //Return next
            return array.get(this.lastNext);
        }

        /**
         * Remove element set as next. After remove, pointer will auto update to
         * next element at internal circular dynamic array
         *
         * @throws IllegalStateException next element was not loaded yet
         */
        @Override
        public void remove() throws IllegalStateException {
            //Next was not set
            if (this.okToRemove == false) {
                throw new IllegalStateException("Can only remove elements loaded by next() method.");
            }

            //Remove item
            array.remove(this.pointer);

            //Block remove
            this.okToRemove = false;
        }
    }
}
