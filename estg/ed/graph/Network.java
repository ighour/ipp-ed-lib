package estg.ed.graph;

import estg.ed.array.DynamicArrayCircular;
import estg.ed.exceptions.ElementNotFoundException;
import estg.ed.exceptions.EmptyCollectionException;
import estg.ed.exceptions.VertexIsNotAccessibleException;
import estg.ed.interfaces.DynamicArrayContract;
import estg.ed.interfaces.NetworkADT;
import estg.ed.interfaces.PriorityQueueADT;
import estg.ed.interfaces.QueueADT;
import estg.ed.interfaces.StackADT;
import estg.ed.interfaces.UnorderedListADT;
import estg.ed.list.UnorderedArrayList;
import estg.ed.queue.ArrayQueue;
import estg.ed.stack.ArrayStack;
import estg.ed.tree.binary.ArrayPriorityMinQueue;
import java.util.Iterator;

/**
 * Implements a directional network with an adjacency matrix.
 *
 * @param <T> generic
 */
public class Network<T> extends BaseGraph<T> implements NetworkADT<T> {

    /**
     * Dynamic array to store adjacent matrix. Uses a dynamic array of dynamic
     * array. Using already implemented circular dynamic array.
     */
    protected DynamicArrayContract<DynamicArrayContract<Double>> adjMatrix;

    /**
     * Default value to put when no edge exists between vertices.
     */
    protected double NO_EDGE_VALUE;

    /**
     * Instantiates an empty network.
     */
    public Network() {
        super();
        this.adjMatrix = new DynamicArrayCircular<>();
        this.NO_EDGE_VALUE = Double.POSITIVE_INFINITY;
    }

    /**
     * Adds a vertex to this network, associating object with vertex.
     *
     * @param vertex the vertex to be added to this network
     */
    @Override
    public void addVertex(T vertex) {
        //Insert vertex space into adjacency matrix
        this.adjMatrix.add(new DynamicArrayCircular<>(), this.adjMatrix.size());

        //Add vertice to vertices list at end
        this.vertices.add(vertex, this.vertices.size());

        //Set new vertice adjacencys as NO_EDGE_VALUE
        int size = this.adjMatrix.size();
        for (int i = 0; i < size; i++) {
            //Set all on new vertex column to NO_EDGE_VALUE (is the last index of each row)
            this.adjMatrix.get(i).add(this.NO_EDGE_VALUE, size - 1);

            //Set all on new vertex row to NO_EDGE_VALUE (all the last row)
            this.adjMatrix.get(size - 1).add(this.NO_EDGE_VALUE, i);
        }
    }

    /**
     * Removes a single vertex with the given value from this network.
     *
     * @param vertex the vertex to be removed from this network
     * @throws estg.ed.exceptions.ElementNotFoundException vertex was not found
     */
    @Override
    public void removeVertex(T vertex) throws ElementNotFoundException {
        //Get vertex index
        int index = this.getIndex(vertex);

        //Not found
        if (index < 0) {
            throw new ElementNotFoundException("Vertex was not found!");
        }

        //Remove vertex from vertices list
        this.vertices.remove(index);

        //Remove vertex row from adjacency matrix
        this.adjMatrix.remove(index);

        //Remove vertex column from adjacency matrix
        int size = this.adjMatrix.size();
        for (int i = 0; i < size; i++) {
            this.adjMatrix.get(i).remove(index);
        }
    }

    /**
     * Inserts an edge between two vertices of this network. Using weights at
     * edges. Edge is directional, so addEdge(A,B) is different from
     * addEdge(B,A). Uses changeEdge() method to handle edge update.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     * @throws estg.ed.exceptions.ElementNotFoundException one of vertices were
     * not found
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException {
        this.changeEdge(vertex1, vertex2, weight);
    }

    /**
     * Removes an edge between two vertices of this network. Edge is
     * directional, so removeEdge(A,B) is different from removeEdge(B,A). Uses
     * changeEdge() method to handle edge update.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws estg.ed.exceptions.ElementNotFoundException one of vertices were
     * not found
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) throws ElementNotFoundException {
        this.changeEdge(vertex1, vertex2, this.NO_EDGE_VALUE);
    }

    /**
     * Change edge value. Helper method to change an edge to desired double
     * value. Used by addEdge() and removeEdge() methods.
     *
     * @param vertex1 vertex at start of edge
     * @param vertex2 vertex at end of edge
     * @param newValue new weight to edge
     * @throws ElementNotFoundException vertex was not found
     */
    protected void changeEdge(T vertex1, T vertex2, double newValue) throws ElementNotFoundException {
        //Get vertices indexes
        int[] indexes = this.getIndex(vertex1, vertex2);

        //Check if is both valid
        if (indexes[0] < 0 || indexes[1] < 0) {
            throw new ElementNotFoundException("Vertex was not found.");
        }

        //Change value of vertices edges at adjacency matrix
        //Only at direction vertex1 -> vertex2
        this.adjMatrix.get(indexes[0]).change(newValue, indexes[1]);
    }

