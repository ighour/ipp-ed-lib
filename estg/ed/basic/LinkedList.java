/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.basic;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.interfaces.CollectionLinkedContract;
import estg.ed.nodes.LinearNode;

/**
 * Linked list of collection elements
 * @author igu
 * @param <T>
 */
public class LinkedList<T> implements CollectionLinkedContract<T> {
  /**
   * Front sentinel of list
   */
  protected LinearNode<T> frontSentinel;
  
  /**
   * List count
   */
  protected int count;
  
  /**
   * Creates a linked list
   */
  public LinkedList(){
    this.frontSentinel = new LinearNode<>();
    this.count = 0;
  }
  
  /**
   * Add an element to front, after front sentinel node
   * @param element 
   */
  @Override
  public void addToFront(T element) {
    //Create new node
    LinearNode<T> newNode = new LinearNode<>(element);
    
    //Insert in list
    newNode.next = this.frontSentinel.next;
    this.frontSentinel.next = newNode;

    //Increment count
    this.count++;
  }
  
  /**
   * Add an element to rear, after all elements
   * @param element 
   */
  @Override
  public void addToRear(T element) {
    //Create new node
    LinearNode<T> newNode = new LinearNode<>(element);
    
    //Get previous node
    LinearNode<T> currentNode = this.frontSentinel;
    
    while(currentNode.next != null)
      currentNode = currentNode.next;
    
    //Insert in list
    currentNode.next = newNode;

    //Increment count
    this.count++;
  }
  
  /**
   * Add an element after another element
   * @param element 
   * @param target 
   * @throws ElementNotFoundException 
   */
  @Override
  public void addAfter(T element, T target) throws ElementNotFoundException {
    //Search for target
    LinearNode<T> currentNode = this.frontSentinel;
    
    while(currentNode.next != null && !currentNode.next.data.equals(target))
      currentNode = currentNode.next;

    if(currentNode == null || !currentNode.next.data.equals(target))
      throw new ElementNotFoundException("Element was not found!");
    
    //Create new node
    LinearNode<T> newNode = new LinearNode<>(element);
    
    //Insert in list
    newNode.next = currentNode.next;
    currentNode.next = newNode;

    //Increment count
    this.count++;
  }

  /**
   * Remove an element
   * @param element
   * @return 
   * @throws ElementNotFoundException 
   */
  @Override
  public T remove(T element) throws ElementNotFoundException{
    //Search for one element before
    LinearNode<T> currentNode = this.frontSentinel;
    
    while(currentNode.next != null && !currentNode.next.data.equals(element))
      currentNode = currentNode.next;
    
    if(currentNode == null || !currentNode.next.data.equals(element))
      throw new ElementNotFoundException("Element was not found!");
    
    //Get removed content
    T removed = currentNode.next.data;
    
    //Rearrange nodes
    currentNode.next = currentNode.next.next;
    this.count--;

    return removed;
  }
  
  /**
   * Remove an element from desired position
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
    LinearNode<T> currentNode = this.frontSentinel;
    
    for(int i = 0; i < position; i++)
      currentNode = currentNode.next;
    
    //Get removed content
    T removed = currentNode.next.data;
    
    //Rearrange nodes
    currentNode.next = currentNode.next.next;
    this.count--;

    return removed;
  }

  /**
   * Get an element from desired position
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
    LinearNode<T> currentNode = this.frontSentinel;
    
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
