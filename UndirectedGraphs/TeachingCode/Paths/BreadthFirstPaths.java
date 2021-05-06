package UndirectedGraphs.TeachingCode.Paths;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

public abstract class BreadthFirstPaths implements Paths {

    private boolean[] marked;
    private int[] edgeTo;

    BreadthFirstPaths(Graph G, int s) {
        // ... -> Initialize Data Structures
        bfs(G, s);
    }

    // ... -> other methods (look at DepthFirstPaths)

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }
    }
}
