package DirectedGraphs.TeachingCode;

import edu.princeton.cs.algs4.Digraph;

public class DirectedDFS {
    private boolean[] marked; // true if path from s

    // constructor marks vertices reachable from s
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // recursive DFS does the work
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    // client can ask whether any vertex is reachable from s
    public boolean visited(int v) {
        return marked[v];
    }
}
