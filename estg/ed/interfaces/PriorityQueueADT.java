package estg.ed.interfaces;

/**
 * Contract for Priority Queues. Uses double type for priority.
 *
 * @param <T> generic
 */
public interface PriorityQueueADT<T> extends BaseQueueADT<T> {

    /**
     * Adds one element to this priority queue. Uses double type for priority.
     *
     * @param element the element to be added to this queue
     * @param priority double value of priority
     */
    public void enqueue(T element, double priority);
}
