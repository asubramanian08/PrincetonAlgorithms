package DirectedGraphs.Implementations;

import edu.princeton.cs.algs4.Bag;

// Shell with some implementations
public abstract class Digraph {
    private final int V;
    private final Bag<Integer>[] adj; // adjacency lists

    // create an empty digraph with V vertices
    public Digraph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    // public Digraph(In in); // create a digraph from input stream

    // add a directed edge v→w
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // vertices pointing from v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public abstract int V(); // number of vertices

    public abstract int E(); // number of edges

    public abstract Digraph reverse(); // reverse of this digraph

    public abstract String toString(); // string representation
}