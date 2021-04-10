package BagsStacksQueues.Implementations;

import java.util.Iterator;

public class QueueLL<Item> {
    private Node first, last;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item item) {
        Node old_last = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            old_last.next = last;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return item;
    }

    public Iterator<Item> getIterator() {
        return new iterator();
    }

    private class iterator implements Iterator<Item> {
        private Node i = first;

        public boolean hasNext() {
            return i.next != null;
        }

        public Item next() {
            return (i = i.next).item;
        }
    }
}
