package BalancedSearchTrees.Implementations;

//my BST (not self balancing)
import ElementarySymbolTables.Implementations.BST;

// I added to node constructor, put public method, and constructor
// Didn't implement the rest of the BST methods needed -> look at BST.java
//Didn't implement delete -> a little more complicated by still lg(n)
public class RedBlackTree<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
    private Node root = null;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color; // color of parent link

        Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null)
            return false; // NULL links are black
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        // assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        // assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // use to change temporary 4 node
    private void flipColors(Node h) {
        // assert !isRed(h);
        // assert isRed(h.left);
        // assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
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

    private Node put(Node h, Key key, Value val) {
        if (h == null) // insert at bottom (and color it red)
            return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, val);
        else if (cmp > 0)
            h.right = put(h.right, key, val);
        else // if (cmp == 0)
            h.val = val;

        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h); // lean left
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h); // balance 4 node
        if (isRed(h.left) && isRed(h.right))
            flipColors(h); // split 4 node
        return h;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }
}