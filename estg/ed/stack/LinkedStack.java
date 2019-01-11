/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.stack;

import estg.ed.basic.LinkedList;
import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.CollectionLinkedContract;
import estg.ed.interfaces.StackADT;

/**
 * Stack implemented with linked list.
 * Using already implemented linked list.
 * @author igu
 * @param <T>
 */
public class LinkedStack<T> implements StackADT<T> {
  
  /**
   * Linked list to store data.
   */
  CollectionLinkedContract<T> linked;
  
  /**
   * Instantiate the stack using a linked list.
   */
  public LinkedStack(){
    this.linked = new LinkedList<>();
  }

  /** 
   * Adds one element to the top of this stack.
   * Inserts on internal linked list at first position.
   * @param element element to be pushed onto stack
   */
  @Override
  public void push(T element) {
    this.linked.addToFront(element);
  }

  /** 
   * Removes and returns the top element from this stack.
   * Removes from internal linked list from first position.
   * Throws EmptyCollectionException if first position is not found (list is empty).
   * @return T element removed from the top of the stack
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T pop() throws EmptyCollectionException {
    try {
      return this.linked.removePosition(0);
      
    } catch (ElementNotFoundException ex) {
      throw new EmptyCollectionException("Stack is empty!");
    }
  }

  /** 
   * Returns without removing the top element of this stack.
   * Returns from internal linked list from first position.
   * Throws EmptyCollectionException if first position is not found (list is empty).
   * @return T element on top of the stack
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  @Override
  public T peek() throws EmptyCollectionException {
    try {
      return this.linked.get(0);
      
    } catch (ElementNotFoundException ex) {
      throw new EmptyCollectionException("Stack is empty!");
    }
  }

  /** 
   * Returns true if this stack contains no elements.
   * Returns result from internal linked list.
   * @return boolean whether or not this stack is empty
   */
  @Override
  public boolean isEmpty() {
    return this.linked.isEmpty();
  }

  /** 
   * Returns the number of elements in this stack.
   * Returns result from internal linked list.
   * @return int number of elements in this stack
   */
  @Override
  public int size() {
    return this.linked.size();
  }
  
  /** 
   * Returns a string representation of this stack.
   * Returns result from internal linked list.
   * @return String representation of this stack
   */
  @Override
  public String toString() {
    return this.linked.toString();
  }
}
