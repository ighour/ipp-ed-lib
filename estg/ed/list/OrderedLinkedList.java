package estg.ed.list;

import estg.ed.exceptions.NotComparableException;
import estg.ed.interfaces.OrderedListADT;
import estg.ed.nodes.DoubleLinearNode;

/**
 * Ordered list implemented with double linked elements.
 *
 * @param <T> generic
 */
public class OrderedLinkedList<T> extends LinkedList<T> implements OrderedListADT<T> {

    /**
     * Adds the specified element to this list at the proper location. Element
     * needs to implement Comparable interface.
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
        DoubleLinearNode<T> current = this.frontSentinel.next;
        Comparable comparing = (Comparable) element;

        while (!current.equals(this.rearSentinel)) {
            Comparable compared = (Comparable) current.data;

            //Is greater
            if (comparing.compareTo(compared) > 0) {
                current = current.next;
            } //Is equal or smaller
            else {
                break;
            }
        }

        //Insert in list
        DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);

        newNode.next = current;
        newNode.previous = current.previous;

        current.previous.next = newNode;
        current.previous = newNode;

        this.count++;
        this.modcount++;
    }

}
