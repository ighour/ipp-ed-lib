/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.tree.binary;

import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.exceptions.NotComparableException;
import estg.ed.interfaces.PriorityQueueADT;
import estg.ed.nodes.PriorityQueueNode;

/**
 * Implements priority queue with maximum priority.
 * Uses double for priority.
 * @author igu
 * @param <T>
 */
public class ArrayPriorityMaxQueue<T> extends ArrayMaxHeap<PriorityQueueNode<T>> implements PriorityQueueADT<T> {  

  /** 
   * Adds one element to this priority queue.
   * Uses double type for priority.
   * @param element the element to be added to this queue
   * @param priority
   */
  @Override
  public void enqueue(T element, double priority) {
    //Create node
    PriorityQueueNode<T> node = new PriorityQueueNode<>(element, priority);
    
    //PriorityQueueNode are always Comparable
    try {  
      super.addElement(node);
      
    } catch (NotComparableException ex){}
  }

  /** 
   * Removes and returns the element at the front of this queue.
   * Throws EmptyCollectionException if queue is empty.
   * @return the element at the front of this queue
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T dequeue() throws EmptyCollectionException {
    //Get node
    PriorityQueueNode<T> node = (PriorityQueueNode<T>) super.removeMax();
    
    //Return data
    return node.data;
  }

  /** 
   * Returns without removing the element at the front of this queue.
   * Throws EmptyCollectionException if queue is empty.
   * @return the first element in this queue
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T first() throws EmptyCollectionException {
    //Get node
    PriorityQueueNode<T> node = (PriorityQueueNode<T>) super.findMax();
    
    //Return data
    return node.data;
  } 
}
