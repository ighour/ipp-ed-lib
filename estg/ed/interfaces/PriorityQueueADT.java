/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

/**
 * Contract for Priority Queues.
 * Uses double type for priority.
 * @author igu
 * @param <T> generic
 */
public interface PriorityQueueADT<T> extends BaseQueueADT<T> {
  /** 
   * Adds one element to this priority queue.
   * Uses double type for priority.
   * @param element the element to be added to this queue
   * @param priority double value of priority
   */
   public void enqueue(T element, double priority);
}
