/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.queue;

import estg.ed.basic.DoubleLinkedList;
import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.CollectionDoubleLinkedContract;
import estg.ed.interfaces.QueueADT;

/**
 * Queue implemented with double linked list.
 * Using already implemented double linked list.
 * @author igu
 * @param <T>
 */
public class LinkedQueue<T> implements QueueADT<T> {
  
  /**
   * Double Linked list to store data.
   */
  CollectionDoubleLinkedContract<T> linked;
  
  /**
   * Instantiate the queue using a double linked list.
   */
  public LinkedQueue(){
    this.linked = new DoubleLinkedList<>();
  }

  /** 
   * Adds one element to the rear of this queue.
   * Inserts on internal double linked list at last position.
   * @param element the element to be added to the rear of this queue
   */
  @Override
  public void enqueue(T element) {
    this.linked.addToRear(element);
  }

  /** 
   * Removes and returns the element at the front of this queue.
   * Removes from internal double linked list from first position.
   * Throws EmptyCollectionException if first position is not found (list is empty).
   * @return the element at the front of this queue
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T dequeue() throws EmptyCollectionException {
    try {
      return this.linked.removePosition(0);
      
    } catch (ElementNotFoundException ex) {
      throw new EmptyCollectionException("Queue is empty!");
    }
  }

  /** 
   * Returns without removing the element at the front of this queue.
   * Returns from internal double linked list from first position.
   * Throws EmptyCollectionException if first position is not found (list is empty).
   * @return the first element in this queue
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T first() throws EmptyCollectionException {
    try {
      return this.linked.get(0);
      
    } catch (ElementNotFoundException ex) {
      throw new EmptyCollectionException("Queue is empty!");
    }
  }

  /** 
   * Returns true if this queue contains no elements.
   * Returns result from internal double linked list.
   * @return boolean whether or not this queue is empty
   */
  @Override
  public boolean isEmpty() {
    return this.linked.isEmpty();
  }

  /** 
   * Returns the number of elements in this queue.
   * Returns result from internal double linked list.
   * @return int number of elements in this queue
   */
  @Override
  public int size() {
    return this.linked.size();
  }
  
  /** 
   * Returns a string representation of this queue.
   * Returns result from internal double linked list.
   * @return String representation of this queue
   */
  @Override
  public String toString() {
    return this.linked.toString();
  }
}
