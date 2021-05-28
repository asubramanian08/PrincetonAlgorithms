package SubstringSearch.Boggle_PA;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver {
    private static final int score[] = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };
    private static final int minLen = 3;
    private final Trie dict;
    private Trie words;
    private boolean vis[][];
    private BoggleBoard board;

    // Initializes the data structure using the given array of strings as the
    // dictionary. (You can assume each word in the dictionary contains only the
    // uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        dict = new Trie();
        for (String word : dictionary)
            dict.add(word);
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        vis = new boolean[board.rows()][board.cols()];
        words = new Trie();
        this.board = board;
        for (int i = 0; i < board.rows(); i++)
            for (int j = 0; j < board.cols(); j++)
                DFS(i, j, dict.root(), words.root(), 1);
        Iterable<String> ans = words.words();
        vis = null;
        words = null;
        this.board = null;
        return ans;
    }

    private void DFS(int x, int y, Trie.Node dictPosPar, Trie.Node wordsPosPar, int depth) {
        Trie.Node dictPos, wordsPos;

        // for this node
        if ((x < 0 || x >= board.rows()) || (y < 0 || y >= board.cols()) || vis[x][y])
            return; // check it is a valid pos AND is not visited
        // change "pointers"
        char let = board.getLetter(x, y);
        if ((dictPos = dict.next(dictPosPar, let)) == null)
            return; // can't be a word
        if (let == 'Q' && (dictPos = dict.next(dictPos, 'U')) == null)
            return; // for Q -> doing again
        wordsPos = words.next(wordsPosPar, let, depth >= minLen && dictPos.isWord());

        // continue the recursion
        vis[x][y] = true;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                DFS(x + i, y + j, dictPos, wordsPos, depth + (let == 'Q' ? 2 : 1));
        vis[x][y] = false;
        words.leave(wordsPosPar, let);
    }

    // Returns the score of the given word if it is in the dictionary, zero
    // otherwise. (You can assume the word contains only the uppercase letters A
    // through Z.)
    public int scoreOf(String word) {
        if (!dict.is_in(word))
            return 0;
        if (word.length() >= score.length)
            return score[score.length - 1];
        return score[word.length()];
    }

    // Testing given
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}