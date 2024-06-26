package estg.ed.stack;

import estg.ed.array.DynamicArray;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.DynamicArrayContract;
import estg.ed.interfaces.StackADT;

/**
 * Stack implemented with array. Using already implemented dynamic array.
 *
 * @param <T> generic
 */
public class ArrayStack<T> implements StackADT<T> {

    /**
     * Dynamic array to store data.
     */
    DynamicArrayContract<T> array;

    /**
     * Instantiate the stack using a dynamic array.
     */
    public ArrayStack() {
        this.array = new DynamicArray<>();
    }

    /**
     * Adds one element to the top of this stack. Inserts on internal dynamic
     * array on next empty index.
     *
     * @param element element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        int top = this.array.size() - 1;

        this.array.add(element, top + 1);
    }

    /**
     * Removes and returns the top element from this stack. Removes from
     * internal dynamic array from last index.
     *
     * @return T element removed from the top of the stack
     * @throws estg.ed.exceptions.EmptyCollectionException stack is empty
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (this.array.size() == 0) {
            throw new EmptyCollectionException("Stack is empty!");
        }

        int top = this.array.size() - 1;

        return this.array.remove(top);
    }

    /**
     * Returns without removing the top element of this stack. Returns from
     * internal dynamic array from last index.
     *
     * @return T element on top of the stack
     * @throws estg.ed.exceptions.EmptyCollectionException stack is empty
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (this.array.size() == 0) {
            throw new EmptyCollectionException("Stack is empty!");
        }

        int top = this.array.size() - 1;

        return this.array.get(top);
    }

    /**
     * Returns true if this stack contains no elements. Returns result from
     * internal dynamic array.
     *
     * @return boolean whether or not this stack is empty
     */
    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    /**
     * Returns the number of elements in this stack. Returns result from
     * internal dynamic array.
     *
     * @return int number of elements in this stack
     */
    @Override
    public int size() {
        return this.array.size();
    }

    /**
     * Returns a string representation of this stack. Returns result from
     * internal dynamic array.
     *
     * @return String representation of this stack
     */
    @Override
    public String toString() {
        return this.array.toString();
    }
}
