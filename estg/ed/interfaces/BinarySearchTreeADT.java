/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.exceptions.NotComparableException;

/**
 * Contract for binary search tree.
 * @author igu
 * @param <T> generic
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {
  /**
   * Adds the specified element to the proper location in this tree.
   * @param element the element to be added to this tree
   * @throws estg.ed.exceptions.NotComparableException element is not comparable
   */
  public void addElement(T element) throws NotComparableException;
 
  /**
   * Removes and returns the specified element from this tree.
   * @param targetElement the element to be removed from this tree
   * @return the element removed from this tree
   * @throws estg.ed.exceptions.ElementNotFoundException element was not found
   * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
   * @throws estg.ed.exceptions.NotComparableException element is not comparable
   */
  public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException, NotComparableException;
  
  /**
   * Removes all occurences of the specified element from this tree.
   * @param targetElement the element that the list will have all instances of it removed
   * @throws estg.ed.exceptions.NotComparableException element is not comparable
   */
  public void removeAllOccurrences(T targetElement) throws NotComparableException;
  
  /**
   * Removes and returns the smallest element from this tree.
   * @return the smallest element from this tree.
   * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
   */
  public T removeMin() throws EmptyCollectionException;
 
  /**
   * Removes and returns the largest element from this tree.
   * @return the largest element from this tree
   * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
   */
  public T removeMax() throws EmptyCollectionException;
  
  /**
   * Returns a reference to the smallest element in this tree.
   * @return a reference to the smallest element in this tree
   * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
   */
  public T findMin() throws EmptyCollectionException;
  
  /**
   * Returns a reference to the largest element in this tree.
   * @return a reference to the largest element in this tree
   * @throws estg.ed.exceptions.EmptyCollectionException tree is empty
   */
  public T findMax() throws EmptyCollectionException;
}
