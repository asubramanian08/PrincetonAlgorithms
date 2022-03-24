package ShortestPaths.Seam_PA;

import edu.princeton.cs.algs4.Picture;
import java.lang.Math;
import java.awt.Color;

public class SeamCarver {
    private Picture pic;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException("Can't initialized SeamCarver with not picture");
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
            throw new IllegalArgumentException("Pixel must be in range of the picture");
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
        double[][] grid = getEnergyGrid();
        for (int i = width() - 1; i > 0; i--)
            for (int j = 0; j < height(); j++) {
                double minEnergy = grid[i][j];
                if (j != 0)
                    minEnergy = Math.min(minEnergy, grid[i][j - 1]);
                if (j != height() - 1)
                    minEnergy = Math.min(minEnergy, grid[i][j + 1]);
                grid[i - 1][j] += minEnergy;
            }

        // find the path
        int[] path = new int[width()];
        int minEnergyIndex = 0;
        for (int i = 1; i < height(); i++)
            if (grid[0][minEnergyIndex] > grid[0][i])
                minEnergyIndex = i;
        for (int i = 0; i < width(); i++) {
            int nextMinEnergy = minEnergyIndex;
            if (minEnergyIndex != 0 && grid[i][minEnergyIndex - 1] < grid[i][nextMinEnergy])
                nextMinEnergy = minEnergyIndex - 1;
            if (minEnergyIndex != height() - 1 && grid[i][minEnergyIndex + 1] < grid[i][nextMinEnergy])
                nextMinEnergy = minEnergyIndex + 1;
            minEnergyIndex = nextMinEnergy;
            path[i] = minEnergyIndex;
        }
        return path;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        // form the energy grid
        double[][] grid = getEnergyGrid();
        for (int j = height() - 1; j > 0; j--)
            for (int i = 0; i < width(); i++) {
                double minEnergy = grid[i][j];
                if (i != 0)
                    minEnergy = Math.min(minEnergy, grid[i - 1][j]);
                if (i != width() - 1)
                    minEnergy = Math.min(minEnergy, grid[i + 1][j]);
                grid[i][j - 1] += minEnergy;
            }

        // find the path
        int[] path = new int[height()];
        int minEnergyIndex = 0;
        for (int i = 1; i < width(); i++)
            if (grid[minEnergyIndex][0] > grid[i][0])
                minEnergyIndex = i;
        for (int i = 0; i < height(); i++) {
            int nextMinEnergy = minEnergyIndex;
            if (minEnergyIndex != 0 && grid[minEnergyIndex - 1][i] < grid[nextMinEnergy][i])
                nextMinEnergy = minEnergyIndex - 1;
            if (minEnergyIndex != width() - 1 && grid[minEnergyIndex + 1][i] < grid[nextMinEnergy][i])
                nextMinEnergy = minEnergyIndex + 1;
            minEnergyIndex = nextMinEnergy;
            path[i] = minEnergyIndex;
        }
        return path;
    }

    private double[][] getEnergyGrid() {
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
        if (seam == null)
            throw new IllegalArgumentException("No seam was passed (null)");
        if (seam.length != (dimension == width() ? height() : width()))
            throw new IllegalArgumentException("Seam length doesn't match according dimension");
        if (dimension <= 1)
            throw new IllegalArgumentException("Can't remove any additional seams");
        if (!inRange(seam[0], dimension))
            throw new IllegalArgumentException(String.format("Pixel at seam position %d out of range: %d", 0, seam[0]));
        for (int i = 1; i < seam.length; i++)
            if (!inRange(seam[i], dimension))
                throw new IllegalArgumentException(
                        String.format("Pixel at seam position %d out of range: %d", i, seam[i]));
            else if (Math.abs(seam[i] - seam[i - 1]) > 1)
                throw new IllegalArgumentException(
                        String.format("Pixel at seam position %d differs by more than one: %d", i, seam[i]));

    }

    private boolean inRange(int x, int max) {
        return x >= 0 && x < max;
    }
}