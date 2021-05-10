package DirectedGraphs.WordNet_PA;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet compare;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        compare = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxDist = 0, currDist;
        String currOut = nouns[0];
        for (String noun1 : nouns) {
            currDist = 0;
            for (String noun2 : nouns)
                currDist += compare.distance(noun1, noun2);
            if (maxDist < currDist) {
                maxDist = currDist;
                currOut = noun1;
            }
        }
        return currOut;
    }

    // see test client below (given)
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}