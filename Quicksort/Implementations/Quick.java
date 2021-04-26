package Quicksort.Implementations;

import Utility.SortOper;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (SortOper.less(a[++i], a[lo]))
                if (i == hi)
                    break;
            while (SortOper.less(a[lo], a[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            SortOper.exch(a, i, j);
        }
        SortOper.exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        // if (hi <= lo + CUTOFF - 1) { Insertion.sort(a, lo, hi); return; }
        // int m = medianOf3(a, lo, lo + (hi - lo)/2, hi); swap(a, lo, m); //for smaller
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // the better algorithm to properly handel duplicates
    private static void sortDuplicates(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                SortOper.exch(a, lt++, i++);
            else if (cmp > 0)
                SortOper.exch(a, i, gt--);
            else
                i++;
        }
        sortDuplicates(a, lo, lt - 1);
        sortDuplicates(a, gt + 1, hi);
    }

    // not part of the quick sort (selection)
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k)
                lo = j + 1;
            else if (j > k)
                hi = j - 1;
            else
                return a[k];
        }
        return a[k];
    }
}