    /**
     * Returns a breadth first iterator starting with the given vertex. Uses
     * recursion.
     *
     * @param startVertex the starting vertex
     * @return a breadth first iterator beginning at the given vertex
     */
    @Override
    //Breadth First Search (X, Y, Xl, Xr, Yl, Yr, Xll, Xlr...)
    public Iterator iteratorBFS(T startVertex) {
        //Get vertex index
        int index = this.getIndex(startVertex);

        //Generate result list
        UnorderedListADT<T> resultList = new UnorderedArrayList<>();

        //If index is invalid, return result list iterator (empty)
        if (index < 0) {
            return resultList.iterator();
        }

        //Generate traversal queue
        QueueADT<Integer> traversalQueue = new ArrayQueue<>();

        //Generate visited boolean array
        boolean[] visited = new boolean[this.vertices.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        //Enqueue first item and set as visited
        traversalQueue.enqueue(index);
        visited[index] = true;

        //Start recursion
        this.iterateBFS(resultList, traversalQueue, visited);

        //Return iterator
        return resultList.iterator();
    }

    private void iterateBFS(UnorderedListADT<T> resultList, QueueADT<Integer> traversalQueue, boolean[] visited) {
        //Dequeue item
        int index;

        try {
            index = traversalQueue.dequeue();

        } catch (EmptyCollectionException ex) {
            //Stop if queue is empty
            return;
        }

        //Add index element to result
        resultList.addToRear(this.vertices.get(index));

        //Look for not visited neighbors to add to queue
        int size = this.vertices.size();
        for (int i = 0; i < size; i++) {
            //Get current to neightbor edge relation and check if was visited
            if (this.adjMatrix.get(index).get(i) != this.NO_EDGE_VALUE && !visited[i]) {
                //Add neighbor to queue and set as visited
                traversalQueue.enqueue(i);
                visited[i] = true;
            }
        }

        //Proceed in recursion if there is vertices in queue to visit
        if (!traversalQueue.isEmpty()) {
            this.iterateBFS(resultList, traversalQueue, visited);
        }
    }

    /**
     * Returns a depth first iterator starting with the given vertex. Uses
     * recursion.
     *
     * @param startVertex the starting vertex
     * @return a depth first iterator starting at the given vertex
     */
    @Override
    //Depth First Search (X, Xl, Xll, Xlr, Xr, Y, Yl, Yr...)
    public Iterator iteratorDFS(T startVertex) {
        //Get vertex index
        int index = this.getIndex(startVertex);

        //Generate result list
        UnorderedListADT<T> resultList = new UnorderedArrayList<>();

        //If index is invalid, return result list iterator (empty)
        if (index < 0) {
            return resultList.iterator();
        }

        //Generate traversal stack
        StackADT<Integer> traversalStack = new ArrayStack<>();

        //Generate visited boolean array
        boolean[] visited = new boolean[this.vertices.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        //Push first item and set as visited
        traversalStack.push(index);
        visited[index] = true;

        //Start recursion
        this.iterateDFS(resultList, traversalStack, visited);

        //Return iterator
        return resultList.iterator();
    }

    private void iterateDFS(UnorderedListADT<T> resultList, StackADT<Integer> traversalStack, boolean[] visited) {
        //Pop item
        int index;

        try {
            index = traversalStack.pop();

        } catch (EmptyCollectionException ex) {
            //Stop if stack is empty
            return;
        }

        //Add to result
        resultList.addToRear(this.vertices.get(index));

        //Look for not visited neighbors to add to stack
        int size = this.vertices.size();
        for (int i = 0; i < size; i++) {
            //Get current to neightbor edge relation and check if was visited
            if (this.adjMatrix.get(index).get(i) != this.NO_EDGE_VALUE && !visited[i]) {
                //Add neighbor to stack and set as visited
                traversalStack.push(i);
                visited[i] = true;
            }
        }

        //Proceed in recursion if there is vertices in stack to visit
        if (!traversalStack.isEmpty()) {
            this.iterateDFS(resultList, traversalStack, visited);
        }
    }

    /**
     * Returns an iterator that contains the shortest path between the two
     * vertices. Similar to iterator BFS, but using cumulative path length.
     * Preference is to less weighted edge.
     *
     * @param startVertex the starting vertex
     * @param targetVertex the ending vertex
     * @return an iterator that contains the shortest path between the two
     * vertices
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        //Get indexes
        int[] indexes = this.getIndex(startVertex, targetVertex);

        //Generate result list
        UnorderedListADT<T> resultList = new UnorderedArrayList<>();

        //If index is invalid, return result list iterator (empty)
        if (indexes[0] < 0 || indexes[1] < 0) {
            return resultList.iterator();
        }

        //Generate traversal priority min queue
        PriorityQueueADT<Integer> traversalQueue = new ArrayPriorityMinQueue<>();

        //Generate visited boolean array
        boolean[] visited = new boolean[this.vertices.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        //Generate path lengths array
        double[] pathLength = new double[this.vertices.size()];
        for (int i = 0; i < pathLength.length; i++) {
            pathLength[i] = -1;
        }

        //Generate antecessor array
        int[] antecessor = new int[this.vertices.size()];
        for (int i = 0; i < antecessor.length; i++) {
            antecessor[i] = -1;
        }

        //Enqueue first item and set as visited and set path length
        traversalQueue.enqueue(indexes[0], 0);
        pathLength[indexes[0]] = 0;

        //Start recursion
        this.iterateSP(traversalQueue, visited, pathLength, antecessor, indexes[1]);

        //Successfully achieved target
        if (antecessor[indexes[1]] != -1) {
            //Add path based on antecessors
            int currentIndex = indexes[1];
            while (currentIndex != -1) {
                resultList.addToFront(this.vertices.get(currentIndex));
                currentIndex = antecessor[currentIndex];
            }
        }

        //Return iterator
        return resultList.iterator();
    }

    private void iterateSP(PriorityQueueADT<Integer> traversalQueue, boolean[] visited, double[] pathLength, int[] antecessor, int target) {
        //Dequeue item
        int index;

        try {
            index = traversalQueue.dequeue();

        } catch (EmptyCollectionException ex) {
            //Stop if queue is empty
            return;
        }

        //Proceed in recursion if it was already visited
        if (visited[index] == true) {
            this.iterateSP(traversalQueue, visited, pathLength, antecessor, target);
            return;
        }

        //Visit if removed from queue
        visited[index] = true;

        //Look for not visited neighbors to add to queue
        int size = this.vertices.size();
        for (int i = 0; i < size; i++) {
            //Get current to neightbor edge relation and check if was visited
            if (this.adjMatrix.get(index).get(i) != this.NO_EDGE_VALUE && !visited[i]) {
                //Get cost
                double cost = this.adjMatrix.get(index).get(i);
                double fullCost = cost + pathLength[index];

                //Add neighbor to queue
                traversalQueue.enqueue(i, fullCost);

                //Compare costs
                //There is no old cost or new cost is smaller then old cost
                if (pathLength[i] == -1 || fullCost < pathLength[i]) {
                    //Set newCost as used cost
                    pathLength[i] = fullCost;

                    //Updates antecessor reference
                    antecessor[i] = index;
                }
            }
        }

        //Proceed in recursion if there is vertices in queue to visit
        if (!traversalQueue.isEmpty()) {
            this.iterateSP(traversalQueue, visited, pathLength, antecessor, target);
        }
    }

    /**
     * Returns true if this network is connected, false otherwise.
     *
     * @return true if this network is connected
     */
    @Override
    public boolean isConnected() {
        int size = this.vertices.size();

        for (int i = 0; i < size; i++) {
            //Check in matrix for a x-column without relations (except itself)
            boolean connectTo = false;

            //Check in matrix for a y-column without relations (except itself)
            boolean connectFrom = false;

            for (int j = 0; j < size; j++) {
                //Pass itself
                if (i == j) {
                    continue;
                }

                //It is pointing to another vertice
                if (this.adjMatrix.get(i).get(j) != this.NO_EDGE_VALUE) {
                    connectTo = true;
                }

                //It is being pointer by another vertice
                if (this.adjMatrix.get(j).get(i) != this.NO_EDGE_VALUE) {
                    connectFrom = true;
                }
            }

            //There is a vertice without pointing to any other (x-column)
            //Or without being pointed by any other (y-column)
            if (connectTo == false || connectFrom == false) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a string representation of the adjacency matrix.
     *
     * @return a string representation of the adjacency matrix
     */
    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();

        stb.append("\t");

        int size = this.vertices.size();
        for (int i = 0; i < size; i++) {
            stb.append(this.vertices.get(i)).append("\t");
        }

        stb.append("\n");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == 0) {
                    stb.append(this.vertices.get(i)).append("\t");
                }

                if (this.adjMatrix.get(i).get(j) != this.NO_EDGE_VALUE) {
                    stb.append(this.adjMatrix.get(i).get(j));
                } else {
                    stb.append("");
                }

                stb.append("\t");
            }

            stb.append("\n");
        }

        return stb.toString();
    }

