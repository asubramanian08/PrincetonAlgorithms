package BagsStacksQueues.OtherCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import BagsStacksQueues.Implementations.*;

//made by dijkstra
public class EvaluateExpression {
    public static void main(String[] args) {
        StackLL<String> ops = new StackLL<String>();
        StackLL<Double> vals = new StackLL<Double>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("("))
                ;
            else if (s.equals("+"))
                ops.push(s);
            else if (s.equals("*"))
                ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+"))
                    vals.push(vals.pop() + vals.pop());
                else if (op.equals("*"))
                    vals.push(vals.pop() * vals.pop());
            } else
                vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}