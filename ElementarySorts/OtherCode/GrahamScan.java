package ElementarySorts.OtherCode;

import edu.princeton.cs.algs4.Point2D;
import java.util.Stack;
import java.util.Arrays;

public class GrahamScan {
    Stack<Point2D> findHull(Point2D[] p, int N) {
        Stack<Point2D> hull = new Stack<Point2D>();

        Arrays.sort(p, Point2D.Y_ORDER);
        Arrays.sort(p, p[0].polarOrder());

        // definitely on hull
        hull.push(p[0]);
        hull.push(p[1]);

        for (int i = 2; i < N; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, p[i]) <= 0)
                top = hull.pop();
            hull.push(top);
            hull.push(p[i]);
        }

        return hull;
    }
}