/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.nodes;

/**
 * Node with reference to next and previous nodes.
 * @author igu
 * @param <T>
 */
public class DoubleLinearNode<T> {
  /**
   * Node data.
   */
  public T data;
  
  /**
   * Next node reference.
   */
  public DoubleLinearNode<T> next;
  
  /**
   * Previous node reference.
   */
  public DoubleLinearNode<T> previous;

  /**
   * Create a node without data.
   */
  public DoubleLinearNode(){}

  /**
   * Create a node with data.
   * @param data 
   */
  public DoubleLinearNode(T data){
    this.data = data;
  }
}
