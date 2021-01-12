package BagsStacksQueues;

import java.util.Iterator;

//Always a debate: Use array or linklist
public class StackLL<Item> {
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
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
