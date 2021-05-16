package ShortestPaths.Seam_PA;

import edu.princeton.cs.algs4.Picture;
import java.lang.Math;
import java.awt.Color;

public class SeamCarver {
    private Picture pic;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException();
        pic = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return new Picture(pic);
    }

    // width of current picture
    public int width() {
        return pic.width();
    }

    // height of current picture
    public int height() {
        return pic.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (!inRange(x, width()) || !inRange(y, height()))
            throw new IllegalArgumentException();
        if ((x == 0 || x == width() - 1) || (y == 0 || y == height() - 1))
            return 1000;
        double xGradient = squareGradient(pic.get(x + 1, y), pic.get(x - 1, y));
        double yGradient = squareGradient(pic.get(x, y + 1), pic.get(x, y - 1));
        return Math.sqrt(xGradient + yGradient);
    }

    private double squareGradient(Color high, Color low) {
        double redSquare = Math.pow(high.getRed() - low.getRed(), 2);
        double greenSquare = Math.pow(high.getGreen() - low.getGreen(), 2);
        double blueSquare = Math.pow(high.getBlue() - low.getBlue(), 2);
        return redSquare + greenSquare + blueSquare;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        // form the energy grid
        double[][] grid = getGrid();
        double add;
        for (int i = width() - 1; i > 0; i--)
            for (int j = 0; j < height(); j++) {
                add = grid[i][j];
                if (j != 0)
                    add = Math.min(add, grid[i][j - 1]);
                if (j != height() - 1)
                    add = Math.min(add, grid[i][j + 1]);
                grid[i - 1][j] += add;
            }

        // find the path
        int[] path = new int[width()];
        int pos = 0, newPos;
        for (int i = 1; i < height(); i++)
            if (grid[0][pos] > grid[0][i])
                pos = i;
        for (int i = 0; i < width(); i++) {
            newPos = pos;
            if (pos != 0 && grid[i][pos - 1] < grid[i][newPos])
                newPos = pos - 1;
            if (pos != height() - 1 && grid[i][pos + 1] < grid[i][newPos])
                newPos = pos + 1;
            pos = newPos;
            path[i] = pos;
        }
        return path;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        // form the energy grid
        double[][] grid = getGrid();
        double add;
        for (int j = height() - 1; j > 0; j--)
            for (int i = 0; i < width(); i++) {
                add = grid[i][j];
                if (i != 0)
                    add = Math.min(add, grid[i - 1][j]);
                if (i != width() - 1)
                    add = Math.min(add, grid[i + 1][j]);
                grid[i][j - 1] += add;
            }

        // find the path
        int[] path = new int[height()];
        int pos = 0, newPos;
        for (int i = 1; i < width(); i++)
            if (grid[pos][0] > grid[i][0])
                pos = i;
        for (int i = 0; i < height(); i++) {
            newPos = pos;
            if (pos != 0 && grid[pos - 1][i] < grid[newPos][i])
                newPos = pos - 1;
            if (pos != width() - 1 && grid[pos + 1][i] < grid[newPos][i])
                newPos = pos + 1;
            pos = newPos;
            path[i] = pos;
        }
        return path;
    }

    private double[][] getGrid() {
        double[][] grid = new double[width()][height()];
        for (int i = 0; i < width(); i++)
            for (int j = 0; j < height(); j++)
                grid[i][j] = energy(i, j);
        return grid;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        checkSeam(seam, height());
        Picture newPic = new Picture(width(), height() - 1);
        for (int i = 0; i < seam.length; i++)
            for (int j = 0; j < height(); j++)
                if (j != seam[i])
                    newPic.set(i, j > seam[i] ? j - 1 : j, pic.get(i, j));
        pic = newPic;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        checkSeam(seam, width());
        Picture newPic = new Picture(width() - 1, height());
        for (int i = 0; i < width(); i++)
            for (int j = 0; j < seam.length; j++)
                if (i != seam[j])
                    newPic.set(i > seam[j] ? i - 1 : i, j, pic.get(i, j));
        pic = newPic;
    }

    private void checkSeam(int[] seam, int dimension) {
        if (seam == null || seam.length != (dimension == width() ? height() : width()) || dimension <= 1
                || !inRange(seam[0], dimension))
            throw new IllegalArgumentException();
        for (int i = 1; i < seam.length; i++)
            if (!inRange(seam[i], dimension) || Math.abs(seam[i] - seam[i - 1]) > 1)
                throw new IllegalArgumentException();
    }

    private boolean inRange(int x, int max) {
        return x >= 0 && x < max;
    }
}