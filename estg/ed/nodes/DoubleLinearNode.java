package estg.ed.nodes;

/**
 * Node with reference to next and previous nodes.
 *
 * @param <T> generic
 */
public class DoubleLinearNode<T> {

    /**
     * Node data.
     */
    public T data;

    /**
     * Next node reference.
     */
    public DoubleLinearNode<T> next;

    /**
     * Previous node reference.
     */
    public DoubleLinearNode<T> previous;

    /**
     * Create a node without data.
     */
    public DoubleLinearNode() {
    }

    /**
     * Create a node with data.
     *
     * @param data content of node
     */
    public DoubleLinearNode(T data) {
        this.data = data;
    }
}
