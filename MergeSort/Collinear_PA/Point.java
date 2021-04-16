package MergeSort.Collinear_PA;

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

//they wrote the draw, to draw, constructor and toString methods
public class Point implements Comparable<Point> {

    private final int x; // x-coordinate of this point
    private final int y; // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public int compareTo(Point that) {
        if (this.y == that.y)
            return Double.compare(this.x, that.x);
        return Double.compare(this.y, that.y);
    }

    public double slopeTo(Point that) {
        if (compareTo(that) == 0)
            return Double.NEGATIVE_INFINITY;
        double yDist = this.y - that.y;
        if (yDist == 0)
            return (double) 0;
        double xDist = this.x - that.x;
        if (xDist == 0)
            return Double.POSITIVE_INFINITY;
        return yDist / xDist;
    }

    private class SlopeCmp implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double s1 = slopeTo(p1);
            double s2 = slopeTo(p2);
            if (s1 < s2)
                return -1;
            if (s1 > s2)
                return 1;
            return 0;
        }
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeCmp();
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
    }
}