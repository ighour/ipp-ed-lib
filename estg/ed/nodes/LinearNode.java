package estg.ed.nodes;

/**
 * Node with reference to next node.
 *
 * @param <T> generic
 */
public class LinearNode<T> {

    /**
     * Node data.
     */
    public T data;

    /**
     * Next node reference.
     */
    public LinearNode<T> next;

    /**
     * Create a node without data.
     */
    public LinearNode() {
    }

    /**
     * Create a node with data.
     *
     * @param data content of node
     */
    public LinearNode(T data) {
        this.data = data;
    }
}
