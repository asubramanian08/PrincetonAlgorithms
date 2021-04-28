package ElementarySymbolTables.Implementations;

// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;

//SHELL
public abstract class ST<Key, Value> {
    protected int N;

    // create a symbol table (constructor)

    // put key-value pair into the table(remove key from table if value is null)
    public abstract void put(Key key, Value val);

    // value paired with key(null if key is absent)
    public abstract Value get(Key key);

    // remove key (and its value) from table
    public void delete(Key key) {
        put(key, null);
    }

    // is there a value paired with key?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // is the table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of key-value pairs in the table
    public abstract int size();

    // smallest key
    public abstract Key min();

    // largest key
    public abstract Key max();

    // largest key less than or equal to key
    public abstract Key floor(Key key);

    // smallest key greater than or equal to key
    public abstract Key ceiling(Key key);

    // number of keys less than key
    public abstract int rank(Key key);

    // key of rank k
    public abstract Key select(int k);

    // delete smallest key
    public abstract void deleteMin();

    // delete largest key
    public abstract void deleteMax();

    // number of keys in [lo..hi]
    public abstract int size(Key lo, Key hi);

    // all the keys in the table, in sorted order
    public abstract Iterable<Key> keys();
}