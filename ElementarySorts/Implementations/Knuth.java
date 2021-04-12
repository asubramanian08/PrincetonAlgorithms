package ElementarySorts.Implementations;

import edu.princeton.cs.algs4.StdRandom;

public class Knuth {
    public static void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(i + 1);
            SortOper.exch(a, i, r);
        }
    }
}
