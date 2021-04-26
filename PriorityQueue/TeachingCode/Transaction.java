package PriorityQueue.TeachingCode;

import edu.princeton.cs.algs4.StdIn;

public class Transaction {
    static final int M = 5; // cut off

    Transaction(String str) {
    }

    public static void main(String[] args) {
        MinPQ<Transaction> pq = new MinPQ<Transaction>();
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction item = new Transaction(line);
            pq.insert(item);
            if (pq.size() > M)
                pq.delMin();
        }
    }
}
