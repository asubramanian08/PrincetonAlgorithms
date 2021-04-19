package Mergesort.Collinear_PA;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] stdLines;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        // check bad arguments + init
        if (points == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new IllegalArgumentException();
        Point[] pts = points.clone();
        Arrays.sort(pts);
        for (int i = 1; i < pts.length; i++)
            if (pts[i].compareTo(pts[i - 1]) == 0)
                throw new IllegalArgumentException();
        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();

        // find the line segments
        double slope1, slope2, slope3;
        for (int p1 = 0; p1 < pts.length; p1++)
            for (int p2 = p1 + 1; p2 < pts.length; p2++)
                for (int p3 = p2 + 1; p3 < pts.length; p3++)
                    for (int p4 = p3 + 1; p4 < pts.length; p4++) {
                        slope1 = pts[p1].slopeTo(pts[p2]);
                        slope2 = pts[p2].slopeTo(pts[p3]);
                        slope3 = pts[p3].slopeTo(pts[p4]);
                        if (slope1 == slope2 && slope1 == slope3)
                            lines.add(new LineSegment(pts[p1], pts[p4]));
                    }

        // form the std lines
        stdLines = new LineSegment[lines.size()];
        stdLines = lines.toArray(stdLines);
    }

    public int numberOfSegments() { // the number of line segments
        return stdLines.length;
    }

    public LineSegment[] segments() { // the line segments
        return stdLines.clone();
    }
}