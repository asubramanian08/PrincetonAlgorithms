package ElementarySorts.Implementations;

import util.SortOper;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...
        while (h >= 1) { // h-sort the array.
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && SortOper.less(a[j], a[j - h]); j -= h)
                    SortOper.exch(a, j, j - h);
            }
            h = h / 3;
        }
    }
}