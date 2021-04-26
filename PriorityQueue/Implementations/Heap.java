package PriorityQueue.Implementations;

import Utility.SortOper;

public class Heap {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--)
            sink(a, k, N);
        while (N > 1) {
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }

    // copied from maxPQ
    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1))
                j++;
            if (!less(a, k, j))
                break;
            exch(a, k, j);
            k = j;
        }
    }

    // the less and exch should be converted to 0 based indexing
    private static boolean less(Comparable[] a, int i, int j) {
        return SortOper.less(a[i - 1], a[j - 1]); // the real less
    }

    private static void exch(Comparable[] a, int i, int j) {
        SortOper.exch(a, i - 1, j - 1); // the real exch
    }
}