package ElementarySorts.Implementations;

public class SortOper {
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Object[] a, int pos1, int pos2) {
        var temp = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = temp;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    public static boolean isSorted(Comparable[] a, int start, int end) {
        for (int i = start + 1; i <= end; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }
}