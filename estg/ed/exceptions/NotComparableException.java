/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estg.ed.exceptions;

/**
 * Element is not comparable.
 * @author igu
 */
public class NotComparableException extends Exception {
    public NotComparableException(){
        super();
    }
    public NotComparableException(String message){
        super(message);
    }
}
