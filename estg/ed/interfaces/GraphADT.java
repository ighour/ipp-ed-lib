package estg.ed.interfaces;

import estg.ed.exceptions.ElementNotFoundException;

/**
 * Contract for graphs. Uses proper addEdge() without weights.
 *
 * @param <T> generic
 */
public interface GraphADT<T> extends BaseGraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws estg.ed.exceptions.ElementNotFoundException one of vertices were
     * not found
     */
    public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException;
}
