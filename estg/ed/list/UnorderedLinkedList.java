package estg.ed.list;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.interfaces.UnorderedListADT;
import estg.ed.nodes.DoubleLinearNode;

/**
 * Ordered list implemented with double linked elements.
 *
 * @param <T> generic
 */
public class UnorderedLinkedList<T> extends LinkedList<T> implements UnorderedListADT<T> {

    /**
     * Adds the specified element to the front of list.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void addToFront(T element) {
        DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);

        newNode.next = this.frontSentinel.next;
        newNode.previous = this.frontSentinel;

        this.frontSentinel.next.previous = newNode;
        this.frontSentinel.next = newNode;

        this.count++;
        this.modcount++;
    }

    /**
     * Adds the specified element to the rear of list.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void addToRear(T element) {
        DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);

        newNode.next = this.rearSentinel;
        newNode.previous = this.rearSentinel.previous;

        this.rearSentinel.previous.next = newNode;
        this.rearSentinel.previous = newNode;

        this.count++;
        this.modcount++;
    }

    /**
     * Adds the specified element after another element.
     *
     * @param element the element to be added to this list
     * @param target the target element
     * @throws estg.ed.exceptions.ElementNotFoundException target does not exist
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        //Find target
        DoubleLinearNode<T> current = this.frontSentinel.next;

        while (!current.equals(this.rearSentinel)) {
            //Is target
            if (current.data.equals(target)) {
                DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);

                newNode.next = current.next;
                newNode.previous = current;

                current.next.previous = newNode;
                current.next = newNode;

                this.count++;
                this.modcount++;

                return;
            }

            current = current.next;
        }

        throw new ElementNotFoundException("Element was not found!");
    }

}
