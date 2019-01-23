/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.EmptyCollectionException;

/**
 * Contract for max heaps.
 * @author igu
 * @param <T> generic
 */
public interface HeapMaxADT<T> extends HeapADT<T> {
  /**
   * Removes element with the highest value from this heap.
   * @return the element with the highest value from this heap
   * @throws estg.ed.exceptions.EmptyCollectionException if heap is empty
   */
  public T removeMax() throws EmptyCollectionException;

  /**
   * Returns a reference to the element with the highest value in this heap.
   * @return a reference to the element with the highest value in this heap
   * @throws estg.ed.exceptions.EmptyCollectionException if heap is empty
   */
  public T findMax() throws EmptyCollectionException;
}
