package Algorithm_Analysis.TeachingCode;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.*;

public class TimeMeasuring {
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println(ThreeSum.count(a));
        double time = stopwatch.elapsedTime();
        StdOut.println(time);
    }
}