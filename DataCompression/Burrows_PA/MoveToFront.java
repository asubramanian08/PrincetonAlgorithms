package DataCompression.Burrows_PA;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static char recent[] = new char[256];

    // apply move-to-front encoding, reading from standard input and writing to
    // standard output
    public static void encode() {
        arrayReset();
        char search, temp, pos;
        while (!BinaryStdIn.isEmpty()) {
            search = BinaryStdIn.readChar();
            temp = search;
            pos = (char) -1;
            do {
                pos++;
                temp = swap(temp, pos);
            } while (temp != search);
            BinaryStdOut.write(pos);
        }
        BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to
    // standard output
    public static void decode() {
        arrayReset();
        char index;
        while (!BinaryStdIn.isEmpty()) {
            index = BinaryStdIn.readChar();
            while (index > 0) {
                recent[index - 1] = swap(recent[index - 1], index);
                index--;
            }
            BinaryStdOut.write(recent[0]);
        }
        BinaryStdOut.flush();
    }

    private static char swap(char set, char index) {
        char temp = recent[index];
        recent[index] = set;
        return temp;
    }

    private static void arrayReset() {
        for (char i = 0; i < recent.length; i++)
            recent[i] = i;
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-"))
            encode();
        else if (args[0].equals("+"))
            decode();
        else
            throw new IllegalArgumentException("Enter '-' or '+' for encode or decode respectively");
    }
}