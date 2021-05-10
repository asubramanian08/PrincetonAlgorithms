package DirectedGraphs.WordNet_PA;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import java.util.ArrayList;
import edu.princeton.cs.algs4.DirectedCycle;

public class WordNet {
    private final SAP ancestor;
    private final ST<String, ArrayList<Integer>> sets;
    private final ST<Integer, String> index;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        // init
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException();
        String[] line;
        In input;
        int id;

        // synsets (sets, index)
        sets = new ST<String, ArrayList<Integer>>();
        index = new ST<Integer, String>();
        ArrayList<Integer> currSet;
        input = new In(synsets);
        while (input.hasNextLine()) {
            line = input.readLine().split(",");
            id = Integer.parseInt(line[0]);
            index.put(id, line[1]);
            line = line[1].split(" ");
            for (String word : line) {
                currSet = sets.get(word);
                if (currSet == null)
                    currSet = new ArrayList<Integer>();
                currSet.add(id);
                sets.put(word, currSet);
            }
        }

        // hypernyms (ancestor)
        Digraph G = new Digraph(index.size());
        input = new In(hypernyms);
        while (input.hasNextLine()) {
            line = input.readLine().split(",");
            id = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++)
                G.addEdge(id, Integer.parseInt(line[i]));
        }
        if (!is_rDAG(G))
            throw new IllegalArgumentException();
        ancestor = new SAP(G);
    }

    private boolean is_rDAG(Digraph G) {
        // DAG
        if ((new DirectedCycle(G)).hasCycle())
            return false;

        // rooted
        int numRoots = 0;
        for (int i = 0; i < G.V(); i++)
            if (!G.adj(i).iterator().hasNext())
                numRoots++;
        return numRoots == 1;
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return sets;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException();
        return sets.contains(word);
    }

    // distance between nounA and nounB
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException();
        return ancestor.length(sets.get(nounA), sets.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common
    // ancestor of nounA and nounB in a shortest ancestral path
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException();
        return index.get(ancestor.ancestor(sets.get(nounA), sets.get(nounB)));
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}