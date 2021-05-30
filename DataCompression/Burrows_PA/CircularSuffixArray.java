package DataCompression.Burrows_PA;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
    private Integer index[];

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException();
        index = new Integer[s.length()];
        for (int i = 0; i < index.length; i++)
            index[i] = Integer.valueOf(i);
        Arrays.sort(index, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                for (int i = 0; i < s.length(); i++) {
                    char c1 = s.charAt((o1 + i) % s.length());
                    char c2 = s.charAt((o2 + i) % s.length());
                    if (c1 == c2)
                        continue;
                    return c1 - c2;
                }
                return 0;
            }
        });
    }

    // length of s
    public int length() {
        return index.length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length())
            throw new IllegalArgumentException();
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String str = new String("ABRACADABRA!");
        StdOut.println("Calling constructor with string: " + str);
        CircularSuffixArray test = new CircularSuffixArray(str);
        StdOut.println("Looping though " + test.length() + " indices and printing");
        StdOut.print("The indices are:");
        for (int i = 0; i < test.length(); i++)
            StdOut.print(" " + test.index(i));
        StdOut.println("\nTesting over");
    }

}