package Mergesort.Implementations;

import util.SortOper;

public class Merge {
    // could flip a and aux for more speed efficiency
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert SortOper.isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
        assert SortOper.isSorted(a, mid + 1, hi); // precondition: a[mid+1..hi] sorted
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (SortOper.less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
        assert SortOper.isSorted(a, lo, hi); // post-condition: a[lo..hi] sorted
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        // if (hi <= lo + CUTOFF - 1) { //20% faster Insertion.sort(a, lo, hi); return;}
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        // if (!SortOper.less(a[mid + 1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
}