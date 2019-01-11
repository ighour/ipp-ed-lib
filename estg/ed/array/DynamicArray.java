/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.array;

/**
 * Dynamically expands array when needed
 * @author igu
 * @param <T>
 */
public class DynamicArray<T> {
  /**
   * Array default size.
   */
  private static final int DEFAULT_SIZE = 20;
  
  /**
   * Array to store data.
   */
  T[] collection;
  
  /**
   * Reference to next empty position in array.
   */
  int next;

  /**
   * Generates a dynamic array with default size.
   */
  public DynamicArray(){
    this(DEFAULT_SIZE);
  }

  /**
   * Generates a dynamic array with desired size.
   * @param size
   */
  public DynamicArray(int size){
    this.collection = (T[]) new Object[size];
    this.next = 0;
  }

  /**
   * Add an element to desired index.
   * Index need to be between 0 and next position.
   * Increases array size if needed.
   * Push elements in array if is not the next available index
   * Throws IndexOutOfBoundsException if index is invalid.
   * @param element
   * @param index
   * @throws IndexOutOfBoundsException 
   */
  public void add(T element, int index) throws IndexOutOfBoundsException {
    //Check if index is allowed
    if(index < 0 || index > this.next)
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");

    //Check if need to expand array
    this.checkNeedToIncrease();

    //Push elements from desired position one index after
    for(int i = this.next; i > index; i--){
        this.collection[i] = this.collection[i-1];
    }

    //Add new element
    this.collection[index] = element;

    //Point to next empty position
    this.next++;
  }

  /**
   * Remove an element from desired index.
   * Index need to be between 0 and next position.
   * Pull elements in array if is not the last index
   * Throws IndexOutOfBoundsException if index is invalid.
   * @param index
   * @return 
   * @throws IndexOutOfBoundsException 
   */
  public T remove(int index) throws IndexOutOfBoundsException {
    //Check if index is allowed
    if(index < 0 || index >= this.next)
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");

    //Select element
    T element = this.collection[index];

    //Pull elements from desired position one index before
    for(int i = index; i < this.next - 1; i++){
        this.collection[i] = this.collection[i+1];
    }

    //Point next to one position before
    this.next--;
    
    //Set last element as null
    this.collection[this.next] = null;

    return element;
  }

  /**
   * Get an element from desired index.
   * Index need to be between 0 and next position.
   * Throws IndexOutOfBoundsException if index is invalid.
   * @param index
   * @return 
   * @throws IndexOutOfBoundsException 
   */
  public T get(int index) throws IndexOutOfBoundsException {
    //Check if index is allowed
    if(index < 0 || index >= this.next)
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");

    return this.collection[index];
  }

  public int size() {
      return this.next;
  }

  public boolean isEmpty() {
      return this.next == 0;
  }

  public String toString(){
      StringBuilder stb = new StringBuilder();

      stb.append("[");

      for(int i = 0; i < this.next; i++){
          stb.append("[").append(this.collection[i].toString()).append("]");

          if(i < this.next - 1)
              stb.append(",");
      }

      stb.append("]");

      return stb.toString();
  }

  private void checkNeedToIncrease(){
    //Need to Increase
    if(this.next >= this.collection.length){
      T[] newCollection = (T[]) new Object[this.collection.length * 2];

      System.arraycopy(this.collection, 0, newCollection, 0, this.collection.length);

      this.collection = newCollection;
    }
  }
}
