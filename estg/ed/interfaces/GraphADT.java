/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

import estg.ed.exceptions.ElementNotFoundException;

/**
 * Contract for graphs.
 * Uses proper addEdge() without weights.
 * @author igu
 * @param <T>
 */
public interface GraphADT<T> extends BaseGraphADT<T> {
  /**
   * Inserts an edge between two vertices of this graph.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @throws estg.ed.exceptions.ElementNotFoundException
   */
  public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException;
}
