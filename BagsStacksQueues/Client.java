package BagsStacksQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class Client {
    public static void main(String[] args) {
        StringStack stack = new StringStack();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
}