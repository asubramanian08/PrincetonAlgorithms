package Mergesort.TeachingCode;

import java.util.Comparator;

//continues froe what is written in ElementarySorts otherCode
public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static int ccw(Point a, Point b, Point c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 < 0)
            return -1; // clockwise
        else if (area2 > 0)
            return +1; // counter-clockwise
        else
            return 0; // collinear
    }

    private class PolarOrder implements Comparator<Point> {
        public int compare(Point q1, Point q2) {
            double dy1 = q1.y - y;
            double dy2 = q2.y - y;
            if (dy1 == 0 && dy2 == 0) {
            } else if (dy1 >= 0 && dy2 < 0)
                return -1;
            else if (dy2 >= 0 && dy1 < 0)
                return +1;
            return -ccw(Point.this, q1, q2);
        }
    }
}