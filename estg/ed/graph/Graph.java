/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.graph;

import estg.ed.array.DynamicArrayCircular;
import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.interfaces.DynamicArrayContract;
import estg.ed.interfaces.GraphADT;
import estg.ed.interfaces.QueueADT;
import estg.ed.interfaces.StackADT;
import estg.ed.interfaces.UnorderedListADT;
import estg.ed.list.UnorderedArrayList;
import estg.ed.queue.ArrayQueue;
import estg.ed.stack.ArrayStack;
import java.util.Iterator;

/**
 * Implements a directional graph with an adjacency matrix.
 * @author igu
 * @param <T> generic
 */
public class Graph<T> extends BaseGraph<T> implements GraphADT<T> {

  /**
   * Dynamic array to store adjacent matrix.
   * Uses a dynamic array of dynamic array.
   * Using already implemented circular dynamic array.
   */
  protected DynamicArrayContract<DynamicArrayContract<Boolean>> adjMatrix;
  
  /**
  * Instantiates an empty graph.
  */
  public Graph() {
    super();
    this.adjMatrix = new DynamicArrayCircular<>();
  }
  
  /**
   * Adds a vertex to this graph, associating object with vertex.
   * @param vertex the vertex to be added to this graph
   */
  @Override
  public void addVertex(T vertex) {
    //Insert vertex space into adjacency matrix
    this.adjMatrix.add(new DynamicArrayCircular<>(), this.adjMatrix.size());
    
    //Add vertice to vertices list at end
    this.vertices.add(vertex, this.vertices.size());

    //Set new vertice adjacencys as false
    int size = this.adjMatrix.size();
    for(int i = 0; i < size; i++){
      //Set all on new vertex column to false (is the last index of each row)
      this.adjMatrix.get(i).add(false, size - 1);
      
      //Set all on new vertex row to false (all the last row)
      this.adjMatrix.get(size - 1).add(false, i);
    }
  }

  /**
   * Removes a single vertex with the given value from this graph.
   * Throws ElementNotFoundException if vertex is not found at graph.
   * @param vertex the vertex to be removed from this graph
   * @throws estg.ed.exceptions.ElementNotFoundException vertex was not found
   */
  @Override
  public void removeVertex(T vertex) throws ElementNotFoundException {
    //Get vertex index
    int index = this.getIndex(vertex);
    
    //Not found
    if(index < 0)
      throw new ElementNotFoundException("Vertex was not found!");

    //Remove vertex from vertices list
    this.vertices.remove(index);
    
    //Remove vertex row from adjacency matrix
    this.adjMatrix.remove(index);
    
    //Remove vertex column from adjacency matrix
    int size = this.adjMatrix.size();
    for(int i = 0; i < size; i++){
      this.adjMatrix.get(i).remove(index);
    }
  }

  /**
   * Inserts an edge between two vertices of this graph.
   * Edge is directional, so addEdge(A,B) is different from addEdge(B,A).
   * Throws ElementNotFoundException if vertex is not found at graph.
   * Uses changeEdge() method to handle edge update.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @throws estg.ed.exceptions.ElementNotFoundException one of vertices were not found
   */
  @Override
  public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException {    
    this.changeEdge(vertex1, vertex2, true);
  }

  /**
   * Removes an edge between two vertices of this graph.
   * Edge is directional, so removeEdge(A,B) is different from removeEdge(B,A).
   * Throws ElementNotFoundException if vertex is not found at graph.
   * Uses changeEdge() method to handle edge update.
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @throws estg.ed.exceptions.ElementNotFoundException one of vertices were not found
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
 * @param vertex1 vertex at start of edge
 * @param vertex2 vertex at end of edge
 * @param newValue new weight value of edge
 * @throws ElementNotFoundException one of vertices were not found
 */
  protected void changeEdge(T vertex1, T vertex2, boolean newValue) throws ElementNotFoundException {
    //Get vertices indexes
    int[] indexes = this.getIndex(vertex1, vertex2);
    
    //Check if is both valid
    if(indexes[0] < 0 || indexes[1] < 0)
      throw new ElementNotFoundException("Vertex was not found.");
    
    //Change value of vertices edges at adjacency matrix
    //Only at direction vertex1 -> vertex2
    this.adjMatrix.get(indexes[0]).change(newValue, indexes[1]);
  }

