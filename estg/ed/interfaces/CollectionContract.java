/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

/**
 * Basic operations of a collection
 * @author ighour
 * @param <T>
 */
public interface CollectionContract<T> {   
  /**
   * Return collection size
   * @return 
   */
  public int size();

  /**
   * Check if collection is empty
   * @return 
   */
  public boolean isEmpty();

  /**
   * Prints the collection
   * @return 
   */
  @Override
  public String toString();
}