    /**
     * Returns the weight of the shortest path in this network. Similar to
     * iteratorShortestPath(), but returns total path length instead. Preference
     * is to less weighted edge. Uses recursion.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     * @throws estg.ed.exceptions.ElementNotFoundException one of vertices were
     * not found
     * @throws estg.ed.exceptions.VertexIsNotAccessibleException second vertex
     * is not accessible from first vertex
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) throws ElementNotFoundException, VertexIsNotAccessibleException {
        //Get indexes
        int[] indexes = this.getIndex(vertex1, vertex2);

        //Index is invalid
        if (indexes[0] < 0 || indexes[1] < 0) {
            throw new ElementNotFoundException("Vertex was not found!");
        }

        //Generate traversal priority min queue to use weighted edges
        PriorityQueueADT<Integer> traversalQueue = new ArrayPriorityMinQueue<>();

        //Generate visited boolean array
        boolean[] visited = new boolean[this.vertices.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        //Generate path lengths array
        double[] pathLength = new double[this.vertices.size()];
        for (int i = 0; i < pathLength.length; i++) {
            pathLength[i] = -1;
        }

        //Generate antecessor array
        int[] antecessor = new int[this.vertices.size()];
        for (int i = 0; i < antecessor.length; i++) {
            antecessor[i] = -1;
        }

        //Enqueue first item and set as visited and set path length
        traversalQueue.enqueue(indexes[0], 0);
        pathLength[indexes[0]] = 0;

        //Start recursion
        this.iterateSP(traversalQueue, visited, pathLength, antecessor, indexes[1]);

        //Successfully achieved target
        if (antecessor[indexes[1]] != -1) //Return path full weight
        {
            return pathLength[indexes[1]];
        }

        //Could not achieve second vertex
        throw new VertexIsNotAccessibleException("Vertex " + vertex2.toString() + " is not accessible from " + vertex1.toString() + " on network.");
    }

    /**
     * Returns a minimum spanning tree of the network from desired element.
     * Preference is to less weighted edge. Uses recursion. Similar to iterator
     * BFS, but using greedy technic to select next vertex instead.
     *
     * @param vertex vertex to start the spawning tree
     * @return a Network containing the minimium spawning tree
     * @throws estg.ed.exceptions.ElementNotFoundException vertex was not found
     */
    @Override
    public NetworkADT<T> mstNetwork(T vertex) throws ElementNotFoundException {
        //Get index
        int index = this.getIndex(vertex);

        //Index is invalid
        if (index < 0) {
            throw new ElementNotFoundException("Vertex was not found!");
        }

        //Generate result graph
        NetworkADT<T> resultGraph = new Network<>();

        //Generate a priority min queue to store the weighted edges
        PriorityQueueADT<Integer> traverseQueue = new ArrayPriorityMinQueue<>();

        //Get size
        int size = this.vertices.size();

        //Generate visited boolean array
        boolean[] visited = new boolean[size];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        //Generate antecessor boolean array
        int[] antecessor = new int[size];
        for (int i = 0; i < antecessor.length; i++) {
            antecessor[i] = -1;
        }

        //Enqueue first item
        traverseQueue.enqueue(index, 0);

        //Iterate
        this.iterateMST(resultGraph, traverseQueue, visited, antecessor);

        //Return result
        return resultGraph;
    }

