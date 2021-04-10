package BagsStacksQueues.Implementations;

import java.util.Iterator;

//my work based off theirs
public class QueueArr<Item> {
    private Item[] arr;
    private int head, tail;

    public QueueArr() {
        arr = (Item[]) new Object[1];
        tail = 0;
        head = 0;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void enqueue(Item item) {
        if (tail == arr.length) // resize when out of bound
            resize(arr.length * 2);
        arr[tail++] = item;
    }

    public Item dequeue() {
        // 'Loitering' version: return s[--N];
        // This one allows garbage
        // collector To free the memory
        Item item = arr[head];
        arr[head++] = null;
        // make sure array isn't too empty
        if (tail - head == arr.length / 4)
            resize(arr.length / 2);
        return item;
    }

    public Iterator<Item> getIterator() {
        return new iterator();
    }

    private class iterator implements Iterator<Item> {
        private int i = head;

        public boolean hasNext() {
            return i < tail;
        }

        public Item next() {
            return arr[i++];
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = head; i < tail; i++)
            copy[i] = arr[i];
        arr = copy;
        tail -= head;
        head = 0;
    }
}