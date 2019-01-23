/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.interfaces;

/**
 * Contract for Queues.
 * @author igu
 * @param <T> generic
 */
public interface QueueADT<T> extends BaseQueueADT<T> {
  /** 
   * Adds one element to the rear of this queue.
   * @param element the element to be added to the rear of this queue
   */
   public void enqueue(T element);
}
