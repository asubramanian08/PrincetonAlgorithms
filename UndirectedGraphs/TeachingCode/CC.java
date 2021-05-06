package UndirectedGraphs.TeachingCode;

import edu.princeton.cs.algs4.Graph;

//Connected components
public abstract class CC {
    private boolean[] marked;
    private int[] id; // id[v] = id of component containing v
    private int count; // number of components

    // find connected components in G
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v); // run DFS from one vertex in each component
                count++;
            }
        }
    }

    // are v and w connected?
    public boolean connected(int v, int w) {
        return id(v) == id(w); // I added
    }

    // number of connected components
    public int count() {
        return count;
    }

    // component identifier for v
    public int id(int v) {
        return id[v];
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count; // all vertices discovered in same call of dfs have same id
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }
}
