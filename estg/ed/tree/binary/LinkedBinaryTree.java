/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.tree.binary;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.BinaryTreeADT;
import estg.ed.interfaces.QueueADT;
import estg.ed.interfaces.UnorderedListADT;
import estg.ed.list.UnorderedArrayList;
import estg.ed.nodes.BinaryTreeNode;
import estg.ed.queue.ArrayQueue;
import java.util.Iterator;

/**
 * Binary tree implementation with linked nodes.
 * @author igu
 * @param <T>
 */
public abstract class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
  
  /**
   * Count elements in tree
   */
  protected int count;
  
  /**
   * Tree root
   */
  protected BinaryTreeNode<T> root;
  
  /**
   * Instantiates a binary tree without root.
   */
  public LinkedBinaryTree(){
    this.count = 0;
    this.root = null;
  }
  
  /**
   * Instantiates a binary tree with root.
   * @param root
   */
  public LinkedBinaryTree(T root){
    this.count = 1;
    this.root = new BinaryTreeNode<>(root);
  }

  /**
   * Returns a reference to the root element.
   * Throws EmptyCollectionException if root is null.
   * @return a reference to the root
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T getRoot() throws EmptyCollectionException {
    if(this.root == null)
      throw new EmptyCollectionException("Tree is empty!");
    
    return this.root.data;
  }

  /**
   * Returns true if this binary tree is empty and false otherwise.
   * @return true if this binary tree is empty
   */
  @Override
  public boolean isEmpty() {
    return this.count == 0;
  }

  /**
   * Returns the number of elements in this binary tree.
   * @return the integer number of elements in this tree
   */
  @Override
  public int size() {
    return this.count;
  }

  /**
   * Returns true if the binary tree contains an element that matches the specified element and false otherwise.
   * @param targetElement the element being sought in the tree
   * @return true if the tree contains the target element
   */
  @Override
  public boolean contains(T targetElement) {
    //Search node in root tree    
    return findNode(targetElement, this.root) != null;
  }

  /**
   * Returns a reference to the specified element if it is found in this binary tree.
   * Throws ElementNotFoundException if the specified element is not found.
   * @param targetElement the element being sought in the tree
   * @return a reference to the specified element
   * @throws estg.ed.exceptions.ElementNotFoundException
   */
  @Override
  public T find(T targetElement) throws ElementNotFoundException {
    //Search node in root tree   
    BinaryTreeNode<T> found = findNode(targetElement, this.root);
    
    if(found == null)
      throw new ElementNotFoundException("Element was not found.");
    
    return found.data;
  }
  
  /**
   * Search for targetElem in (sub)tree.
   * Uses recursion.
   * @param targetElem
   * @param currentNode
   * @return 
   */
  private BinaryTreeNode<T> findNode(T targetElem, BinaryTreeNode<T> currentNode){
    //Node is null
    if(currentNode == null)
      return null;
    
    //Found targetElem
    if(currentNode.data.equals(targetElem))
      return currentNode;
    
    //Search in left tree
    BinaryTreeNode<T> leftNode = this.findNode(targetElem, currentNode.left);
    if(leftNode != null)
      return leftNode;
    
    //Search in right tree
    BinaryTreeNode<T> rightNode = this.findNode(targetElem, currentNode.right);
    if(rightNode != null)
      return rightNode;
    
    //Not found
    return null;
  }
  
  /**
   * Returns the string representation of the binary tree.
   * Using iterator in level order.
   * @return a string representation of the binary tree
   */
  @Override
  public String toString() {
    Iterator<T> it = this.iteratorLevelOrder();

    StringBuilder stb = new StringBuilder();

    stb.append("[");

    while(it.hasNext()){
        T content = it.next();

        stb.append("[").append(content.toString()).append("]");

        if(it.hasNext())
            stb.append(",");
    }

    stb.append("]");

    return stb.toString();
  }

  /**
   * Performs an inorder traversal on this binary tree by calling an overloaded, recursive inorder method that starts with the root.
   * Uses an unordered array list to store elements order.
   * @return an iterator over the elements of this binary tree
   */
  @Override
  public Iterator<T> iteratorInOrder() {
    UnorderedListADT<T> list = new UnorderedArrayList<>();
    
    //Start recursion in root
    inorder(list, this.root);
    
    return list.iterator();
  }
  
  private void inorder(UnorderedListADT<T> list, BinaryTreeNode<T> node){
    if(node == null)
      return;
    
    inorder(list, node.left);
    list.addToRear(node.data);
    inorder(list, node.right);
  }

  /**
   * Performs a preorder traversal on this binary tree by calling an overloaded, recursive preorder method that starts with the root.
   * Uses an unordered array list to store elements order.
   * @return an iterator over the elements of this binary tree
   */
  @Override
  public Iterator<T> iteratorPreOrder() {
    UnorderedListADT<T> list = new UnorderedArrayList<>();
    
    //Start recursion in root
    preorder(list, this.root);
    
    return list.iterator();
  }
  
  private void preorder(UnorderedListADT<T> list, BinaryTreeNode<T> node){
    if(node == null)
      return;
    
    list.addToRear(node.data);
    preorder(list, node.left);
    preorder(list, node.right);
  }

  /**
   * Performs a postorder traversal on this binary tree by calling an overloaded, recursive postorder method that starts with the root.
   * Uses an unordered array list to store elements order.
   * @return an iterator over the elements of this binary tree
   */
  @Override
  public Iterator<T> iteratorPostOrder() {
    UnorderedListADT<T> list = new UnorderedArrayList<>();
    
    //Start recursion in root
    postorder(list, this.root);
    
    return list.iterator();
  }
  
  private void postorder(UnorderedListADT<T> list, BinaryTreeNode<T> node){
    if(node == null)
      return;
    
    postorder(list, node.left);
    postorder(list, node.right);
    list.addToRear(node.data);
  }

  /**
   * Performs a levelorder traversal on the binary tree, using a queue.
   * Uses an unordered array list to store elements order.
   * Uses a queue array to store retrieve order of nodes before storing in list.
   * @return an iterator over the elements of this binary tree
   */
  @Override
  public Iterator<T> iteratorLevelOrder() {
    UnorderedListADT<T> list = new UnorderedArrayList<>();
    QueueADT<BinaryTreeNode<T>> queue = new ArrayQueue<>();
    
    //Is empty
    if(this.root == null)
      return list.iterator();
    
    //Start recursion with root in queue
    queue.enqueue(this.root);
    levelorder(list, queue);
    
    return list.iterator();
  }
  
  private void levelorder(UnorderedListADT<T> list, QueueADT<BinaryTreeNode<T>> queue){
    //Queue is not empty
    while(!queue.isEmpty()){
      BinaryTreeNode<T> currentNode;
      try {
        
        //Retrieve first from queue
        currentNode = queue.dequeue();
        
        //Add current node data to result
        list.addToRear(currentNode.data);
      
        //Add left child of current node to queue
        if(currentNode.left != null)
          queue.enqueue(currentNode.left);

        //Add right child of current node to queue
        if(currentNode.right != null)
          queue.enqueue(currentNode.right);
        
        //Proceed in recursion
        this.levelorder(list, queue);
        
      } catch (EmptyCollectionException ex) {
        return;
      }
    }
  }
  
}
