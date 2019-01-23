/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.tree.binary;

import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.HeapMinADT;

/**
 * Minheap implementation with circular dynamic array.
 * Using already implemented circular dynamic array.
 * @author igu
 * @param <T> generic
 */
public class ArrayMinHeap<T> extends ArrayHeap<T> implements HeapMinADT<T> {

  /**
   * Heapify up an element.
   * Until element is smaller then parent.
   * Uses recursion.
   * @param elementIndex index of element to heapifyUp
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
    
    //Check if element is smaller then parentElement
    if(((Comparable) element).compareTo((Comparable) parentElement) < 0){
      //Exchange elements
      this.array.change(element, parentIndex);
      this.array.change(parentElement, elementIndex);
      
      //Proceed heapify up
      this.heapifyUp(parentIndex);
    }
  }

  /**
   * Removes element with the lowest value from this heap.
   * Uses removeRoot() method of ArrayHeap.
   * @return the element with the lowest value from this heap
   * @throws estg.ed.exceptions.EmptyCollectionException heap is empty
   */
  @Override
  public T removeMin() throws EmptyCollectionException {
    try {
      return super.removeRoot();
    }
    catch(EmptyCollectionException e){
      throw new EmptyCollectionException("Minheap is empty!");
    }
  }
  
  /**
   * Heapify down an element.
   * Until parent is smaller or equal to children.
   * Uses recursion.
   * @param parentIndex index of element to heapifyDown
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
      //Right > Left
      if(((Comparable) right).compareTo((Comparable) left) > 0){
        //Parent > Left
        if(((Comparable) parent).compareTo((Comparable) left) > 0)
          this.exchangeParentChild(parentIndex, parent, leftIndex, left);
      }
      //Left >= Right
      else{
        //Parent > Right
        if(((Comparable) parent).compareTo((Comparable) right) > 0)
          this.exchangeParentChild(parentIndex, parent, rightIndex, right);
      }
    }
    
    //Left
    else if(left != null){
      //Parent > Left
      if(((Comparable) parent).compareTo((Comparable) left) > 0)
        this.exchangeParentChild(parentIndex, parent, leftIndex, left);
    }
    
    //Right
    else if(right != null){
      //Parent > Right
      if(((Comparable) parent).compareTo((Comparable) right) > 0)
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
   * Returns a reference to the element with the lowest value in this heap.
   * Uses findRoot() method of ArrayHeap.
   * @return a reference to the element with the lowest value in this heap
   * @throws estg.ed.exceptions.EmptyCollectionException heap is empty
   */
  @Override
  public T findMin() throws EmptyCollectionException {
    try {
      return super.findRoot();
    }
    catch(EmptyCollectionException e){
      throw new EmptyCollectionException("Minheap is empty!");
    }
  }
}
