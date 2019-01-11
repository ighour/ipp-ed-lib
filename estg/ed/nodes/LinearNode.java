/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.nodes;

/**
 * Node with reference to next node.
 * @author igu
 * @param <T>
 */
public class LinearNode<T> {
  /**
   * Node data.
   */
  public T data;
  
  /**
   * Next node reference.
   */
  public LinearNode<T> next;

  /**
   * Create a node without data.
   */
  public LinearNode(){}

  /**
   * Create a node with data.
   * @param data 
   */
  public LinearNode(T data){
      this.data = data;
  }
}
