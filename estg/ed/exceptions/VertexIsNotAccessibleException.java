package estg.ed.exceptions;

/**
 * Vertex is not accessible in graph from another vertex.
 */
public class VertexIsNotAccessibleException extends Exception {

    public VertexIsNotAccessibleException() {
        super();
    }

    public VertexIsNotAccessibleException(String message) {
        super(message);
    }
}
