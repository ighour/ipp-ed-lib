/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.graph;

import estg.ed.array.DynamicArrayCircular;
import estg.ed.interfaces.BaseGraphADT;
import estg.ed.interfaces.DynamicArrayContract;

/**
 * Implements a base for graphs and networks with an adjacency matrix.
 * @author igu
 * @param <T>
 */
public abstract class BaseGraph<T> implements BaseGraphADT<T> {

  /**
   * Dynamic array to store vertices values.
   * Using already implemented circular dynamic array.
   */
  protected DynamicArrayContract<T> vertices;
  
  /**
  * Instantiates an empty graph.
  */
  public BaseGraph() {
    this.vertices = new DynamicArrayCircular<>();
  }

  /**
   * Returns true if this graph is empty, false otherwise.
   * @return true if this graph is empty
   */
  @Override
  public boolean isEmpty() {
    return this.vertices.size() == 0;
  }

  /**
   * Returns the number of vertices in this graph.
   * @return the integer number of vertices in this graph
   */
  @Override
  public int size() {
    return this.vertices.size();
  }
  
  /**
   * Get index of a vertex.
   * @param vertex1
   * @return 
   */  
  protected int getIndex(T vertex1){
    int size = this.vertices.size();
    for(int i = 0; i < size; i++){
      T compared = this.vertices.get(i);
      
      if(compared.equals(vertex1))
        return i;
    }
    
    return -1;
  }
  
  /**
   * Get indexes of a pair of vertex.
   * @param vertex1
   * @param vertex2
   * @return 
   */  
  protected int[] getIndex(T vertex1, T vertex2){
    int[] result = new int[2];
    result[0] = -1;
    result[1] = -1;
    
    int size = this.vertices.size();
    for(int i = 0; i < size; i++){
      T compared = this.vertices.get(i);
      
      if(compared.equals(vertex1)){
        result[0] = i;
      }
      
      if(compared.equals(vertex2)){
        result[1] = i;
      }
      
      if(result[0] != -1 && result[1] != -1)
        break;
    }
    
    return result;
  }
}