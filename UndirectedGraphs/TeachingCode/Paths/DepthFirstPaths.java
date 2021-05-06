package UndirectedGraphs.TeachingCode.Paths;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

//DFS -> this implements the Paths API
public class DepthFirstPaths implements Paths {
    private boolean[] marked; // marked[v] = true if v connected to s
    private int[] edgeTo; // edgeTo[v] = previous vertex on path from s to v
    private int s;

    // find paths in G from source s
    public DepthFirstPaths(Graph G, int s) {
        // ... -> Initialize Data Structures
        dfs(G, s); // find vertices connected to s
    }

    private void dfs(Graph G, int v) {
        // Recursive DFS does the work
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
    }

    // is there a path from s to v?
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // path from s to v; null if no such path
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph G = new Graph(3);
        int s = 0;

        // Goal -> print all vertices connected to s
        DepthFirstPaths paths = new DepthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++)
            if (paths.hasPathTo(v))
                StdOut.println(v);
    }
}