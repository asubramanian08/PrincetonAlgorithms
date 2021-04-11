package BagsStacksQueues.Queues_PA;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    // node class
    private class Node {
        public Item curr;
        public Node prev, next;

        public Node(Item curr, Node prev, Node next) {
            this.curr = curr;
            this.prev = prev;
            this.next = next;
        }
    }

    // iterator class
    private class DequeIter implements Iterator<Item> {
        Node current;

        public DequeIter(Node list) {
            current = list;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item data = current.curr;
            current = current.next;
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // variables
    private Node first, last;
    private int length;

    // construct an empty deque
    public Deque() {
        length = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return length == 0;
    }

    // return the number of items on the deque
    public int size() {
        return length;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node temp = new Node(item, null, first);
        if (first != null)
            first.prev = temp;
        if (last == null)
            last = temp;
        first = temp;
        length++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node temp = new Node(item, last, null);
        if (last != null)
            last.next = temp;
        if (first == null)
            first = temp;
        last = temp;
        length++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item temp = first.curr;
        if (last == first)
            last = null;
        first = first.next;
        if (first != null)
            first.prev = null;
        length--;
        return temp;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item temp = last.curr;
        if (first == last)
            first = null;
        last = last.prev;
        if (last != null)
            last.next = null;
        length--;
        return temp;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIter(first);
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("Calling the constructor");
        Deque<Integer> test = new Deque<Integer>();
        StdOut.println("The deque returns " + test.isEmpty() + " for isEmpty");
        StdOut.println("The deque returns " + test.size() + " for size");
        StdOut.println("Adding 7 to last, then 2 to first");
        test.addLast(Integer.valueOf(7));
        test.addFirst(Integer.valueOf(2));
        StdOut.println("Getting Iterator and printing elements");
        var iter = test.iterator();
        while (iter.hasNext())
            StdOut.print(iter.next() + " ");
        StdOut.print('\n');
        StdOut.println("Remove first returns " + test.removeFirst());
        StdOut.println("Size returns " + test.size());
        StdOut.println("Remove last returns " + test.removeLast());
        try {
            StdOut.println("Remove last(again) returns " + test.removeLast());
        } catch (NoSuchElementException e) {
            StdOut.println("Caught NoSuchElementException");
        }
        StdOut.println("Testing ended");
    }
}