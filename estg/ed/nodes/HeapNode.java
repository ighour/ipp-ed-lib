package estg.ed.nodes;

/**
 * Node with reference to left child, right child and parent
 *
 * @param <T> generic
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    /**
     * Parent node reference.
     */
    public HeapNode<T> parent;

    /**
     * Create a node with data.
     *
     * @param data content of node
     */
    public HeapNode(T data) {
        super(data);
    }
}
