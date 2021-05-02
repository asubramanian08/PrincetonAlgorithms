package GeometricSearch.KdTree_PA;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

//Really a 2D tree - not k | Actually Implement a ~BST
public class KdTree {
    private static final int XMIN = 0;
    private static final int YMIN = 0;
    private static final int XMAX = 1;
    private static final int YMAX = 1;

    private Node root;
    private int size;

    private class Node {
        final Point2D pt;
        final boolean is_vert;
        final RectHV rect;
        Node left, right;

        // calculates and sets everything for user
        Node(Point2D pt, Node parent) {
            this.pt = pt;
            this.left = null;
            this.right = null;
            size++;

            // parent stuff
            if (parent != null) {
                this.is_vert = !parent.is_vert;
                if (parent.is_right(pt)) {
                    parent.right = this;
                    if (parent.is_vert)
                        this.rect = new RectHV(parent.pt.x(), parent.rect.ymin(), parent.rect.xmax(),
                                parent.rect.ymax());
                    else
                        this.rect = new RectHV(parent.rect.xmin(), parent.pt.y(), parent.rect.xmax(),
                                parent.rect.ymax());
                } else {
                    parent.left = this;
                    if (parent.is_vert)
                        this.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.pt.x(),
                                parent.rect.ymax());
                    else
                        this.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(),
                                parent.pt.y());
                }
            } else {
                this.is_vert = true;
                root = this;
                this.rect = new RectHV(XMIN, YMIN, XMAX, YMAX);
            }
        }

        // false if left and true if right
        boolean is_right(Point2D pt) {
            if (is_vert)
                return pt.x() > this.pt.x();
            return pt.y() > this.pt.y();
        }

        Node closest(Point2D pt) {
            return this.is_right(pt) ? right : left;
        }

        Node farthest(Point2D pt) {
            return this.is_right(pt) ? left : right;
        }
    }

    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        // NOTE: node allocations assign the memory automatically
        if (p == null)
            throw new IllegalArgumentException("The point passed to contains was null");
        if (isEmpty())
            new Node(p, null);
        Node search = root;
        while (!search.pt.equals(p) && search.closest(p) != null)
            search = search.closest(p);
        if (!search.pt.equals(p))
            new Node(p, search);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("The point passed to contains was null");
        Node search = root;
        while (search != null && !search.pt.equals(p))
            search = search.closest(p);
        return search != null;
    }

    // draw all points and colored lines to standard draw
    public void draw() {
        StdDraw.clear();
        StdDraw.setPenRadius();
        StdDraw.setCanvasSize(XMAX, YMAX);
        StdDraw.setPenColor(StdDraw.BLACK);
        draw(root);
    }

    private void draw(Node node) {
        if (node == null)
            return;

        /// this drawing
        StdDraw.point(node.pt.x(), node.pt.y());
        if (node.is_vert) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.pt.x(), node.rect.ymin(), node.pt.x(), node.rect.ymax());
        }
        if (node.is_vert) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.pt.y(), node.rect.xmax(), node.pt.y());
        }
        StdDraw.setPenColor(StdDraw.BLACK);

        // next nodes
        draw(node.left);
        draw(node.right);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("The rectangle passed to range was null");
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        range(root, rect, list);
        return list;
    }

    private void range(Node node, RectHV rect, ArrayList<Point2D> list) {
        if (node == null || !rect.intersects(node.rect))
            return;
        if (rect.contains(node.pt))
            list.add(node.pt);
        range(node.left, rect, list);
        range(node.right, rect, list);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("The point passed to nearest was null");
        if (root == null)
            return null;
        return nearest(root, root.pt, p);
    }

    private Point2D nearest(Node node, Point2D closest, Point2D query) {
        if (node == null || node.rect.distanceSquaredTo(query) > closest.distanceSquaredTo(query))
            return closest;
        if (query.distanceToOrder().compare(closest, node.pt) > 0)
            closest = node.pt;
        closest = nearest(node.closest(query), closest, query);
        closest = nearest(node.farthest(query), closest, query);
        return closest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        System.out.println("Making the kd tree");
        KdTree test = new KdTree();
        System.out.println("Contains 0.5,0.2: " + test.contains(new Point2D(0.5, 0.2)));
        System.out.println("IsEmpty: " + test.isEmpty());
        // Insertions
        System.out.println("Inserting (0.7,0.2); (0.5,0.4); (0.2,0.3); (0.4,0.7); (0.9,0.6)");
        test.insert(new Point2D(0.7, 0.2));
        test.insert(new Point2D(0.5, 0.4));
        test.insert(new Point2D(0.2, 0.3));
        test.insert(new Point2D(0.4, 0.7));
        test.insert(new Point2D(0.9, 0.6));
        // !Insertions
        System.out.println("Getting the points in the rectangle (0.3,0.25),(0.8, 0.8)");
        Iterable<Point2D> list = test.range(new RectHV(0.3, 0.25, 0.8, 0.8));
        System.out.print("The list of points is:");
        for (Point2D pt : list)
            System.out.print(" " + pt.toString());
        System.out.println();
        System.out.println("Nearest to 1,1: " + test.nearest(new Point2D(1, 1)).toString());
        System.out.println("IsEmpty: " + test.isEmpty());
        System.out.println("Size: " + test.size());
        System.out.println("Contains 0.4,0.7: " + test.contains(new Point2D(0.4, 0.7)));
        System.out.println("Drawing the KdTree");
        test.draw();
        System.out.println("KdTree testing is complete");
    }
}