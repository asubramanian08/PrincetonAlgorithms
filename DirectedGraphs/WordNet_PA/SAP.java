package DirectedGraphs.WordNet_PA;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private static final int NA = -1;
    private final Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException();
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return sap(makeQueue(v), makeQueue(w), false);
    }

    // a common ancestor of v and w that participates
    // in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return sap(makeQueue(v), makeQueue(w), true);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in
    // w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return sap(makeQueue(v), makeQueue(w), false);
    }

    // a common ancestor that participates in shortest
    // ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return sap(makeQueue(v), makeQueue(w), true);
    }

    // return value if false of length and true for ancestor
    private int sap(Queue<Integer> v, Queue<Integer> w, boolean retVal) {
        // init
        int[] depthV = new int[G.V()];
        int[] depthW = new int[G.V()];

        BFS(v, depthV);
        BFS(w, depthW);

        int minDepth = NA, min = NA, depth;
        for (int i = 0; i < G.V(); i++) {
            if (depthV[i] == NA || depthW[i] == NA)
                continue;
            depth = depthV[i] + depthW[i];
            if (minDepth == NA || minDepth > depth) {
                min = i;
                minDepth = depth;
            }
        }
        return retVal == false ? minDepth : min;
    }

    private void BFS(Queue<Integer> q, int[] depth) {
        for (int i = 0; i < depth.length; i++)
            depth[i] = NA;
        for (Integer i : q)
            depth[i] = 0;

        Integer src;
        while (!q.isEmpty()) {
            src = q.dequeue();
            for (Integer next : G.adj(src)) {
                if (depth[next] != NA)
                    continue;
                depth[next] = depth[src] + 1;
                q.enqueue(next);
            }
        }
    }

    // making the queue (with one value)
    private Queue<Integer> makeQueue(int val) {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(checkRange(val));
        return q;
    }

    // making the queue (with many values)
    private Queue<Integer> makeQueue(Iterable<Integer> vals) {
        if (vals == null)
            throw new IllegalArgumentException();
        Queue<Integer> q = new Queue<Integer>();
        for (Integer val : vals)
            q.enqueue(checkRange(val));
        return q;
    }

    private Integer checkRange(Integer val) {
        if (val == null || val >= G.V() || val < 0)
            throw new IllegalArgumentException();
        return val;
    }

    // do unit testing of this class -> given
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}