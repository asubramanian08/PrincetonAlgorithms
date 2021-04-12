package ElementarySorts.GeneralCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import ElementarySorts.Implementations.*;

public class StringSorter {
    public static void main(String[] args) {
        String[] a = In.readStrings(args[0]);
        Insertion.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}