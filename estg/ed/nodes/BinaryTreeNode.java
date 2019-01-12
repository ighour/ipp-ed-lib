/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.nodes;

/**
 * Node with reference to left and right children.
 * @author igu
 * @param <T>
 */
public class BinaryTreeNode<T> {
  /**
   * Node data.
   */
  public T data;
  
  /**
   * Left child node reference.
   */
  public BinaryTreeNode<T> left;
  
  /**
   * Right child node reference.
   */
  public BinaryTreeNode<T> right;
  
  /**
   * Create a node with data.
   * @param data
   */
  public BinaryTreeNode(T data){
    this.data = data;
  }
  
  /**
   * Returns the number of children nodes in current tree.
   * Using recursion.
   * @return 
   */
  public int numChildren(){
    int children = 0;
    
    if(left != null)
      children = 1 + left.numChildren();
    
    if(right != null)
      children = 1 + right.numChildren();
    
    return children;
  }
}