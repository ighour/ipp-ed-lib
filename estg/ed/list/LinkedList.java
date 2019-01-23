/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.list;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.ListADT;
import estg.ed.nodes.DoubleLinearNode;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * List implemented with double linked elements.
 * @author igu
 * @param <T> generic
 */
public abstract class LinkedList<T> implements ListADT<T> {

  /**
   * Front sentinel of list.
   */
  protected DoubleLinearNode<T> frontSentinel;
  
  /**
   * Rear sentinel of list.
   */
  protected DoubleLinearNode<T> rearSentinel;
  
  /**
   * Elements in list.
   */
  protected int count;
  
  /**
   * Count modifications in list.
   */
  protected int modcount;
  
  /**
   * Instantiate the list.
   * Linking front and rear sentinels.
   */
  public LinkedList(){
    this.frontSentinel = new DoubleLinearNode<>();
    this.rearSentinel = new DoubleLinearNode<>();
    
    this.frontSentinel.next = this.rearSentinel;
    this.rearSentinel.previous = this.frontSentinel;
    
    this.modcount = 0;
  }
  
  /**
   * Removes and returns the first element from this list.
   * Removes first node after front sentinel.
   * @return the first element from this list
   * @throws estg.ed.exceptions.EmptyCollectionException list is empty
   */
  @Override
  public T removeFirst() throws EmptyCollectionException {
    if(this.count == 0)
      throw new EmptyCollectionException("List is empty!");
    
    //Save removed info
    DoubleLinearNode<T> removed = this.frontSentinel.next;
    
    //Remove
    this.frontSentinel.next = removed.next;
    removed.next.previous = this.frontSentinel;
    
    this.count--;
    this.modcount++;
    
    return removed.data;
  }

  /**
   * Removes and returns the last element from this list.
   * Removes first node before rear sentinel.
   * @return the last element from this list
   * @throws estg.ed.exceptions.EmptyCollectionException list is empty
   */
  @Override
  public T removeLast() throws EmptyCollectionException {
    if(this.count == 0)
      throw new EmptyCollectionException("List is empty!");
    
    //Save removed info
    DoubleLinearNode<T> removed = this.rearSentinel.previous;
    
    //Remove
    this.rearSentinel.previous = removed.previous;
    removed.previous.next = this.rearSentinel;
    
    this.count--;
    this.modcount++;
    
    return removed.data;
  }

  /**
   * Removes and returns the specified element from this list.
   * Removes desired node.
   * @param element the element to be removed from the list
   * @return the last element of list
   * @throws estg.ed.exceptions.EmptyCollectionException list is empty
   * @throws estg.ed.exceptions.ElementNotFoundException element was not found
   */
  @Override
  public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
    if(this.count == 0)
      throw new EmptyCollectionException("List is empty!");
    
    //Search element
    DoubleLinearNode<T> current = this.frontSentinel.next;
    
    while(!current.equals(this.rearSentinel)){
      //Found
      if(current.data.equals(element)){
        DoubleLinearNode<T> removed = current;
        
        //Remove
        current.previous.next = current.next;
        current.next.previous = current.previous;

        this.count--;
        this.modcount++;

        return removed.data;
      }
    }
    
