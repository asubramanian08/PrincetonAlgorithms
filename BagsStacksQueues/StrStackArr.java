package BagsStacksQueues;

//Always a debate: Use array or linklist
public class StrStackArr {
    private String[] arr;
    private int N = 0;

    public StrStackArr(/* int capacity: Can't expect user to know this */) {
        arr = new String[1/* capacity */];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        if (N == arr.length) // resize when out of bound
            resize(arr.length * 2);
        arr[N++] = item;
    }

    public String pop() {
        // 'Loitering' version: return s[--N];
        // This one allows garbage
        // collector To free the memory
        String item = arr[--N];
        arr[N] = null;
        // make sure array isn't too empty
        if (N > 0 && N == arr.length / 4)
            resize(arr.length / 2);
        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = arr[i];
        arr = copy;
    }
}
