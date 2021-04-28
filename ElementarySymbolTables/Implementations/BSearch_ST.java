package ElementarySymbolTables.Implementations;

//use abstract so I don't have to make empty functions
public abstract class BSearch_ST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {
    private Key[] keys;
    private Value[] vals;

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else if (cmp == 0)
                return mid;
        }
        return lo;
    }

    public Value get(Key key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }
}