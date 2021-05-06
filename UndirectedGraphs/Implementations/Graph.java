package UndirectedGraphs.Implementations;

import edu.princeton.cs.algs4.Bag;

// Shell with some implementations
public abstract class Graph {
    // adjacency lists ( using Bag data type )
    private final int V;
    private Bag<Integer>[] adj;

    // create an empty graph with V vertices
    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    // public Graph(In in); // create a graph from input stream

    // add an edge v-w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // vertices adjacent to v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public abstract int V(); // number of vertices

    public abstract int E(); // number of edges

    public abstract String toString(); // string representation
}