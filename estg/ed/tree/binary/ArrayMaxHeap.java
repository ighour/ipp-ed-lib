/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.tree.binary;

import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.HeapMaxADT;

/**
 * Maxheap implementation with circular dynamic array.
 * Using already implemented circular dynamic array.
 * @author igu
 * @param <T>
 */
public class ArrayMaxHeap<T> extends ArrayHeap<T> implements HeapMaxADT<T> {

  /**
   * Heapify up an element.
   * Until element is higher then parent.
   * Uses recursion.
   * @param elementIndex
   */
  @Override
  protected void heapifyUp(int elementIndex) {
    //Always ends on root
    if(elementIndex == 0)
      return;
    
    //Get parent index
    int parentIndex = this.getParent(elementIndex);
    
    //Get elements
    T element = this.array.get(elementIndex);
    T parentElement = this.array.get(parentIndex);
    
    //Check if element is higher then parentElement
    if(((Comparable) element).compareTo((Comparable) parentElement) > 0){
      //Exchange elements
      this.array.change(element, parentIndex);
      this.array.change(parentElement, elementIndex);
      
      //Proceed heapify up
      this.heapifyUp(parentIndex);
    }
  }

  /**
   * Removes element with the highest value from this heap.
   * Throws EmptyCollectionException if heap is empty.
   * Uses removeRoot() method of ArrayHeap.
   * @return the element with the highest value from this heap
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T removeMax() throws EmptyCollectionException {
    try {
      return super.removeRoot();
    }
    catch(EmptyCollectionException e){
      throw new EmptyCollectionException("Maxheap is empty!");
    }
  }
  
  /**
   * Heapify down an element.
   * Until parent is higher or equal to children.
   * Uses recursion.
   * @param parentIndex
   */
  @Override
  protected void heapifyDown(int parentIndex){
    //Get heap size
    int size = this.array.size();
    
    //Ends if index is outside
    if(parentIndex >= size)
      return;
    
    //Get parent
    T parent = this.array.get(parentIndex);
    
    //Get children indexes
    int rightIndex = this.getRightChild(parentIndex);
    int leftIndex = this.getLeftChild(parentIndex);
    T right, left;
    
    //Get right child
    if(rightIndex < size)
      right = this.array.get(rightIndex);
    else
      right = null;
    
    //Get left child
    if(leftIndex < size)
      left = this.array.get(leftIndex);
    else
      left = null;
    
    //Right + Left
    if(right != null && left != null){
      //Right <= Left
      if(((Comparable) right).compareTo((Comparable) left) <= 0){
        //Parent < Left
        if(((Comparable) parent).compareTo((Comparable) left) < 0)
          this.exchangeParentChild(parentIndex, parent, leftIndex, left);
      }
      //Right > Left
      else{
        //Parent < Right
        if(((Comparable) parent).compareTo((Comparable) right) < 0)
          this.exchangeParentChild(parentIndex, parent, rightIndex, right);
      }
    }
    
    //Left
    else if(left != null){
      //Parent < Left
      if(((Comparable) parent).compareTo((Comparable) left) < 0)
        this.exchangeParentChild(parentIndex, parent, leftIndex, left);
    }
    
    //Right
    else if(right != null){
      //Parent < Right
      if(((Comparable) parent).compareTo((Comparable) right) < 0)
        this.exchangeParentChild(parentIndex, parent, rightIndex, right);
    }
  }
  
  private void exchangeParentChild(int parentIndex, T parent, int childIndex, T child){
    //Exchange parent with child
    this.array.change(parent, childIndex);
    this.array.change(child, parentIndex);

    //Proceed heapify down
    this.heapifyDown(childIndex);
  }
  
  /**
   * Returns a reference to the element with the highest value in this heap.
   * Throws EmptyCollectionException if heap is empty.
   * Uses findRoot() method of ArrayHeap.
   * @return a reference to the element with the highest value in this heap
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T findMax() throws EmptyCollectionException {
    try {
      return super.findRoot();
    }
    catch(EmptyCollectionException e){
      throw new EmptyCollectionException("Maxheap is empty!");
    }
  }
}
