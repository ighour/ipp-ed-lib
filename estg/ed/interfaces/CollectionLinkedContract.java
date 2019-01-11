/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.ElementNotFoundException;

/**
 * Basic operations of an array collection
 * @author ighour
 * @param <T>
 */
public interface CollectionLinkedContract<T> extends CollectionContract<T> {   
  /**
   * Add an element to front
   * @param element 
   */
  public void addToFront(T element);
  
  /**
   * Add an element to rear
   * @param element 
   */
  public void addToRear(T element);

  /**
   * Add an element after another element
   * @param element 
   * @param target 
   * @throws ElementNotFoundException 
   */
  public void addAfter(T element, T target) throws ElementNotFoundException;

  /**
   * Remove an element
   * @param element
   * @return 
   * @throws ElementNotFoundException 
   */
  public T remove(T element) throws ElementNotFoundException;
  
  /**
   * Remove an element from desired position
   * @param position
   * @return 
   * @throws ElementNotFoundException 
   */
  public T removePosition(int position) throws ElementNotFoundException;

  /**
   * Get an element from desired position
   * @param position
   * @return 
   * @throws ElementNotFoundException 
   */
  public T get(int position) throws ElementNotFoundException;
}
