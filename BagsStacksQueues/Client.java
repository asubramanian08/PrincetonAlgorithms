package BagsStacksQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//we want to make this genaric -> not just string
class Client {
    public static void main(String[] args) {
        StackLL<String> stack = new StackLL<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
}