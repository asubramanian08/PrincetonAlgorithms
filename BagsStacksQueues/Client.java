package BagsStacksQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//we want to make this genaric -> not just string

//Itterable interface : if client wants to go through each element.
//Key: Client shoudn't notice difference between a 
//linklist implementation or a array implementation

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