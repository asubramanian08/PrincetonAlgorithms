package MergeSort.Implementations;

public class MergeBU {
    // copied from merge.java
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

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
    }
}
