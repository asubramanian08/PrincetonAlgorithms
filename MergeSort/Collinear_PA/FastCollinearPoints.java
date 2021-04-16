package MergeSort.Collinear_PA;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] stdLines;

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points

        // init vars + check problems
        if (points == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new IllegalArgumentException();
        Point[] newPts = points.clone();
        Arrays.sort(newPts);
        for (int i = 1; i < newPts.length; i++)
            if (newPts[i].compareTo(newPts[i - 1]) == 0)
                throw new IllegalArgumentException();

        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
        double slope;
        int covered;

        // find the line segment
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(newPts); // need range of points
            Arrays.sort(newPts, points[i].slopeOrder());
            for (int j = 1; j < newPts.length; j += covered) { // the first one is the point
                // find the point in the line
                slope = points[i].slopeTo(newPts[j]);
                for (covered = 1; (j + covered < newPts.length)
                        && (slope == points[i].slopeTo(newPts[j + covered])); covered++)
                    ;

                // check if line segment is valid and add it
                if (covered < 3)
                    continue;
                if (points[i].compareTo(newPts[j]) < 0)
                    lines.add(new LineSegment(points[i], newPts[j + covered - 1]));
            }
        }

        // set std lines
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