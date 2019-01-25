package estg.ed.tree.binary;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.exceptions.NotComparableException;
import estg.ed.interfaces.BinarySearchTreeADT;
import estg.ed.nodes.BinaryTreeNode;

/**
 * Binary search tree implementation with linked nodes.
 *
 * @param <T> generic
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Instantiates a binary search tree without root.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Instantiates a binary search tree with root.
     *
     * @param root first element on tree
     */
    public LinkedBinarySearchTree(T root) {
        super(root);
    }

    /**
     * Adds the specified object to the binary search tree in the appropriate
     * position according to its key value. Note that equal elements are added
     * to the right.
     *
     * @param element the element to be added to the binary search tree
     * @throws estg.ed.exceptions.NotComparableException element is not
     * comparable
     */
    @Override
    public void addElement(T element) throws NotComparableException {
        if (!(element instanceof Comparable)) {
            throw new NotComparableException("Element is not comparable!");
        }

        BinaryTreeNode<T> newNode = new BinaryTreeNode<>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;

        //Tree is empty
        if (this.isEmpty()) {
            this.root = newNode;
        } //Tree is not empty
        else {
            //Find new element position in tree
            BinaryTreeNode<T> current = this.root;

            //Stops when find a position
            while (true) {
                //Is smaller then current element
                if (comparableElement.compareTo(current.data) < 0) {
                    BinaryTreeNode<T> leftNode = current.left;

                    //There is no left child
                    if (leftNode == null) {
                        current.left = newNode;
                        break;
                    } //Proceed in left child subtree
                    else {
                        current = leftNode;
                    }
                } //Is greater or equal to current element
                else {
                    BinaryTreeNode<T> rightNode = current.right;

                    //There is no right child
                    if (rightNode == null) {
                        current.right = newNode;
                        break;
                    } //Proceed in right child subtree
                    else {
                        current = rightNode;
                    }
                }
            }
        }

        //Increment count
        this.count++;
    }

    /**
     * Removes the first element that matches the specified target element from
     * the binary search tree and returns a reference to it. Replaces removed
     * node.
     *
     * @param targetElement the element to be removed from this tree
     * @return the element removed from this tree
     * @throws estg.ed.exceptions.ElementNotFoundException specified target
     * element is not found
     * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
     * @throws estg.ed.exceptions.NotComparableException target element is not
     * comparable
     */
    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException, NotComparableException {
        //Tree is empty
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Binary search tree is empty!");
        }

        if (!(targetElement instanceof Comparable)) {
            throw new NotComparableException("Target element is not comparable!");
        }

        //Find element
        BinaryTreeNode<T> currentNode = this.root;
        BinaryTreeNode<T> parentNode = currentNode;

        Comparable targetComp = (Comparable) targetElement;

        while (!targetComp.equals(currentNode.data)) {
            parentNode = currentNode;

            //Is smaller then current node
            if (targetComp.compareTo(currentNode.data) < 0) //Search in left subtree
            {
                currentNode = currentNode.left;
            } //Is greater or equal to current node
            else //Search in right subtree
            {
                currentNode = currentNode.right;
            }

            //New current node is null
            //There is no more node to search
            if (currentNode == null) {
                throw new ElementNotFoundException("Element not found!");
            }
        }

        //Save result
        T result = currentNode.data;

        //Is root node
        if (currentNode.equals(this.root)) //Replace root node
        {
            this.root = this.replacement(currentNode);
        } //Is left child of parentNode
        else if (currentNode.equals(parentNode.left)) //Replace parentNode left child
        {
            parentNode.left = this.replacement(currentNode);
        } //Is right child of parentNode
        else //Replace parentNode right child
        {
            parentNode.right = this.replacement(currentNode);
        }

        return result;
    }

    /**
     * Returns a reference to a node that will replace the one specified for
     * removal. In the case where the removed node has two children, the inorder
     * successor is used as its replacement.
     *
     * @param baseNode node to be replaced
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> baseNode) {
        BinaryTreeNode<T> left = baseNode.left;
        BinaryTreeNode<T> right = baseNode.right;

        //Is leaf
        if (left == null && right == null) {
            return null;
        } //Has only left child
        else if (left != null && right == null) {
            return left;
        } //Has only right
        else if (left == null && right != null) {
            return right;
        } //Has both children
        else {
            //Replacement has to be in right branch (value is bigger then left branch)
            BinaryTreeNode<T> parent = baseNode;
            BinaryTreeNode<T> current = right;

            //Get the smallest value on right branch
            while (current.left != null) {
                parent = current;
                current = current.left;
            }

            //First node on right branch was the smallest
            //Put left branch as this node's left child
            if (current.equals(baseNode.right)) {
                current.left = left;
            } //After got the smallest value on right branch
            else {
                //Put the right child of current (on right branch, because left child is null)
                //as left child of parent (like temp removed current from tree)
                parent.left = current.right;

                //Put the full right branch as current node right child
                current.right = baseNode.right;

                //Put left branch as current left child
                current.left = baseNode.left;
            }

            //Current will be the replacement
            //With left children as old replaced left children
            //And right children as new rearranged right children (without current)
            return current;
        }
    }

    /**
     * Removes all occurences of the specified element from this tree. Uses
     * removeElement() while tree has desired element.
     *
     * @param targetElement the element that the list will have all instances of
     * it removed
     * @throws estg.ed.exceptions.NotComparableException target element is not
     * comparable
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws NotComparableException {
        while (true) {
            try {
                //Remove target element
                this.removeElement(targetElement);
            } //There is no more targetElement to remove
            catch (ElementNotFoundException | EmptyCollectionException e) {
                break;
            }
        }
    }

    /**
     * Removes and returns the smallest element from this tree.
     *
     * @return the smallest element from this tree.
     * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (this.root == null) {
            throw new EmptyCollectionException("Binary search tree is empty!");
        }

        BinaryTreeNode<T> currentNode = this.root;

        //Has no left children
        //Root was min
        if (currentNode.left == null) //Right child will be new root
        {
            this.root = currentNode.right;
        } //Has left children
        else {
            //Go to smallest left child
            BinaryTreeNode<T> parentNode = currentNode;
            currentNode = currentNode.left;

            while (currentNode.left != null) {
                parentNode = currentNode;
                currentNode = currentNode.left;
            }

            //After found smallest left child (currentNode)
            //Is a leaf (has no children)
            if (currentNode.right == null) //parentNode left child became null
            {
                parentNode.left = null;
            } //Is an internal node (has right child)
            else //parentNode left child became currentNode right child
            {
                parentNode.left = currentNode.right;
            }
        }

        //Return removed data
        return currentNode.data;
    }

    /**
     * Removes and returns the largest element from this tree.
     *
     * @return the largest element from this tree
     * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
     */
    @Override
    public T removeMax() throws EmptyCollectionException {
        if (this.root == null) {
            throw new EmptyCollectionException("Binary search tree is empty!");
        }

        BinaryTreeNode<T> currentNode = this.root;

        //Has no right children
        //Root was max
        if (currentNode.right == null) //Left child will be new root
        {
            this.root = currentNode.left;
        } //Has right children
        else {
            //Go to biggest right child
            BinaryTreeNode<T> parentNode = currentNode;
            currentNode = currentNode.right;
            while (currentNode.right != null) {
                parentNode = currentNode;
                currentNode = currentNode.right;
            }

            //After found biggest right child (currentNode)
            //Is a leaf (has no children)
            if (currentNode.left == null) //parentNode right child became null
            {
                parentNode.right = null;
            } //Is an internal node (has left child)
            else //parentNode right child became currentNode left child
            {
                parentNode.right = currentNode.left;
            }
        }

        //Return removed data
        return currentNode.data;
    }

    /**
     * Returns a reference to the smallest element in this tree.
     *
     * @return a reference to the smallest element in this tree
     * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (this.root == null) {
            throw new EmptyCollectionException("Binary search tree is empty!");
        }

        BinaryTreeNode<T> currentNode = this.root;

        //Has left child
        //Root is not the smallest
        if (currentNode.left != null) {
            //Get smallest children
            currentNode = currentNode.left;
            while (currentNode.left != null) {
                currentNode = currentNode.left;
            }
        }

        //Return data
        return currentNode.data;
    }

    /**
     * Returns a reference to the largest element in this tree.
     *
     * @return a reference to the largest element in this tree
     * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
     */
    @Override
    public T findMax() throws EmptyCollectionException {
        if (this.root == null) {
            throw new EmptyCollectionException("Binary search tree is empty!");
        }

        BinaryTreeNode<T> currentNode = this.root;

        //Has right child
        //Root is not the biggest
        if (currentNode.right != null) {
            //Get biggest children
            currentNode = currentNode.right;
            while (currentNode.right != null) {
                currentNode = currentNode.right;
            }
        }

        //Return data
        return currentNode.data;
    }

}
