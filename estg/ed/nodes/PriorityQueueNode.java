/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.nodes;

/**
 * Node which implements comparable of itself.
 * Priority uses double type.
 * @author igu
 * @param <T> content type
 */
public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode> {
  
  /**
   * Order for new PriorityQueueNode instance.
   */
  private static int nextOrder = 0;
  
  /**
   * Node priority.
   */
  public final double priority;
  
  /**
   * 2nd attribute to compare, using order of instantiation.
   */
  public final int order;
  
  /**
   * Node data.
   */
  public final T data;
  
  /**
   * Instantiates with data and priority
   * @param data content of node
   * @param priority double value of priority
   */
  public PriorityQueueNode(T data, double priority){
    this.data = data;
    this.priority = priority;
    this.order = nextOrder;
    nextOrder++;
  }
  
  /**
   * Returns a string representation of this node.
   * Returns result from data toString(), priority and order.
   * @return a string representation of this node
   */  
  @Override
  public String toString(){
    return "{{" + this.data.toString() + "}:{" + this.priority + "}:{" + this.order + "}}";
  }
  
  /**
   * Compare node.
   * @return 1 if is bigger. -1 if is smaller. 0 if is equivalent.
   */  
  @Override
  public int compareTo(PriorityQueueNode o) {
    if(this.priority > o.priority)
      return 1;
    
    else if(this.priority < o.priority)
      return -1;
    
    else if(this.order > o.order)
      return 1;
    
    return -1;
  }
}
