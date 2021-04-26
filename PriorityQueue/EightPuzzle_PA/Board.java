package PriorityQueue.EightPuzzle_PA;

import java.util.ArrayList;
import java.util.Iterator;

public class Board {
    private int sideLen;
    private int[][] spots;
    private static final int space = 0;
    // the dist vars are the useable ones
    private int distSpace;

    private int[][] copy(int[][] orgSpots) {
        int[][] newSpots = new int[orgSpots.length][orgSpots.length];
        for (int i = 0; i < orgSpots.length; i++)
            for (int j = 0; j < orgSpots.length; j++)
                newSpots[i][j] = orgSpots[i][j];
        return newSpots;
    }

    private int[][] swap(int p1x, int p1y, int p2x, int p2y) {
        int[][] newSpots = copy(spots);
        int temp = newSpots[p1x][p1y];
        newSpots[p1x][p1y] = newSpots[p2x][p2y];
        newSpots[p2x][p2y] = temp;
        return newSpots;
    }

    private int[][] useableSpots() {
        int[][] newSpots = new int[sideLen][sideLen];
        for (int i = 0; i < sideLen; i++)
            for (int j = 0; j < sideLen; j++)
                if (spots[i][j] == 0)
                    newSpots[i][j] = distSpace;
                else
                    newSpots[i][j] = spots[i][j] - 1;
        return newSpots;
    }

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null)
            throw new IllegalArgumentException();
        sideLen = tiles.length;
        spots = copy(tiles);
        distSpace = sideLen * sideLen - 1;
    }

    // string representation of this board
    public String toString() {
        String strRep = new String();
        strRep += String.valueOf(sideLen);
        for (int i = 0; i < sideLen; i++) {
            strRep += '\n';
            for (int j = 0; j < sideLen; j++)
                strRep += ' ' + String.valueOf(spots[i][j]);
        }
        strRep += '\n';
        return strRep;
    }

    // board dimension n
    public int dimension() {
        return sideLen;
    }

    // number of tiles out of place
    public int hamming() {
        int[][] distSpots = useableSpots();
        int outPos = 0;
        for (int i = 0; i < sideLen; i++)
            for (int j = 0; j < sideLen; j++)
                if (distSpots[i][j] != distSpace && distSpots[i][j] != i * sideLen + j)
                    outPos++;
        return outPos;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int[][] distSpots = useableSpots();
        int moveDist = 0;
        for (int i = 0; i < sideLen; i++)
            for (int j = 0; j < sideLen; j++)
                if (distSpots[i][j] != distSpace)
                    moveDist += Math.abs(i - distSpots[i][j] / sideLen) + Math.abs(j - distSpots[i][j] % sideLen);
        return moveDist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y
    public boolean equals(Object y) {
        if (y == null || !y.getClass().equals(getClass()))
            return false;
        Board that = (Board) y;
        if (this.sideLen != that.sideLen)
            return false;
        for (int i = 0; i < sideLen; i++)
            for (int j = 0; j < sideLen; j++)
                if (this.spots[i][j] != that.spots[i][j])
                    return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> next = new ArrayList<Board>();

        // findSpace
        int spaceX = -1, spaceY = -1;
        for (int i = 0; spaceX == -1; i++)
            for (int j = 0; j < sideLen; j++)
                if (spots[i][j] == space) {
                    spaceX = i;
                    spaceY = j;
                }

        // make all the neighbors
        if (spaceX > 0)
            next.add(new Board(swap(spaceX - 1, spaceY, spaceX, spaceY)));
        if (spaceY > 0)
            next.add(new Board(swap(spaceX, spaceY - 1, spaceX, spaceY)));
        if (spaceX < sideLen - 1)
            next.add(new Board(swap(spaceX + 1, spaceY, spaceX, spaceY)));
        if (spaceY < sideLen - 1)
            next.add(new Board(swap(spaceX, spaceY + 1, spaceX, spaceY)));

        return next;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        if ((spots[0][0] != space) && (spots[0][1] != space))
            return new Board(swap(0, 0, 0, 1));
        if (spots[0][0] == space)
            return new Board(swap(0, 1, 1, 0));
        return new Board(swap(0, 0, 1, 0));
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        // init -> hard coded sample array
        int[][] sampArr = { { 1, 0, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
        Board sampBoard = new Board(sampArr);
        // call functions
        System.out.println("Sample board:");
        System.out.print(sampBoard.toString());
        System.out.println("Dimension from array: " + sampArr.length);
        System.out.println("Dimension from board: " + sampBoard.dimension());
        System.out.println("Hamming distance: " + sampBoard.hamming());
        System.out.println("Manhattan distance: " + sampBoard.manhattan());
        System.out.println("Is goal: " + sampBoard.isGoal());
        Board twinBoard = sampBoard.twin();
        System.out.println("Twin board: ");
        System.out.print(twinBoard.toString());
        System.out.println("Equals twin: " + sampBoard.equals(twinBoard));
        System.out.println("Equals itself: " + sampBoard.equals(sampBoard));
        // System.out.println("Equals an Integer 5: " +
        // sampBoard.equals(Integer.valueOf(5)));
        System.out.println("Neighbors: ");
        Iterator<Board> iter = sampBoard.neighbors().iterator();
        while (iter.hasNext())
            System.out.println(iter.next());
        System.out.println("Done testing!");
    }
}