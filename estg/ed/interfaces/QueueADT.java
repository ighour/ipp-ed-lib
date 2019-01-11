/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.EmptyCollectionException;

/**
 * Contract for Queues
 * @author igu
 * @param <T>
 */
public interface QueueADT<T> {
  /** 
   * Adds one element to the rear of this queue.
   * @param element the element to be added to the rear of this queue
   */
   public void enqueue(T element);
   
  /** 
   * Removes and returns the element at the front of this queue.
   * @return the element at the front of this queue
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
   public T dequeue() throws EmptyCollectionException;
   
  /** 
   * Returns without removing the element at the front of this queue.
   * @return the first element in this queue
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
   public T first() throws EmptyCollectionException;
   
  /** 
   * Returns true if this queue contains no elements.
   * @return boolean whether or not this queue is empty
   */
   public boolean isEmpty();
   
  /** 
   * Returns the number of elements in this queue.
   * @return int number of elements in this queue
   */
   public int size();
   
  /** 
   * Returns a string representation of this queue.
   * @return String representation of this queue
   */
   @Override
   public String toString();
}
