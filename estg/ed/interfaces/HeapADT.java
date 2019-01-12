/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.NotComparableException;

/**
 * Contract for heaps.
 * @author igu
 * @param <T>
 */
public interface HeapADT<T> extends BinaryTreeADT<T> {
  /**
   * Adds the specified object to this heap.
   * @param element the element to added to this head
   * @throws estg.ed.exceptions.NotComparableException
   */
  public void addElement(T element) throws NotComparableException;
}
