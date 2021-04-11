package BagsStacksQueues.Queues_PA;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int numPrint = Integer.parseInt(args[0]);
        RandomizedQueue<String> randStr = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            randStr.enqueue(StdIn.readString());
        var iter = randStr.iterator();
        for (int i = 0; i < numPrint; i++)
            StdOut.println(iter.next());
    }
}