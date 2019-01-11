/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

/**
 * Basic operations of an array collection
 * @author ighour
 * @param <T>
 */
public interface CollectionArrayContract<T> extends CollectionContract<T> {   
  /**
   * Add an element to desired index
   * @param element 
   * @param index 
   * @throws IndexOutOfBoundsException 
   */
  public void add(T element, int index) throws IndexOutOfBoundsException;

  /**
   * Remove an element from desired index
   * @param index
   * @return 
   * @throws IndexOutOfBoundsException 
   */
  public T remove(int index) throws IndexOutOfBoundsException;

  /**
   * Get an element from desired index
   * @param index
   * @return 
   * @throws IndexOutOfBoundsException 
   */
  public T get(int index) throws IndexOutOfBoundsException;
}
