package UnionFind.Diff_UFs;

//symple Union Find
public class QuickFind {
    //initially set every entry to its own group
    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    //connected if in same group
    public boolean connected(int p, int q) { return id[p] == id[q]; }
    //every entry in one group needs to be changed to the other
    public void union(int p, int q) {
        for (int i = 0; i < id.length; i++)
            if (id[i] == id[p])
                id[i] = id[q];
    }
    private int[] id;
}