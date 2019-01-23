/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.queue;

import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.QueueADT;
import estg.ed.nodes.LinearNode;

/**
 * Queue implemented with linked list.
 * @author igu
 * @param <T> generic
 */
public class LinkedQueue<T> implements QueueADT<T> {
  
  /**
   * Front sentinel of list.
   */
  protected LinearNode<T> frontSentinel;
  
  /**
   * Rear sentinel of list.
   */
  protected LinearNode<T> rearSentinel;
  
  /**
   * Elements in queue.
   */
  protected int count;
  
  /**
   * Instantiate the queue using front and rear sentinels.
   */
  public LinkedQueue(){
    this.frontSentinel = new LinearNode<>();
    this.rearSentinel = new LinearNode<>();
    this.count = 0;
  }

  /** 
   * Adds one element to the rear of this queue.
   * Inserts as front sentinel next node if empty
   * Inserts as rear sentinel next node
   * @param element the element to be added to the rear of this queue
   */
  @Override
  public void enqueue(T element) {
    LinearNode<T> newNode = new LinearNode<>(element);
    
    //Is empty
    if(this.count == 0)
      this.frontSentinel.next = newNode;
    
    //Not empty
    else
      this.rearSentinel.next.next = newNode;
    
    //Set element as new last
    this.rearSentinel.next = newNode;
    
    this.count++;
  }

  /** 
   * Removes and returns the element at the front of this queue.
   * Removes from first node after front sentinel.
   * @return the element at the front of this queue
   * @throws estg.ed.exceptions.EmptyCollectionException queue is empty
   */
  @Override
  public T dequeue() throws EmptyCollectionException {
    if(this.count == 0)
      throw new EmptyCollectionException("Queue is empty!");

    //Get top element data
    T element = this.frontSentinel.next.data;
    
    //Remove top element
    this.frontSentinel.next = this.frontSentinel.next.next;
    
    this.count--;
    
    //Return element
    return element;
  }

  /** 
   * Returns without removing the element at the front of this queue.
   * Returns from first node after front sentinel.
   * @return the first element in this queue
   * @throws estg.ed.exceptions.EmptyCollectionException queue is empty
   */
  @Override
  public T first() throws EmptyCollectionException {
    if(this.count == 0)
      throw new EmptyCollectionException("Queue is empty!");

    //Return element
    return this.frontSentinel.next.data;
  }

  /** 
   * Returns true if this queue contains no elements.
   * @return boolean whether or not this queue is empty
   */
  @Override
  public boolean isEmpty() {
    return this.count == 0;
  }

  /** 
   * Returns the number of elements in this queue.
   * @return int number of elements in this queue
   */
  @Override
  public int size() {
    return this.count;
  }
  
  /** 
   * Returns a string representation of this queue.
   * @return String representation of this queue
   */
  @Override
  public String toString() {
    LinearNode<T> currentNode = this.frontSentinel.next;

    StringBuilder stb = new StringBuilder();

    stb.append("[");

    while(currentNode != null){
        T content = currentNode.data;

        stb.append("[").append(content.toString()).append("]");

        currentNode = currentNode.next;

        if(currentNode != null)
            stb.append(",");
    }

    stb.append("]");

    return stb.toString();
  }
}
