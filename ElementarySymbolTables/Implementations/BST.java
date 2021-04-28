package ElementarySymbolTables.Implementations;

import edu.princeton.cs.algs4.Queue;

// Many things recursively implemented.
// I wrote: constructor, min, max, ceiling, select, and deleteMax ~size(hi, low)
// look at their BST implementation on algs4
public class BST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public BST() {
        root = null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    // concise, but tricky, recursive code; read carefully!
    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else // if (cmp == 0)
            x.val = val;
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else // if (cmp == 0)
                return x.val;
        }
        return null;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        // search for key
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            // no right child
            if (x.right == null)
                return x.left;
            // no left child
            if (x.left == null)
                return x.right;

            // replace with successor
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        // update subtree counts
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.count;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x == null || x.left == null)
            return x;
        else
            return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x == null || x.right == null)
            return x;
        else
            return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null)
            return t;
        else
            return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else // if (cmp == 0)
            return size(x.left);
    }

    public Key select(int k) {
        return select(k, root);
    }

    private Key select(int k, Node x) {
        if (x == null)
            return null;
        int cmp = k - size(x.left);
        if (cmp < 0)
            return select(k, x.left);
        else if (cmp > 0)
            return select(cmp - 1, x.right);
        else // if (cmp == 0)
            return x.key;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public int size(Key lo, Key hi) {
        // copied from algs4
        if (lo.compareTo(hi) > 0)
            return 0;
        if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        if (x == null)
            return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }
}