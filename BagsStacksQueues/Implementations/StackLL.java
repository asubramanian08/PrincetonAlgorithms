package BagsStacksQueues.Implementations;

import java.util.Iterator;

//Can exemplify recursion
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
        Node old_first = first;
        first = new Node();
        first.item = item;
        first.next = old_first;
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
