/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.exceptions;

/**
 * Vertex is not accessible in graph from another vertex.
 * @author igu
 */
public class VertexIsNotAccessibleException extends Exception {
    public VertexIsNotAccessibleException(){
        super();
    }
    public VertexIsNotAccessibleException(String message){
        super(message);
    }
}