  /**
   * Returns a breadth first iterator starting with the given vertex.
   * Uses recursion.
   * @param startVertex the starting vertex
   * @return a breadth first iterator beginning at the given vertex
   */
  @Override
  //Breadth First Search (X, Y, Xl, Xr, Yl, Yr, Xll, Xlr...)
  public Iterator iteratorBFS(T startVertex) {
    //Get vertex index
    int index = this.getIndex(startVertex);
    
    //Generate result list
    UnorderedListADT<T> resultList = new UnorderedArrayList<>();
    
    //If index is invalid, return result list iterator (empty)
    if(index < 0)
      return resultList.iterator();
    
    //Generate traversal queue
    QueueADT<Integer> traversalQueue = new ArrayQueue<>();
    
    //Generate visited boolean array
    boolean[] visited = new boolean[this.vertices.size()];
    for(int i = 0; i < visited.length; i++)
      visited[i] = false;
    
    //Enqueue first item and set as visited
    traversalQueue.enqueue(index);
    visited[index] = true;
    
    //Start recursion
    this.iterateBFS(resultList, traversalQueue, visited);

    //Return iterator
    return resultList.iterator();
  }
  
  private void iterateBFS(UnorderedListADT<T> resultList, QueueADT<Integer> traversalQueue, boolean[] visited) {
    //Dequeue item
    int index;
    
    try {
      index = traversalQueue.dequeue();
      
    } catch (EmptyCollectionException ex) {
      //Stop if queue is empty
      return;
    }
    
    //Add index element to result
    resultList.addToRear(this.vertices.get(index));
    
    //Look for not visited neighbors to add to queue
    int size = this.vertices.size();
    for(int i = 0; i < size; i++){
      //Get current to neightbor edge relation and check if was visited
      if(this.adjMatrix.get(index).get(i) && !visited[i]){
        //Add neighbor to queue and set as visited
        traversalQueue.enqueue(i);
        visited[i] = true;
      }
    }
    
    //Proceed in recursion if there is vertices in queue to visit
    if(!traversalQueue.isEmpty())
      this.iterateBFS(resultList, traversalQueue, visited);
  }

  /**
   * Returns a depth first iterator starting with the given vertex.
   * Uses recursion.
   * @param startVertex the starting vertex
   * @return a depth first iterator starting at the given vertex
   */
  @Override
  //Depth First Search (X, Xl, Xll, Xlr, Xr, Y, Yl, Yr...)
  public Iterator iteratorDFS(T startVertex) {
    //Get vertex index
    int index = this.getIndex(startVertex);
    
    //Generate result list
    UnorderedListADT<T> resultList = new UnorderedArrayList<>();
    
    //If index is invalid, return result list iterator (empty)
    if(index < 0)
      return resultList.iterator();
    
    //Generate traversal stack
    StackADT<Integer> traversalStack = new ArrayStack<>();
    
    //Generate visited boolean array
    boolean[] visited = new boolean[this.vertices.size()];
    for(int i = 0; i < visited.length; i++)
      visited[i] = false;
    
    //Push first item and set as visited
    traversalStack.push(index);
    visited[index] = true;
    
    //Start recursion
    this.iterateDFS(resultList, traversalStack, visited);

    //Return iterator
    return resultList.iterator();
  }
  
  private void iterateDFS(UnorderedListADT<T> resultList, StackADT<Integer> traversalStack, boolean[] visited) {
    //Pop item
    int index;
    
    try {
      index = traversalStack.pop();
      
    } catch (EmptyCollectionException ex) {
      //Stop if stack is empty
      return;
    }
    
    //Add to result
    resultList.addToRear(this.vertices.get(index));
    
    //Look for not visited neighbors to add to stack
    int size = this.vertices.size();
    for(int i = 0; i < size; i++){
      //Get current to neightbor edge relation and check if was visited
      if(this.adjMatrix.get(index).get(i) && !visited[i]){
        //Add neighbor to stack and set as visited
        traversalStack.push(i);
        visited[i] = true;
      }
    }   
    
    //Proceed in recursion if there is vertices in stack to visit
    if(!traversalStack.isEmpty())
      this.iterateDFS(resultList, traversalStack, visited);
  }

  /**
   * Returns an iterator that contains the shortest path between the two vertices.
   * Similar to iterator BFS, but using cumulative path length.
   * @param startVertex the starting vertex
   * @param targetVertex the ending vertex
   * @return an iterator that contains the shortest path between the two vertices
   */
  @Override
  public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
    //Get indexes
    int[] indexes = this.getIndex(startVertex, targetVertex);
    
    //Generate result list
    UnorderedListADT<T> resultList = new UnorderedArrayList<>();
    
