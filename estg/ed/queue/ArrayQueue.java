/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.queue;

import estg.ed.basic.DynamicArrayCircular;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.CollectionArrayContract;
import estg.ed.interfaces.QueueADT;

/**
 * Queue implemented with circular dynamic array.
 * Using already implemented circular dynamic array.
 * @author igu
 * @param <T>
 */
public class ArrayQueue<T> implements QueueADT<T> {
  
  /**
   * Circular dynamic array to store data.
   */
  CollectionArrayContract<T> array;
  
  /**
   * Instantiate the queue using a circular dynamic array.
   */
  public ArrayQueue(){
    this.array = new DynamicArrayCircular<>();
  }

  /** 
   * Adds one element to the rear of this queue.
   * Inserts on internal circular dynamic array on next empty index.
   * @param element the element to be added to the rear of this queue
   */
  @Override
  public void enqueue(T element) {
    int last = this.array.size() - 1;
    
    this.array.add(element, last + 1);
  }

  /** 
   * Removes and returns the element at the front of this queue.
   * Removes from internal circular dynamic array from first index.
   * Throws EmptyCollectionException if array is empty.
   * @return the element at the front of this queue
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T dequeue() throws EmptyCollectionException {
    if(this.array.size() == 0)
      throw new EmptyCollectionException("Queue is empty!");
    
    return this.array.remove(0);
  }

  /** 
   * Returns without removing the element at the front of this queue.
   * Returns from internal circular dynamic array from first index.
   * Throws EmptyCollectionException if array is empty.
   * @return the first element in this queue
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T first() throws EmptyCollectionException {
    if(this.array.size() == 0)
      throw new EmptyCollectionException("Queue is empty!");
    
    return this.array.get(0);
  }

  /** 
   * Returns true if this queue contains no elements.
   * Returns result from internal circular dynamic array.
   * @return boolean whether or not this queue is empty
   */
  @Override
  public boolean isEmpty() {
    return this.array.isEmpty();
  }

  /** 
   * Returns the number of elements in this queue.
   * Returns result from internal circular dynamic array.
   * @return int number of elements in this queue
   */
  @Override
  public int size() {
    return this.array.size();
  }
  
  /** 
   * Returns a string representation of this queue.
   * Returns result from internal circular dynamic array.
   * @return String representation of this queue
   */
  @Override
  public String toString() {
    return this.array.toString();
  }
}
