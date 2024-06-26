package estg.ed.interfaces;

import estg.ed.exceptions.ElementNotFoundException;

/**
 * Contract for unordered list.
 *
 * @param <T> generic
 */
public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specified element to the front of list
     *
     * @param element the element to be added to this list
     */
    public void addToFront(T element);

    /**
     * Adds the specified element to the rear of list
     *
     * @param element the element to be added to this list
     */
    public void addToRear(T element);

    /**
     * Adds the specified element after another element
     *
     * @param element the element to be added to this list
     * @param target the target element
     * @throws estg.ed.exceptions.ElementNotFoundException element was not found
     */
    public void addAfter(T element, T target) throws ElementNotFoundException;
}
