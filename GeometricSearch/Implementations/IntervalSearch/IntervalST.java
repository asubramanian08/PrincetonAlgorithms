package GeometricSearch.Implementations.IntervalSearch;

//Interval search tree
//They gave the API and the search code (I did the rest)
public abstract class IntervalST<Key extends Comparable<Key>, Value extends Intersects<Key>> {
    protected class Node {
        Node left, right;
        Value interval;
        Key max;
    }

    protected Node root;

    // create interval search tree
    // public IntervalST() {;}

    // put interval-value pair into ST
    public abstract void put(Key lo, Key hi, Value val);

    // value paired with given interval
    public abstract Value get(Key lo, Key hi);

    // delete the given interval
    public abstract void delete(Key lo, Key hi);

    protected Value search(Key lo, Key hi) {
        Node x = root;
        while (x != null) {
            if (x.interval.intersects(lo, hi))
                return x.interval;
            else if (x.left == null)
                x = x.right;
            else if (x.left.max.compareTo(lo) < 0)
                x = x.right;
            else
                x = x.left;
        }
        return null;
    }

    // all intervals that intersect the given interval
    public abstract Iterable<Value> intersects(Key lo, Key hi);
}