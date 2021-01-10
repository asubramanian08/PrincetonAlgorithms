package UnionFind; //not supposed to be here in submittion

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

//mine work
public class PercolationStats {

    private static final double STD_MULT = 1.96;
    private final double avr, sDev, trialsDone;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        double[] ratios = new double[trials];
        int squares = n * n;
        trialsDone = trials;
        for (int i = 0; i < trials; i++) {
            Percolation per = new Percolation(n);
            while (!per.percolates())
                per.open(StdRandom.uniform(0, n) + 1, StdRandom.uniform(0, n) + 1);
            ratios[i] = (double) per.numberOfOpenSites() / (double) squares;
        }
        avr = StdStats.mean(ratios);
        sDev = StdStats.stddev(ratios);
    }

    // sample mean of percolation threshold
    public double mean() {
        return avr;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return sDev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return avr - ((STD_MULT * sDev) / Math.sqrt(trialsDone));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return avr + ((STD_MULT * sDev) / Math.sqrt(trialsDone));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
