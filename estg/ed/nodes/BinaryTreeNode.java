package estg.ed.nodes;

/**
 * Node with reference to left and right children.
 *
 * @param <T> generic
 */
public class BinaryTreeNode<T> {

    /**
     * Node data.
     */
    public T data;

    /**
     * Left child node reference.
     */
    public BinaryTreeNode<T> left;

    /**
     * Right child node reference.
     */
    public BinaryTreeNode<T> right;

    /**
     * Create a node with data.
     *
     * @param data content of node
     */
    public BinaryTreeNode(T data) {
        this.data = data;
    }

    /**
     * Returns the number of children nodes in current tree. Using recursion.
     *
     * @return integer count of all children
     */
    public int numChildren() {
        int children = 0;

        if (left != null) {
            children = 1 + left.numChildren();
        }

        if (right != null) {
            children = 1 + right.numChildren();
        }

        return children;
    }
}
