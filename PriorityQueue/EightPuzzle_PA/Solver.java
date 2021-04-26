package PriorityQueue.EightPuzzle_PA;

import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private ArrayList<Board> sol;

    // for the optimizations
    private class Node implements Comparable<Node> {
        private int dist, moves; // manhattan
        public Node parent;
        public Board val;

        public Node(Node parent, Board val) {
            this.parent = parent;
            this.val = val;
            dist = this.val.manhattan();
            if (parent == null)
                moves = 0;
            else
                moves = parent.moves + 1;
        }

        public int compareTo(Node that) {
            return (this.dist + this.moves) - (that.dist + that.moves);
        }

        public boolean isFinal() {
            return dist == 0;
        }

        public boolean is_unique(Board potVal) {
            if (parent == null)
                return true;
            return !potVal.equals(parent.val);
        }
    }

    private Node doSearch(Node currNode, MinPQ<Node> aSearch) {
        Iterable<Board> neighbors = currNode.val.neighbors();
        for (Board newPos : neighbors)
            if (currNode.is_unique(newPos))
                aSearch.insert(new Node(currNode, newPos));
        return aSearch.delMin();
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        // initialize
        if (initial == null)
            throw new IllegalArgumentException();
        Node currNode1 = new Node(null, initial), currNode2 = new Node(null, initial.twin());
        MinPQ<Node> aSearch1 = new MinPQ<Node>(), aSearch2 = new MinPQ<Node>();

        // get pq done
        while ((!currNode1.isFinal()) && (!currNode2.isFinal())) {
            currNode1 = doSearch(currNode1, aSearch1);
            currNode2 = doSearch(currNode2, aSearch2);
        }

        // find the order
        sol = new ArrayList<Board>();
        if (!currNode1.isFinal())
            return;
        while (currNode1 != null) {
            sol.add(currNode1.val);
            currNode1 = currNode1.parent;
        }
        Collections.reverse(sol);
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return sol.size() != 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return sol.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;
        return sol;
    }

    // test client (see below) -> their code
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}