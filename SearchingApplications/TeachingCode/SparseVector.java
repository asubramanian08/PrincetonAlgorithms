package SearchingApplications.TeachingCode;

import edu.princeton.cs.algs4.ST;

//replaced HashST with ST because there is not HashST
public class SparseVector {
    // HashST because order not important
    private ST<Integer, Double> v;

    // empty ST represents all 0s vector
    public SparseVector() {
        v = new ST<Integer, Double>();
    }

    // a[i] = value
    public void put(int i, double x) {
        v.put(i, x);
    }

    // return a[i]
    public double get(int i) {
        if (!v.contains(i))
            return 0.0;
        else
            return v.get(i);
    }

    public Iterable<Integer> indices() {
        return v.keys();
    }

    // dot product is constant time for sparse vectors
    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : indices())
            sum += that[i] * this.get(i);
        return sum;
    }
}