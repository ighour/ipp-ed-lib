package estg.ed.list;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.interfaces.UnorderedListADT;

/**
 * Unordered list implemented with circular dynamic array
 *
 * @param <T> generic
 */
public class UnorderedArrayList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Adds the specified element to the front of list. Add to internal circular
     * dynamic array on front.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void addToFront(T element) {
        array.add(element, 0);
        this.modcount++;
    }

    /**
     * Adds the specified element to the rear of list. Add to internal circular
     * dynamic array on rear.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void addToRear(T element) {
        array.add(element, array.size());
        this.modcount++;
    }

    /**
     * Adds the specified element after another element. Add to internal
     * circular dynamic after a target.
     *
     * @param element the element to be added to this list
     * @param target the target element
     * @throws estg.ed.exceptions.ElementNotFoundException target does not exist
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        //Search target
        for (int i = 0; i < array.size(); i++) {
            T current = array.get(i);

            //Found target
            if (current.equals(target)) {
                //Add after target
                array.add(element, i + 1);
                this.modcount++;
                return;
            }
        }

        throw new ElementNotFoundException("Element was not found!");
    }

}
