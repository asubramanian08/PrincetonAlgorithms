package DataCompression.Burrows_PA;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int CHAR_RANGE = 256;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        StringBuilder str = new StringBuilder();
        while (!BinaryStdIn.isEmpty())
            str.append(BinaryStdIn.readChar());
        CircularSuffixArray csa = new CircularSuffixArray(str.toString());
        StringBuilder trans = new StringBuilder();
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0)
                BinaryStdOut.write(i);
            trans.append(str.charAt((csa.index(i) != 0 ? csa.index(i) : str.length()) - 1));
        }
        BinaryStdOut.write(trans.toString());
        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        // read
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        // make next
        int[] pos = new int[CHAR_RANGE + 1];
        for (int i = 0; i < t.length(); i++)
            pos[t.charAt(i) + 1]++;
        for (int i = 1; i < CHAR_RANGE; i++)
            pos[i] += pos[i - 1];
        int[] next = new int[t.length()];
        for (int i = 0; i < t.length(); i++)
            next[pos[t.charAt(i)]++] = i;
        // write answer -> handle going back to spot
        for (int i = 0, loc = next[first]; i < t.length(); i++, loc = next[loc])
            BinaryStdOut.write(t.charAt(loc));
        BinaryStdOut.flush();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-"))
            transform();
        else if (args[0].equals("+"))
            inverseTransform();
        else
            throw new IllegalArgumentException("Enter '-' or '+' for transform or inverseTransform respectively");
    }
}