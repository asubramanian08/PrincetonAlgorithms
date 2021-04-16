package BagsStacksQueues.TeachingCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import BagsStacksQueues.Implementations.*;

//we want to make this generic -> not just string

//Iterable interface : if client wants to go through each element.
//Key: Client shouldn't notice difference between a 
//link-list implementation or a array implementation

//BAG: Put things in bag but don't take out
//Same imp. as stack with no pop, or queue with no dequeue
//Have Iterator *** necessary

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