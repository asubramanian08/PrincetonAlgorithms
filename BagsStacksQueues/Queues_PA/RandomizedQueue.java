package BagsStacksQueues.Queues_PA;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int length;
    private Item[] queue;

    private class RandIter implements Iterator<Item> {
        int[] arr;
        int pos;

        public RandIter() {
            pos = 0;
            arr = new int[length];
            for (int i = 0; i < arr.length; i++)
                arr[i] = i;
            int randPos, temp;
            for (int i = 0; i < arr.length; i++) {
                randPos = StdRandom.uniform(arr.length);
                temp = arr[i];
                arr[i] = arr[randPos];
                arr[randPos] = temp;
            }

        }

        public boolean hasNext() {
            return pos != arr.length;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item data = queue[arr[pos]];
            pos++;
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        length = 0;
        queue = (Item[]) new Object[1];
    }

    private void swap(int p1, int p2) {
        Item temp = queue[p1];
        queue[p1] = queue[p2];
        queue[p2] = temp;
    }

    private void resize(int size) {
        Item[] temp = (Item[]) new Object[size];
        System.arraycopy(queue, 0, temp, 0, length);
        queue = temp;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return length == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return length;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (length == queue.length)
            resize(queue.length * 2);
        queue[length++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int pos = StdRandom.uniform(length);
        Item val = queue[pos];
        queue[pos] = null;
        swap(pos, --length);
        if (length < queue.length / 4)
            resize(queue.length / 2);
        return val;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int pos = StdRandom.uniform(length);
        return queue[pos];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandIter();
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("Calling the constructor");
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        StdOut.println("The deque returns " + test.isEmpty() + " for isEmpty");
        StdOut.println("The deque returns " + test.size() + " for size");
        StdOut.println("Adding 1 though 5");
        for (int i = 0; i < 5; i++)
            test.enqueue(Integer.valueOf(i + 1));
        StdOut.println("Getting Iterator and printing elements");
        var iter = test.iterator();
        while (iter.hasNext())
            StdOut.print(iter.next() + " ");
        StdOut.print('\n');
        StdOut.println("Deque returns " + test.dequeue());
        StdOut.println("Size returns " + test.size());
        StdOut.println("Sample returns " + test.sample());
        StdOut.println("Calling Enque with null argument");
        try {
            test.enqueue(null);
        } catch (IllegalArgumentException e) {
            StdOut.println("IllegalArgumentException");
        }
        StdOut.println("Testing ended");
    }
}