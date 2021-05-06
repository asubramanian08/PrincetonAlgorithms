package UndirectedGraphs.TeachingCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;

public class Client {
    public static void main(String[] args) {
        // read graph from input steam
        In in = new In(args[0]);
        Graph G = new Graph(in);

        // print out each graph (twice)
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                StdOut.println(v + "-" + w);
    }
}