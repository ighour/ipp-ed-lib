/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

/**
 * Contract for dynamic arrays.
 * Which redimension itself when needed.
 * @author igu
 * @param <T>
 */
public interface DynamicArrayContract<T> {
  /**
   * Add an element to desired index.
   * Increases array size if needed.
   * Push elements in array if needed.
   * @param element
   * @param index
   * @throws IndexOutOfBoundsException 
   */
  public void add(T element, int index) throws IndexOutOfBoundsException;

  /**
   * Remove an element from desired index.
   * Pull elements in array if needed.
   * @param index
   * @return 
   * @throws IndexOutOfBoundsException 
   */
  public T remove(int index) throws IndexOutOfBoundsException;
  
  /**
   * Change content of desired index.
   * @param element
   * @param index
   * @throws IndexOutOfBoundsException 
   */
  public void change(T element, int index) throws IndexOutOfBoundsException;

  /**
   * Get an element from desired index.
   * @param index
   * @return 
   * @throws IndexOutOfBoundsException 
   */
  public T get(int index) throws IndexOutOfBoundsException;

  /**
   * Get length of array.
   * Only counts valid indexes used by dynamic array.
   * @return 
   */
  public int size();

  /**
   * Check if array is empty.
   * Only counts valid indexes used by dynamic array.
   * @return 
   */
  public boolean isEmpty();

  /**
   * Returns the string representation of the dynamic array.
   * @return a string representation of the dynamic array
   */
  @Override
  public String toString();
}
