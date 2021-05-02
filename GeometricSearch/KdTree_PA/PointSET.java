package GeometricSearch.KdTree_PA;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;
import java.util.Comparator;

public class PointSET {
    private SET<Point2D> set;

    // construct an empty set of points
    public PointSET() {
        set = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D pt : set)
            pt.draw();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("The rectangle in range was null");
        ArrayList<Point2D> inRect = new ArrayList<Point2D>();
        for (Point2D pt : set)
            if (rect.contains(pt))
                inRect.add(pt);
        return inRect;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("The point passed to nearest was null");
        Point2D closest = null;
        Comparator<Point2D> cmp = p.distanceToOrder();
        for (Point2D pt : set)
            if (closest == null || cmp.compare(pt, closest) < 0)
                closest = pt;
        return closest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET test = new PointSET();
        System.out.println("Is Empty is: " + test.isEmpty());
        System.out.println("Inserting points 1,1 ... 4,4");
        for (int i = 0; i < 4; i++)
            test.insert(new Point2D(i + 1, i + 1));
        System.out.println("Size is: " + test.size());
        System.out.println("The nearest to -2,4.5 is: " + test.nearest(new Point2D(-2, 4.5)).toString());
        System.out.println("Contains -2,4.5 is: " + test.contains(new Point2D(-2, 4.5)));
        System.out.println("Contains 3,3 is: " + test.contains(new Point2D(3, 3)));
        System.out.println("Getting things in rectangle (-2, -2.5), (1, 4)");
        Iterable<Point2D> inRange = test.range(new RectHV(-2, -2.5, 1, 4));
        System.out.print("The points in the range: ");
        for (Point2D pt : inRange)
            System.out.println(pt.toString() + ' ');
        System.out.println();
        System.out.println("Drawing the points");
        test.draw();
        System.out.println("Test Finished, bye!");
    }
}