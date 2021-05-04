package SearchingApplications.TeachingCode;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//make a similar thing for blacklist
public class WhiteList {
    public static void main(String[] args) {
        // create empty set of strings
        SET<String> set = new SET<String>();
        // read in whitelist
        In in = new In(args[0]);
        while (!in.isEmpty())
            set.add(in.readString());
        // print words not in list
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (set.contains(word))
                StdOut.println(word);
        }
    }
}