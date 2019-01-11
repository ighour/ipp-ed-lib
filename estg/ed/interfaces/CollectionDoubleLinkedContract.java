/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.ElementNotFoundException;

/**
 * Basic operations of an array collection.
 * @author ighour
 * @param <T>
 */
public interface CollectionDoubleLinkedContract<T> extends CollectionLinkedContract<T> {   
  /**
   * Add an element before another element.
   * @param element 
   * @param target 
   * @throws ElementNotFoundException 
   */
  public void addBefore(T element, T target) throws ElementNotFoundException;
}
