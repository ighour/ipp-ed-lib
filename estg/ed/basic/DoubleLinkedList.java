/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.basic;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.interfaces.CollectionDoubleLinkedContract;
import estg.ed.nodes.DoubleLinearNode;

/**
 * Linked list of collection elements
 * @author igu
 * @param <T>
 */
public class DoubleLinkedList<T> implements CollectionDoubleLinkedContract<T> {
  /**
   * Front sentinel of list.
   */
  protected DoubleLinearNode<T> frontSentinel;
  
  /**
   * Rear sentinel of list.
   */
  protected DoubleLinearNode<T> rearSentinel;
  
  /**
   * List count.
   */
  protected int count;
  
  /**
   * Creates a double linked list.
   */
  public DoubleLinkedList(){
    this.frontSentinel = new DoubleLinearNode<>();
    this.rearSentinel = new DoubleLinearNode<>();
    this.frontSentinel.next = this.rearSentinel;
    this.rearSentinel.previous = this.frontSentinel;
    this.count = 0;
  }
  
  /**
   * Add an element to front, after front sentinel node.
   * Traversing list from frontSentinel to rearSentinel.
   * @param element 
   */
  @Override
  public void addToFront(T element) {
    //Create new node
    DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);
    
    //Insert in list
    newNode.next = this.frontSentinel.next;
    newNode.previous = this.frontSentinel;
    
    this.frontSentinel.next.previous = newNode;
    this.frontSentinel.next = newNode; 

    //Increment count
    this.count++;
  }
  
  /**
   * Add an element to rear, after all elements.
   * Traversing list from rearSentinel to frontSentinel.
   * @param element 
   */
  @Override
  public void addToRear(T element) {
    //Create new node
    DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);
    
    //Insert in list
    newNode.next = this.rearSentinel;
    newNode.previous = this.rearSentinel.previous;
    
    this.rearSentinel.previous = newNode;
    newNode.previous.next = newNode; 

    //Increment count
    this.count++;
  }
  
  /**
   * Add an element after another element.
   * Traversing list from frontSentinel to rearSentinel.
   * Throws ElementNotFoundException if target is not in list.
   * @param element 
   * @param target 
   * @throws ElementNotFoundException 
   */
  @Override
  public void addAfter(T element, T target) throws ElementNotFoundException {
    //Search for target
    DoubleLinearNode<T> currentNode = this.frontSentinel.next;
    
    while(!currentNode.equals(this.rearSentinel) && !currentNode.data.equals(target))
      currentNode = currentNode.next;

    if(currentNode.equals(this.rearSentinel) || !currentNode.data.equals(target))
      throw new ElementNotFoundException("Element was not found!");
    
    //Create new node
    DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);
    
    //Insert in list
    newNode.next = currentNode.next;
    newNode.previous = currentNode;
    
    currentNode.next = newNode;
    newNode.next.previous = newNode;

    //Increment count
    this.count++;
  }
  
  /**
   * Add an element before another element.
   * Traversing list from frontSentinel to rearSentinel.
   * Throws ElementNotFoundException if target is not in list.
   * @param element 
   * @param target 
   * @throws ElementNotFoundException 
   */
  @Override
  public void addBefore(T element, T target) throws ElementNotFoundException {
    //Search for target
    DoubleLinearNode<T> currentNode = this.frontSentinel.next;
    
    while(!currentNode.equals(this.rearSentinel) && !currentNode.data.equals(target))
      currentNode = currentNode.next;

    if(currentNode.equals(this.rearSentinel) || !currentNode.data.equals(target))
      throw new ElementNotFoundException("Element was not found!");
    
    //Create new node
    DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element);
    
    //Insert in list
    newNode.next = currentNode;
    newNode.previous = currentNode.previous;
    
    currentNode.previous = newNode;
    newNode.previous.next = newNode;

    //Increment count
    this.count++;
  }

  /**
   * Remove an element.
   * Traversing list from frontSentinel to rearSentinel.
   * Throws ElementNotFoundException if element is not in list.
   * @param element
   * @return 
   * @throws ElementNotFoundException 
   */
  @Override
  public T remove(T element) throws ElementNotFoundException{
    //Search for one element before
    DoubleLinearNode<T> currentNode = this.frontSentinel;
    
    while(!currentNode.next.equals(this.rearSentinel) && !currentNode.next.data.equals(element))
      currentNode = currentNode.next;
    
    if(currentNode.equals(this.rearSentinel) || !currentNode.next.data.equals(element))
      throw new ElementNotFoundException("Element was not found!");
    
    //Get removed content
    T removed = currentNode.next.data;
    
    //Rearrange nodes
    currentNode.next.next.previous = currentNode;
    currentNode.next = currentNode.next.next;
    this.count--;

    return removed;
  }
  
  /**
   * Remove an element from desired position.
   * Traversing list from frontSentinel to rearSentinel.
   * Throws ElementNotFoundException if position is invalid.
   * @param position
   * @return 
   * @throws ElementNotFoundException 
   */
  @Override
  public T removePosition(int position) throws ElementNotFoundException {
    //Position is invalid
    if(position < 0 || position > this.count - 1)
      throw new ElementNotFoundException("Element was not found!");
    
    //Search for one element before desired position
    DoubleLinearNode<T> currentNode = this.frontSentinel;
    
    for(int i = 0; i < position; i++)
      currentNode = currentNode.next;
    
    //Get removed content
    T removed = currentNode.next.data;
    
    //Rearrange nodes
    currentNode.next.next.previous = currentNode;
    currentNode.next = currentNode.next.next;
    this.count--;

    return removed;
  }

  /**
   * Get an element from desired position.
   * Traversing list from frontSentinel to rearSentinel.
   * Throws ElementNotFoundException if position is invalid.
   * @param position
   * @return 
   * @throws ElementNotFoundException 
   */
  @Override
  public T get(int position) throws ElementNotFoundException {
    //Position is invalid
    if(position < 0 || position > this.count - 1)
      throw new ElementNotFoundException("Element was not found!");
    
    //Search for desired position
    DoubleLinearNode<T> currentNode = this.frontSentinel;
    
    for(int i = 0; i <= position; i++)
      currentNode = currentNode.next;

    return currentNode.data;
  }

  @Override
  public int size() {
    return this.count;
  }

  @Override
  public boolean isEmpty() {
    return this.count == 0;
  }

  @Override
  public String toString() {
      DoubleLinearNode<T> currentNode = this.frontSentinel.next;

      StringBuilder stb = new StringBuilder();

      stb.append("[");

      while(!currentNode.equals(this.rearSentinel)){
          T content = currentNode.data;

          stb.append("[").append(content.toString()).append("]");

          currentNode = currentNode.next;

          if(!currentNode.equals(this.rearSentinel))
              stb.append(",");
      }

      stb.append("]");

      return stb.toString();
  }
}
