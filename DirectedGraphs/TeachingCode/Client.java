package DirectedGraphs.TeachingCode;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Client {
    public static void main(String[] args) {
        // read digraph from input stream
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v)) // print out each edge (once)
                StdOut.println(v + "->" + w);
    }
}