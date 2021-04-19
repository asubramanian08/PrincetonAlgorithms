package Quicksort.TeachingCode;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StringSort {
    public static void main(String[] args) {
        String[] a = StdIn.readStrings();
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}