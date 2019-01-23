/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.stack;

import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.StackADT;
import estg.ed.nodes.LinearNode;

/**
 * Stack implemented with linked list.
 * Using already implemented linked list. * @author igu
 * @param <T> generic
 */
public class LinkedStack<T> implements StackADT<T> {

  /**
   * Sentinel node of stack.
   */
  protected LinearNode<T> sentinel;
  
  /**
   * Elements in stack.
   */
  protected int count;
  
  /**
   * Instantiate the stack with a sentinel node.
   */
  public LinkedStack(){
    this.sentinel = new LinearNode<>();
    this.count = 0;
  }

  /** 
   * Adds one element to the top of this stack.
   * Inserts as sentinel next node.
   * @param element element to be pushed onto stack
   */
  @Override
  public void push(T element) {
    LinearNode<T> newNode = new LinearNode<>(element);
    
    //Insert element
    newNode.next = this.sentinel.next;
    this.sentinel.next = newNode;
    
    this.count++;
  }

  /** 
   * Removes and returns the top element from this stack.
   * Removes from first node after sentinel.
   * @return T element removed from the top of the stack
   * @throws estg.ed.exceptions.EmptyCollectionException stack is empty
   */
  @Override
  public T pop() throws EmptyCollectionException {
    if(this.count == 0)
      throw new EmptyCollectionException("Stack is empty!");

    //Get top element data
    T element = this.sentinel.next.data;
    
    //Remove top element
    this.sentinel.next = this.sentinel.next.next;
    
    this.count--;
    
    //Return element
    return element;
  }

  /** 
   * Returns without removing the top element of this stack.
   * Returns from first node after sentinel.
   * @return T element on top of the stack
   * @throws estg.ed.exceptions.EmptyCollectionException stack is empty
   */
  @Override
  public T peek() throws EmptyCollectionException {
    if(this.count == 0)
      throw new EmptyCollectionException("Stack is empty!");

    //Return element
    return this.sentinel.next.data;
  }

  /** 
   * Returns true if this stack contains no elements.
   * @return boolean whether or not this stack is empty
   */
  @Override
  public boolean isEmpty() {
    return this.count == 0;
  }

  /** 
   * Returns the number of elements in this stack.
   * @return int number of elements in this stack
   */
  @Override
  public int size() {
    return this.count;
  }
  
  /** 
   * Returns a string representation of this stack.
   * @return String representation of this stack
   */
  @Override
  public String toString() {
    LinearNode<T> currentNode = this.sentinel.next;

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
