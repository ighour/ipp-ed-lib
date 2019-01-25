package estg.ed.exceptions;

/**
 * Element was not found.
 */
public class ElementNotFoundException extends Exception {

    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String message) {
        super(message);
    }
}
