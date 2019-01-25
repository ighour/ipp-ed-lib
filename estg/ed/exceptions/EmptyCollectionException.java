package estg.ed.exceptions;

/**
 * Collection is empty.
 */
public class EmptyCollectionException extends Exception {

    public EmptyCollectionException() {
        super();
    }

    public EmptyCollectionException(String message) {
        super(message);
    }
}
