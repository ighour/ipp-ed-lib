/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.graph;

import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.NetworkADT;
import estg.ed.interfaces.PriorityQueueADT;
import estg.ed.tree.binary.ArrayPriorityMinQueue;

/**
 * Implements a bidirectional network with an adjacency matrix.
 * @author igu
 * @param <T>
 */
public class BidirectionalNetwork<T> extends Network<T> implements NetworkADT<T> {
  /**
   * Inserts an edge between two vertices of this network.
   * Using weights at edges.
   * Edge is bidirectional.
   * Throws ElementNotFoundException if vertex is not found at network.
   * Uses changeEdge() method to handle edge update.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @param weight the weight
   * @throws estg.ed.exceptions.ElementNotFoundException
   */
  @Override
  public void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException {    
    this.changeEdge(vertex1, vertex2, weight);
  }

  /**
   * Removes an edge between two vertices of this network.
   * Edge is bidirectional.
   * Throws ElementNotFoundException if vertex is not found at network.
   * Uses changeEdge() method to handle edge update.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @throws estg.ed.exceptions.ElementNotFoundException
   */
  @Override
  public void removeEdge(T vertex1, T vertex2) throws ElementNotFoundException {
    this.changeEdge(vertex1, vertex2, Double.POSITIVE_INFINITY);
  }
  
/**
 * Change edge value.
 * Helper method to change an edge to desired double value.
 * Used by addEdge() and removeEdge() methods.
 * Throws ElementNotFoundException if vertex is not found at network.
 * @param vertex1
 * @param vertex2
 * @param newValue
 * @throws ElementNotFoundException 
 */
  @Override
  protected void changeEdge(T vertex1, T vertex2, double newValue) throws ElementNotFoundException {
    //Get vertices indexes
    int[] indexes = this.getIndex(vertex1, vertex2);
    
    //Check if is both valid
    if(indexes[0] < 0 || indexes[1] < 0)
      throw new ElementNotFoundException("Vertex was not found.");
    
    //Change value of vertices edges at adjacency matrix
    this.adjMatrix.get(indexes[0]).change(newValue, indexes[1]);
    this.adjMatrix.get(indexes[1]).change(newValue, indexes[0]);
  }
  
  /**
   * Returns a minimum spanning tree of the network from desired element.
   * Preference is to less weighted edge.
   * Uses recursion.
   * Similar to iterator BFS, but using greedy technic to select next vertex instead.
   * @param vertex
   * @return 
   * @throws estg.ed.exceptions.ElementNotFoundException 
   */
  @Override
  public NetworkADT<T> mstNetwork(T vertex) throws ElementNotFoundException {
    //Get index
    int index = this.getIndex(vertex);
    
    //Index is invalid
    if(index < 0)
      throw new ElementNotFoundException("Vertex was not found!");
    
   //Generate result graph
    NetworkADT<T> resultGraph = new BidirectionalNetwork<>();
        
    //Generate a priority min queue to store the weighted edges
    PriorityQueueADT<Integer> traverseQueue = new ArrayPriorityMinQueue<>();
    
    //Get size
    int size = this.vertices.size();
    
    //Generate visited boolean array
    boolean[] visited = new boolean[size];
    for(int i = 0; i < visited.length; i++)
      visited[i] = false;
    
    //Generate antecessor boolean array
    int[] antecessor = new int[size];
    for(int i = 0; i < antecessor.length; i++)
      antecessor[i] = -1;
    
    //Enqueue first item
    traverseQueue.enqueue(index, 0);
    
    //Iterate
    this.iterateMST(resultGraph, traverseQueue, visited, antecessor);
        
    //Return result
    return resultGraph;
  }
}
