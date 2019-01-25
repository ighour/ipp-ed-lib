package estg.ed.interfaces;

/**
 * Contract for Queues.
 *
 * @param <T> generic
 */
public interface QueueADT<T> extends BaseQueueADT<T> {

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    public void enqueue(T element);
}
