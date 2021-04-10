package UnionFind.Implementations;

//change the roots to connect each other 
//instead of going through the array
public class QuickUnion {
    // initially set every entry to its own group
    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // if their roots are the same
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // make one root connect to the other
    public void union(int p, int q) {
        id[root(p)] = root(q);
    }

    // find its first parent
    private int root(int i) {
        while (i != id[i])
            i = id[i];
        return i;
    }

    private int[] id;
}