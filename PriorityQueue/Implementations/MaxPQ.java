package PriorityQueue.Implementations;

import Utility.SortOper;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    // fixed capacity for simplicity
    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    // pq ops
    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[++N] = x;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        SortOper.exch(pq, 1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }

    // heap helper functions
    private void swim(int k) {
        while (k > 1 && SortOper.less(k / 2, k)) {
            SortOper.exch(pq, k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && SortOper.less(j, j + 1))
                j++;
            if (!SortOper.less(k, j))
                break;
            SortOper.exch(pq, k, j);
            k = j;
        }
    }
}