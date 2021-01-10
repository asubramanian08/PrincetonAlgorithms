package BagsStacksQueues;

import java.util.ArrayList;

//my work based off theirs
public class QueueArr<Item> {
    private ArrayList<Item> arr;
    private int head, tail;

    public QueueArr() {
        arr = new ArrayList<Item>(1);
        tail = 0;
        head = 0;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void enqueue(Item item) {
        /*
         * if (tail == arr.size()) // resize when out of bound resize(arr.size() * 2);
         */
        arr.set(tail, item);
        tail++;
    }

    public Item dequeue() {
        // 'Loitering' version: return s[--N];
        // This one allows garbage
        // collector To free the memory
        Item item = arr.get(head);
        arr.set(head, null);
        head++;
        // make sure array isn't too empty
        /*
         * if (tail - head == arr.length / 4) resize(arr.length / 2);
         */
        return item;
    }

    /*
     * private void resize(int capacity) { Item[] copy = new Item[capacity]; for
     * (int i = head; i < tail; i++) copy[i] = arr[i]; arr = copy; tail -= head;
     * head = 0; }
     */
}
