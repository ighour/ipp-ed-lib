/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.EmptyCollectionException;

/**
 * Contract for min heaps.
 * @author igu
 * @param <T>
 */
public interface HeapMinADT<T> extends HeapADT<T> {
  /**
   * Removes element with the lowest value from this heap.
   * @return the element with the lowest value from this heap
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  public T removeMin() throws EmptyCollectionException;

  /**
   * Returns a reference to the element with the lowest value in this heap.
   * @return a reference to the element with the lowest value in this heap
   * @throws estg.ed.exceptions.EmptyCollectionException
   */
  public T findMin() throws EmptyCollectionException;
}
