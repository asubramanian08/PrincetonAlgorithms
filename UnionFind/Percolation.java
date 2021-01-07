package UnionFind; //not supposed to be here i submition

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] openGrid;
    private final int sideLen;
    private final WeightedQuickUnionUF uf;
    private int numOpen, numBottemOpen;
    private boolean isPerc;
    private int bottemOpen[];

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        openGrid = new boolean[n][n];
        bottemOpen = new int[n];
        sideLen = n;
        numBottemOpen = numOpen = 0;
        // int squares = n * n; // name?
        uf = new WeightedQuickUnionUF(n * n + 2); // +2 for top and bottom
        /*
         * for (int i = 1; i < n; i++) { uf.union(i - 1, i); // union top
         * uf.union(squares - i, squares - (i + 1)); // union bottom }
         */
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row--;
        col--;
        if (!inRange(row) || !inRange(col))
            throw new IllegalArgumentException();
        if (openGrid[row][col])
            return;
        openGrid[row][col] = true;
        numOpen++;
        if (inRange(row - 1)) {
            if (openGrid[row - 1][col]) {
                uf.union(row * sideLen + col, (row - 1) * sideLen + col);
            }
        } else // connect to top
            uf.union(row * sideLen + col, sideLen * sideLen);
        if (inRange(row + 1)) {
            if (openGrid[row + 1][col]) {
                uf.union(row * sideLen + col, (row + 1) * sideLen + col);
            }
        } else { // connect to bottem //uf.union(row * sideLen + col, sideLen * sideLen + 1);
            bottemOpen[numBottemOpen] = col;
            numBottemOpen++;
        }
        if (inRange(col - 1) && openGrid[row][col - 1])
            uf.union(row * sideLen + col, row * sideLen + (col - 1));
        if (inRange(col + 1) && openGrid[row][col + 1])
            uf.union(row * sideLen + col, row * sideLen + (col + 1));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (!inRange(row) || !inRange(col))
            throw new IllegalArgumentException();
        return openGrid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row--;
        col--;
        if (!inRange(row) || !inRange(col))
            throw new IllegalArgumentException();
        return uf.find(row * sideLen + col) == uf.find(sideLen * sideLen);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (isPerc)
            return true;
        else
            for (int i = 0; i < numBottemOpen; i++)
                if (uf.find((sideLen - 1) * sideLen + bottemOpen[i]) == uf.find(sideLen * sideLen)) {
                    isPerc = true;
                    return true;
                }
        return false;
        // return uf.find(sideLen * sideLen) == uf.find(sideLen * sideLen + 1);
    }

    private boolean inRange(int p) {
        return (p >= 0) && (p < sideLen);
    }

}