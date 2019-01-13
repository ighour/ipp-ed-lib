/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.VertexIsNotAccessibleException;

/**
 * Contract for networks.
 * Uses proper addEdge() with weights.
 * @author igu
 * @param <T>
 */
public interface NetworkADT<T> extends BaseGraphADT<T> {
  /**
   * Inserts an edge between two vertices of this network.
   * Using weights at edges.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @param weight the weight
   * @throws estg.ed.exceptions.ElementNotFoundException
   */
  public void addEdge (T vertex1, T vertex2, double weight) throws ElementNotFoundException;

   /**
   * Returns the weight of the shortest path in this network.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @return the weight of the shortest path in this network
   * @throws estg.ed.exceptions.ElementNotFoundException
   * @throws estg.ed.exceptions.VertexIsNotAccessibleException
   */
  public double shortestPathWeight(T vertex1, T vertex2) throws ElementNotFoundException, VertexIsNotAccessibleException;
  
  /**
   * Returns a minimum spanning tree of the network from desired element.
   * @param vertex
   * @return 
   * @throws estg.ed.exceptions.ElementNotFoundException 
   */
  public NetworkADT<T> mstNetwork(T vertex) throws ElementNotFoundException;
}