    //If index is invalid, return result list iterator (empty)
    if(indexes[0] < 0 || indexes[1] < 0)
      return resultList.iterator();
    
    //Generate traversal queue
    QueueADT<Integer> traversalQueue = new ArrayQueue<>();
    
    //Generate visited boolean array
    boolean[] visited = new boolean[this.vertices.size()];
    for(int i = 0; i < visited.length; i++)
      visited[i] = false;
    
    //Generate path lengths array
    int[] pathLength = new int[this.vertices.size()];
    for(int i = 0; i < pathLength.length; i++)
      pathLength[i] = -1;
    
    //Generate antecessor array
    int[] antecessor = new int[this.vertices.size()];
    for(int i = 0; i < antecessor.length; i++)
      antecessor[i] = -1;
    
    //Enqueue first item and set as visited and set path length
    traversalQueue.enqueue(indexes[0]);
    visited[indexes[0]] = true;
    pathLength[indexes[0]] = 0;
    
    //Start recursion
    this.iterateSP(traversalQueue, visited, pathLength, antecessor, indexes[1]);
    
    //Successfully achieved target
    if(antecessor[indexes[1]] != -1){
      //Add path based on antecessors
      int currentIndex = indexes[1];
      while(currentIndex != -1){
        resultList.addToFront(this.vertices.get(currentIndex));
        currentIndex = antecessor[currentIndex];
      }
    }
       
    //Return iterator
    return resultList.iterator();
  }
  
  private void iterateSP(QueueADT<Integer> traversalQueue, boolean[] visited, int[] pathLength, int[] antecessor, int target) {
    //Dequeue item
    int index;
    
    try {
      index = traversalQueue.dequeue();
      
    } catch (EmptyCollectionException ex) {
      //Stop if queue is empty
      return;
    }
    
    //Look for not visited neighbors to add to queue
    int size = this.vertices.size();
    for(int i = 0; i < size; i++){
      //Get current to neightbor edge relation and check if was visited
      if(this.adjMatrix.get(index).get(i) && !visited[i]){
        //Add neighbor to queue and set as visited
        traversalQueue.enqueue(i);
        visited[i] = true;
        
        //Get already existent cost
        int oldCost = pathLength[i];
        
        //Get new cost
        int newCost = pathLength[index] + 1;
        
        //Compare costs
        //There is no old cost or new cost is smaller then old cost
        if(oldCost == -1 || newCost < oldCost){
          //Set newCost as used cost
          pathLength[i] = newCost;
          
          //Updates antecessor reference
          antecessor[i] = index;
        }
        
        //Stops if target was adquired
        if(i == target)
          return;
      }
    }
    
    //Proceed in recursion if there is vertices in queue to visit
    if(!traversalQueue.isEmpty())
      this.iterateSP(traversalQueue, visited, pathLength, antecessor, target);
  }

  /**
   * Returns true if this graph is connected, false otherwise.
   * @return true if this graph is connected
   */
  @Override
  public boolean isConnected() {
    int size = this.vertices.size();
       
    for(int i = 0; i < size; i++){
      //Check in matrix for a x-column without relations (except itself)
      boolean connectTo = false;
      
      //Check in matrix for a y-column without relations (except itself)
      boolean connectFrom = false;
      
      for(int j = 0; j < size; j++){
        //Pass itself
        if(i == j)
          continue;
        
        //It is pointing to another vertice
        if(this.adjMatrix.get(i).get(j) == true)
          connectTo = true;
        
        //It is being pointer by another vertice
        if(this.adjMatrix.get(j).get(i) == true)
          connectFrom = true;
      }
      
      //There is a vertice without pointing to any other (x-column)
      //Or without being pointed by any other (y-column)
      if(connectTo == false || connectFrom == false)
        return false;
    }
    
    return true;
  }
  
  /**
   * Returns a string representation of the adjacency matrix.
   * @return a string representation of the adjacency matrix
   */
  @Override
  public String toString(){
    StringBuilder stb = new StringBuilder();
    
    stb.append("\t");
    
    int size = this.vertices.size();
    for(int i = 0; i < size; i++){
      stb.append(this.vertices.get(i)).append("\t");
    }
    
    stb.append("\n");
    
    for(int i = 0; i < size; i++){      
      for(int j = 0; j < size; j++){
        if(j == 0)
          stb.append(this.vertices.get(i)).append("\t");
        
        if(this.adjMatrix.get(i).get(j) == true)
          stb.append("1");
        else
          stb.append("");
        
        stb.append("\t");
      }
      
      stb.append("\n");
    }
  
    return stb.toString();
  }
}
