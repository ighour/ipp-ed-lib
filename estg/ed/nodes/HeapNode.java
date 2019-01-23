/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.nodes;

/**
 * Node with reference to left child, right child and parent
 * @author igu
 * @param <T> generic
 */
public class HeapNode<T> extends BinaryTreeNode<T> {
  
  /**
   * Parent node reference.
   */
  public HeapNode<T> parent;
  
  /**
   * Create a node with data.
   * @param data content of node
   */
  public HeapNode(T data){
    super(data);
  }
}