    //Not found
    throw new ElementNotFoundException("Element was not found!");
  }

  /**
   * Returns a reference to the first element in this list.
   * Returns first node data after front sentinel.
   * @return a reference to the first element in this list
   * @throws estg.ed.exceptions.EmptyCollectionException list is empty
   */
  @Override
  public T first() throws EmptyCollectionException {
    if(this.count == 0)
      throw new EmptyCollectionException("List is empty!");
  
    return this.frontSentinel.next.data;
  }

  /**
   * Returns a reference to the last element in this list.
   * Returns first node data before rear sentinel.
   * @return a reference to the last element in this list
   * @throws estg.ed.exceptions.EmptyCollectionException list is empty
   */
  @Override
  public T last() throws EmptyCollectionException {
    if(this.count == 0)
      throw new EmptyCollectionException("List is empty!");
  
    return this.rearSentinel.previous.data;
  }

  /**
   * Returns true if this list contains the specified target element.
   * @param target the target that is being sought in the list
   * @return true if the list contains this element
   */
  @Override
  public boolean contains(T target) {
    //Search target
    DoubleLinearNode<T> current = this.frontSentinel.next;
    
    while(!current.equals(this.rearSentinel)){
      //Found
      if(current.data.equals(target))
        return true;
      
      current = current.next;
    }
    
    //Not found
    return false;
  }

  /**
   * Returns true if this list contains no elements.
   * @return true if this list contains no elements
   */
  @Override
  public boolean isEmpty() {
    return this.count == 0;
  }

  /**
   * Returns the number of elements in this list.
   * @return the integer representation of number of
   * elements in this list
   */
  @Override
  public int size() {
    return this.count;
  }

  /**
   * Returns an iterator for the elements in this list.
   * @return an iterator over the elements in this list
   */
  @Override
  public Iterator<T> iterator() {
    return new InnerIterator();
  }

  /**
   * Returns a string representation of this list.
   * @return a string representation of this list
   */
  @Override
  public String toString() {
    DoubleLinearNode<T> current = this.frontSentinel.next;

    StringBuilder stb = new StringBuilder();

    stb.append("[");

    while(!current.equals(this.rearSentinel)){
        stb.append("[").append(current.data.toString()).append("]");

        current = current.next;

        if(!current.equals(this.rearSentinel))
            stb.append(",");
    }

    stb.append("]");

    return stb.toString();
  }
  
  /**
   * Inner iterator for list.
   */
  class InnerIterator implements Iterator<T> {
    /**
     * Points to first element of list.
     */
    private DoubleLinearNode<T> pointer;
    
    /**
     * Expected modcount at operations.
     */
    private final int expectedModcount;
    
    /**
     * Reference to last next index.
     */
    private DoubleLinearNode<T> lastNext;
    
    /**
     * Flag to check if is ok to remove.
     */
    private boolean okToRemove;
    
    /**
     * Instantiates iterator.
     * Points to first node after front sentinel.
     * Store expectedModcount same as list.
     * Set lastNext as null (there is no lastNext).
     * Set not ok to remove.
     */
    public InnerIterator(){
      this.pointer = frontSentinel.next;
      this.expectedModcount = modcount;
      this.lastNext = null;
      this.okToRemove = false;
    }

    /**
     * Check if there is next.
     * @throws ConcurrentModificationException list has changed
     * @return true if there is next element in iterator
     */
    @Override
    public boolean hasNext() throws ConcurrentModificationException {
      //List has changed
      if(this.expectedModcount != modcount)
        throw new ConcurrentModificationException("List has changed while iterating.");
      
      //List is empty
      if(isEmpty())
        return false;
      
      //Check if there is next
      return !this.pointer.equals(rearSentinel);
    }

    /**
     * Get next element.
     * @throws ConcurrentModificationException list has changed
     * @throws NoSuchElementException there is no next element
     * @return next element in iterator
     */
    @Override
    public T next() throws ConcurrentModificationException, NoSuchElementException {
      //List has changed
      if(this.expectedModcount != modcount)
        throw new ConcurrentModificationException("List has changed while iterating.");

      //Check if there is next
      if(!this.hasNext())
        throw new NoSuchElementException("There is no more elements.");

      //Set pointer as last next
      this.lastNext = this.pointer;
      
      //Increment pointer
      this.pointer = this.pointer.next;
      
      //Set as removable (next is set)
      this.okToRemove = true;

      //Return next
      return this.lastNext.data;
    }

    /**
     * Remove element set as next.
     * @throws IllegalStateException Next element was not set
     */
    @Override
    public void remove() throws IllegalStateException{
      //Next was not set
      if(this.okToRemove == false)
        throw new IllegalStateException("Can only remove elements loaded by next() method.");

      //Remove item
      this.pointer.previous.next = this.pointer.next;
      this.pointer.next.previous = this.pointer.previous;

      //Block remove
      this.okToRemove = false;
    }
  }
}
