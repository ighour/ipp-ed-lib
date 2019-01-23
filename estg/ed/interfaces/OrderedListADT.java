/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.NotComparableException;

/**
 * Contract for ordered lists.
 * @author igu
 * @param <T> generic
 */
public interface OrderedListADT<T> extends ListADT<T> {
  /**
   * Adds the specified element to this list at the proper location.
   * Element needs to implement Comparable interface.
   * @param element the element to be added to this list
   * @throws estg.ed.exceptions.NotComparableException element is not comparable
   */
  public void add(T element) throws NotComparableException;
}
