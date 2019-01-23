/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.tree.binary;

import estg.ed.array.DynamicArrayCircular;
import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.BinaryTreeADT;
import estg.ed.interfaces.DynamicArrayContract;
import estg.ed.interfaces.QueueADT;
import estg.ed.interfaces.UnorderedListADT;
import estg.ed.list.UnorderedArrayList;
import estg.ed.queue.ArrayQueue;
import java.util.Iterator;

/**
 * Binary tree implementation with circular dynamic array.
 * Using already implemented circular dynamic array.
 * @author igu
 * @param <T> generic
 */
public abstract class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
  
  /**
   * Circular dynamic array to store data.
   */
  DynamicArrayContract<T> array;
  
  /**
   * Instantiates a binary tree without root.
   */
  public ArrayBinaryTree(){
    this.array = new DynamicArrayCircular();
  }
  
  /**
   * Instantiates a binary tree with root.
   * @param root first nood of tree
   */
  public ArrayBinaryTree(T root){
    this.array = new DynamicArrayCircular();
    this.array.add(root, 0);
  }

  /**
   * Returns a reference to the root element.
   * Uses internal circular dynamic array get() method on first position.
   * @return a reference to the root
   * @throws estg.ed.exceptions.EmptyCollectionException root is null
   */
  @Override
  public T getRoot() throws EmptyCollectionException {
    if(this.array.size() == 0)
      throw new EmptyCollectionException("Tree is empty!");
    
    return this.array.get(0);
  }

  /**
   * Returns true if this binary tree is empty and false otherwise.
   * Uses internal circular dynamic array isEmpty() method.
   * @return true if this binary tree is empty
   */
  @Override
  public boolean isEmpty() {
    return this.array.isEmpty();
  }

  /**
   * Returns the number of elements in this binary tree.
   * Uses internal circular dynamic array size() method.
   * @return the integer number of elements in this tree
   */
  @Override
  public int size() {
    return this.array.size();
  }

  /**
   * Returns true if the binary tree contains an element that matches the specified element and false otherwise.
   * @param targetElement the element being sought in the tree
   * @return true if the tree contains the target element
   */
  @Override
  public boolean contains(T targetElement) {
    //Search element in internal array from root
    return this.findElement(targetElement, 0) >= 0;
  }

  /**
   * Returns a reference to the specified element if it is found in this binary tree.
   * @param targetElement the element being sought in the tree
   * @return a reference to the specified element
   * @throws estg.ed.exceptions.ElementNotFoundException specified element is not found
   */
  @Override
  public T find(T targetElement) throws ElementNotFoundException {
    //Search element in internal array from root 
    int found = this.findElement(targetElement, 0);
    
    if(found < 0)
      throw new ElementNotFoundException("Element was not found.");
    
    return this.array.get(found);
  }
  
  /**
   * Search for targetElem in (sub)tree.
   * Uses recursion.
   * Uses internal circular dynamic array to get elements.
   * @param targetElem element to find
   * @param currentIndex index to start searching
   * @return index of found element or -1 if not found
   */
  private int findElement(T targetElem, int currentIndex){
    //Search element
    try{
      //Found Index
      if(this.array.get(currentIndex).equals(targetElem))
        return currentIndex;
    }
    catch(IndexOutOfBoundsException e){
      //Index is invalid
      return -1;
    }
    
    //Search in left children
    int leftIndex = this.findElement(targetElem, currentIndex * 2 + 1);
    if(leftIndex >= 0)
      //Found
      return leftIndex;
    
    //Search in right children
    int rightIndex = this.findElement(targetElem, currentIndex * 2 + 2);
    if(rightIndex >= 0)
      //Found
      return rightIndex;
    
    //Not found
    return -1;
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
    inorder(list, 0);
    
    return list.iterator();
  }
  
  private void inorder(UnorderedListADT<T> list, int index){
    //Check left child
    int leftIndex = index * 2 + 1;
    if(leftIndex < this.array.size())
      //Enter left child
      this.inorder(list, leftIndex);

    //Add current to list
    list.addToRear(this.array.get(index));
    
    //Check right child
    int rightChild = index * 2 + 2;
    if(rightChild < this.array.size())
      //Enter right child
      this.inorder(list, rightChild);
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
    preorder(list, 0);
    
    return list.iterator();
  }
  
  private void preorder(UnorderedListADT<T> list, int index){
    //Add current to list
    list.addToRear(this.array.get(index));
    
    //Check left child
    int leftIndex = index * 2 + 1;
    if(leftIndex < this.array.size())
      //Enter left child
      this.preorder(list, leftIndex);

    //Check right child
    int rightChild = index * 2 + 2;
    if(rightChild < this.array.size())
      //Enter right child
      this.preorder(list, rightChild);
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
    postorder(list, 0);
    
    return list.iterator();
  }
  
  private void postorder(UnorderedListADT<T> list, int index){        
    //Check left child
    int leftIndex = index * 2 + 1;
    if(leftIndex < this.array.size())
      //Enter left child
      this.postorder(list, leftIndex);

    //Check right child
    int rightChild = index * 2 + 2;
    if(rightChild < this.array.size())
      //Enter right child
      this.postorder(list, rightChild);
    
    //Add current to list
    list.addToRear(this.array.get(index));
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
    QueueADT<Integer> queue = new ArrayQueue<>();
    
    //Start recursion with root in queue
    queue.enqueue(0);
    levelorder(list, queue);
    
    return list.iterator();
  }
  
  private void levelorder(UnorderedListADT<T> list, QueueADT<Integer> queue){
    //Try to get from queue
    try {
      //Retrieve first from queue
      //If empty will throw EmptyCollectionException
      int index = queue.dequeue();
      
      //Retrieve element from internal array
      //If index is invalid throw IndexOutOfBoundsException
      T element = this.array.get(index);

      //Add element to list
      list.addToRear(element);

      //Add left child index to queue
      queue.enqueue(index * 2 + 1);

      //Add right child index to queue
      queue.enqueue(index * 2 + 2);

      //Proceed in recursion
      this.levelorder(list, queue);

    } catch (EmptyCollectionException | IndexOutOfBoundsException ex) {}
  }
  
}
