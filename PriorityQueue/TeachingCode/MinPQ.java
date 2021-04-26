package PriorityQueue.TeachingCode;

public class MinPQ<Item> {
    private int length; // Already handled
    private Item[] PQ_arr;

    public MinPQ() {
        length = 0;
        // constructor stuff here
    }

    public void insert(Item i) {
        length++;
        // do the insertion
    }

    public int size() {
        return length;
    }

    public Item delMin() {
        length--;
        return PQ_arr[0];
    }

    public boolean isEmpty() {
        return length == 0;
    }
}
