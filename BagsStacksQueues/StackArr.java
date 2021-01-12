package BagsStacksQueues;

import java.util.Iterator;

//Always a debate: Use array or linklist
public class StackArr<Item> {
    private Item[] arr;
    private int N = 0;

    public StackArr(/* int capacity: Can't expect user to know this */) {
        arr = (Item[]) new Object[1/* capacity */];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (N == arr.length) // resize when out of bound
            resize(arr.length * 2);
        arr[N++] = item;
    }

    public Item pop() {
        // 'Loitering' version: return s[--N];
        // This one allows garbage
        // collector To free the memory
        Item item = arr[--N];
        arr[N] = null;
        // make sure array isn't too empty
        if (N > 0 && N == arr.length / 4)
            resize(arr.length / 2);
        return item;
    }

    public Iterator<Item> getIterator() {
        return new iterator();
    }

    private class iterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return arr[--i];
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = arr[i];
        arr = copy;
    }
}