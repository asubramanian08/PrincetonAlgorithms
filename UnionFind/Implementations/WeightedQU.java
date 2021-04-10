package UnionFind.Implementations;

//  similar to quick union, but puts the smaller
//  tree under larger tree in union function 
public class WeightedQU {
    // initially set every entry to its own group
    public WeightedQU(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // if their roots are the same
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // make one root connect to the other based on the sizes
    public void union(int p, int q) {
        int i = root(p), j = root(q);
        if (i == j)
            return;

        // makes the size always less then lg N -> proof on pg 36
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    // find its first parent
    private int root(int i) {
        while (i != id[i])
            i = id[i];
        return i;
    }

    private int id[], sz[];
}