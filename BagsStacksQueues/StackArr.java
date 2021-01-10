package BagsStacksQueues;

import java.util.ArrayList;

//Always a debate: Use array or linklist
public class StackArr<Item> {
    private ArrayList<Item> arr;
    private int N = 0;

    public StackArr(/* int capacity: Can't expect user to know this */) {
        arr = new ArrayList<Item>(/* capacity */1);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        /*
         * if (N == arr.length) // resize when out of bound resize(arr.length * 2);
         */
        arr.set(N, item);
        N++;
    }

    public Item pop() {
        // 'Loitering' version: return s[--N];
        // This one allows garbage
        // collector To free the memory
        N--;
        Item item = arr.get(N);
        arr.set(N, null);
        // make sure array isn't too empty
        /*
         * if (N > 0 && N == arr.length / 4) resize(arr.length / 2);
         */
        return item;
    }

    /*
     * private void resize(int capacity) { Item[] copy = new Item[capacity]; for
     * (int i = 0; i < N; i++) copy[i] = arr[i]; arr = copy; }
     */
}
