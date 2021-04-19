package ElementarySorts.Implementations;

import util.SortOper;

public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (SortOper.less(a[j], a[min]))
                    min = j;
            SortOper.exch(a, i, min);
        }
    }
}