package UndirectedGraphs.TeachingCode.Paths;

public interface Paths {
    // Paths(Graph G, int s); //find paths in G from source s

    boolean hasPathTo(int v); // is there a path from s to v?

    Iterable<Integer> pathTo(int v); // path from s to v; null if no such path
}
