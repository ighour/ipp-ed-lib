package estg.ed.list;

import estg.ed.exceptions.NotComparableException;
import estg.ed.interfaces.OrderedListADT;

/**
 * Ordered list implemented with circular dynamic array.
 *
 * @param <T> generic
 */
public class OrderedArrayList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Adds the specified element to this list at the proper location. Element
     * needs to implement Comparable interface. Add to internal circular dynamic
     * array on proper location.
     *
     * @param element the element to be added to this list
     * @throws estg.ed.exceptions.NotComparableException element is not
     * comparable
     */
    @Override
    public void add(T element) throws NotComparableException {
        //Element is not comparable
        if (!(element instanceof Comparable)) {
            throw new NotComparableException("Element is not comparable.");
        }

        //Find position for element
        int index = 0;
        Comparable comparing = (Comparable) element;

        for (int i = 0; i < array.size(); i++) {
            Comparable compared = (Comparable) array.get(i);

            //Is greater
            if (comparing.compareTo(compared) > 0) {
                index = i + 1;
            } //Is equal or smaller
            else {
                break;
            }
        }

        //Insert in list
        array.add(element, index);

        this.modcount++;
    }

}
