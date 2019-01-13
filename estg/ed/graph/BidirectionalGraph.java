/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.graph;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.interfaces.GraphADT;

/**
 * Implements a bidirectional graph with an adjacency matrix.
 * @author igu
 * @param <T>
 */
public class BidirectionalGraph<T> extends Graph<T> implements GraphADT<T> {
  /**
   * Inserts an edge between two vertices of this graph.
   * Edge is bidirectional, so addEdge(A,B) is equal to addEdge(B,A).
   * Throws ElementNotFoundException if vertex is not found at graph.
   * Uses changeEdge() method to handle edge update.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @throws estg.ed.exceptions.ElementNotFoundException
   */
  @Override
  public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException {   
    this.changeEdge(vertex1, vertex2, true);
  }

  /**
   * Removes an edge between two vertices of this graph.
   * Edge is bidirectional, so addEdge(A,B) is equal to addEdge(B,A).
   * Throws ElementNotFoundException if vertex is not found at graph.
   * Uses changeEdge() method to handle edge update.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @throws estg.ed.exceptions.ElementNotFoundException
   */
  @Override
  public void removeEdge(T vertex1, T vertex2) throws ElementNotFoundException {
    this.changeEdge(vertex1, vertex2, false);
  }
  
/**
 * Change edge value.
 * Helper method to change an edge to desired boolean value.
 * Used by addEdge() and removeEdge() methods.
 * Throws ElementNotFoundException if vertex is not found at graph.
 * @param vertex1
 * @param vertex2
 * @param newValue
 * @throws ElementNotFoundException 
 */
  @Override
  protected void changeEdge(T vertex1, T vertex2, boolean newValue) throws ElementNotFoundException {
    //Get vertices indexes
    int[] indexes = this.getIndex(vertex1, vertex2);
    
    //Check if is both valid
    if(indexes[0] < 0 || indexes[1] < 0)
      throw new ElementNotFoundException("Vertex was not found.");
    
    //Change value of vertices edges at adjacency matrix
    //On both directions
    this.adjMatrix.get(indexes[0]).change(newValue, indexes[1]);
    this.adjMatrix.get(indexes[1]).change(newValue, indexes[0]);
  }
}
