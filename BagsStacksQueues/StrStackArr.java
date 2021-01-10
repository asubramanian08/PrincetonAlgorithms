package BagsStacksQueues;

//Always a debate: Use array or linklist
public class StrStackArr {
    private String[] s;
    private int N = 0;

    public StrStackArr(int capacity) { // Can't expect user to know this
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item;
    }

    public String pop() {
        // 'Loitering' version: return s[--N];
        // This one allows garbage
        // collector To free the memory
        String item = s[--N];
        s[N] = null;
        return item;
    }
}
