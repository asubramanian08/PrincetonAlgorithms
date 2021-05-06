package UndirectedGraphs.TeachingCode;

import edu.princeton.cs.algs4.Graph;

public final class GraphProcessing {
    // compute the degree of v
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v))
            degree++;
        return degree;
    }

    // compute maximum degree
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++)
            if (degree(G, v) > max)
                max = degree(G, v);
        return max;
    }

    // compute average degree
    public static double averageDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    // count self-loops
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v == w)
                    count++;
        return count / 2; // each edge counted twice}
    }
}