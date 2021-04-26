package PriorityQueue.Implementations;

import Utility.SortOper;

public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // pq[i] = ith element on pq
    private int N; // number of elements on pq

    // pre-required capacity
    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[N++] = x;
    }

    // order n delete
    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++)
            if (SortOper.less(max, i))
                max = i;
        SortOper.exch(pq, max, N - 1);
        return pq[--N];
    }
}