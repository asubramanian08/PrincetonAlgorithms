package GeometricSearch.Implementations;

import BalancedSearchTrees.Implementations.RedBlackTree;

public class RangeSearch1D<Key extends Comparable<Key>, Value> extends RedBlackTree<Key, Value> {
    public int size(Key lo, Key hi) {
        if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else // number of keys < hi
            return rank(hi) - rank(lo);
    }
}