    protected void iterateMST(NetworkADT<T> resultGraph, PriorityQueueADT<Integer> traversalQueue, boolean[] visited, int[] antecessor) {
        //Dequeue item
        int index;

        try {
            index = traversalQueue.dequeue();

        } catch (EmptyCollectionException ex) {
            //Stop if queue is empty
            return;
        }

        //Proceed in recursion if it was already visited
        if (visited[index] == true) {
            this.iterateMST(resultGraph, traversalQueue, visited, antecessor);
            return;
        }

        //Set as visited when dequeued
        visited[index] = true;

        //Add index element to result
        resultGraph.addVertex(this.vertices.get(index));

        //If has antecessor, generate an edge
        int antIndex = antecessor[index];
        if (antIndex != -1) {
            try {
                //Add an edge with original weights
                resultGraph.addEdge(this.vertices.get(antIndex), this.vertices.get(index), this.adjMatrix.get(antIndex).get(index));
            } catch (ElementNotFoundException ex) {
            }
        }

        //Look for not visited neighbors to add to queue
        int size = this.vertices.size();
        for (int i = 0; i < size; i++) {
            //Get current to neightbor edge relation and check if was visited
            if (this.adjMatrix.get(index).get(i) != this.NO_EDGE_VALUE && !visited[i]) {
                //Add neighbor to queue
                traversalQueue.enqueue(i, this.adjMatrix.get(index).get(i));

                //Set antecessor
                antecessor[i] = index;
            }
        }

        //Proceed in recursion if there is vertices in queue to visit
        if (!traversalQueue.isEmpty()) {
            this.iterateMST(resultGraph, traversalQueue, visited, antecessor);
        }
    }
}
