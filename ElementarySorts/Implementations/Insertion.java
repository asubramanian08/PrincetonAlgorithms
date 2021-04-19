package ElementarySorts.Implementations;

import java.lang.Comparable;
import util.SortOper;

public class Insertion {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0; j--)
                if (SortOper.less(a[j], a[j - 1]))
                    SortOper.exch(a, j, j - 1);
                else
                    break;
    }
}