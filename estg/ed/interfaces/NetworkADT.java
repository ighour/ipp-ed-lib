/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.ElementNotFoundException;

/**
 * Contract for networks.
 * Uses proper addEdge() with weights.
 * @author igu
 * @param <T>
 */
public interface NetworkADT<T> extends BaseGraphADT<T> {
  /**
   * Inserts an edge between two vertices of this graph.
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
   */
  public double shortestPathWeight(T vertex1, T vertex2) throws ElementNotFoundException;
}
