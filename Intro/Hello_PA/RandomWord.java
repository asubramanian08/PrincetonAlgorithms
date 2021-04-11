package Intro.Hello_PA;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String str = null, currStr;
        for (double count = 1; !StdIn.isEmpty(); count++) {
            currStr = StdIn.readString();
            if (StdRandom.bernoulli(1 / count))
                str = currStr;
        }
        StdOut.println(str);
    }
}