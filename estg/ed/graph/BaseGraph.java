package estg.ed.graph;

import estg.ed.array.DynamicArrayCircular;
import estg.ed.interfaces.BaseGraphADT;
import estg.ed.interfaces.DynamicArrayContract;

/**
 * Implements a base for graphs and networks with an adjacency matrix.
 *
 * @param <T> generic
 */
public abstract class BaseGraph<T> implements BaseGraphADT<T> {

    /**
     * Dynamic array to store vertices values. Using already implemented
     * circular dynamic array.
     */
    protected DynamicArrayContract<T> vertices;

    /**
     * Instantiates an empty graph.
     */
    public BaseGraph() {
        this.vertices = new DynamicArrayCircular<>();
    }

    /**
     * Returns true if this graph is empty, false otherwise.
     *
     * @return true if this graph is empty
     */
    @Override
    public boolean isEmpty() {
        return this.vertices.size() == 0;
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the integer number of vertices in this graph
     */
    @Override
    public int size() {
        return this.vertices.size();
    }

    /**
     * Get index of a vertex.
     *
     * @param vertex1 vertex to find
     * @return index of vertex
     */
    protected int getIndex(T vertex1) {
        int size = this.vertices.size();
        for (int i = 0; i < size; i++) {
            T compared = this.vertices.get(i);

            if (compared.equals(vertex1)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Get indexes of a pair of vertex.
     *
     * @param vertex1 vertex to find
     * @param vertex2 second vertex to find
     * @return integer array with indexes of vertices
     */
    protected int[] getIndex(T vertex1, T vertex2) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;

        int size = this.vertices.size();
        for (int i = 0; i < size; i++) {
            T compared = this.vertices.get(i);

            if (compared.equals(vertex1)) {
                result[0] = i;
            }

            if (compared.equals(vertex2)) {
                result[1] = i;
            }

            if (result[0] != -1 && result[1] != -1) {
                break;
            }
        }

        return result;
    }
}
