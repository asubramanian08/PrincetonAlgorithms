package SubstringSearch.Boggle_PA;

import edu.princeton.cs.algs4.Bag;

public class Trie {
    public class Node {
        private boolean isWord;
        private Node next[];

        Node() {
            this.isWord = false;
            next = new Node[CHAR_RANGE];
        }

        public boolean isWord() {
            return isWord;
        }
    };

    private static final int CHAR_RANGE = 26;
    private static final char START_CHAR = 'A';
    private static final int NA = -1;
    private final Node root;
    private int nextString;

    Trie() {
        root = new Node();
        nextString = NA;
    }

    public Node next(Node nd, char ch, boolean isWord) {
        if (!inRange(ch))
            return null;
        ch -= START_CHAR;
        if (nd.next[ch] == null) {
            nd.next[ch] = new Node();
            if (nextString != NA)
                nextString++;
        }
        if (isWord)
            nextString = 0;
        nd.next[ch].isWord |= isWord;
        return nd.next[ch];
    }

    public Node next(Node nd, char ch) {
        if (!inRange(ch))
            return null;
        return nd.next[ch - START_CHAR];
    }

    private boolean inRange(char ch) {
        return (ch >= START_CHAR) && (ch < START_CHAR + CHAR_RANGE);
    }

    public void leave(Node nd, char ch) {
        if (nextString != 0)
            nd.next[ch - START_CHAR] = null;
        if (nextString > 0)
            nextString--;
    }

    public void add(String word) {
        Node pos = root;
        for (int i = 0; i < word.length(); i++)
            pos = next(pos, word.charAt(i), i == word.length() - 1);
    }

    public boolean is_in(String word) {
        Node pos = root;
        for (int i = 0; i < word.length(); i++) {
            pos = next(pos, word.charAt(i));
            if (pos == null)
                return false;
        }
        return pos.isWord;
    }

    public Iterable<String> words() {
        Bag<String> ans = new Bag<String>();
        DFS(root, ans, new StringBuilder());
        return ans;
    }

    private void DFS(Node pos, Bag<String> ans, StringBuilder str) {
        if (pos == null)
            return;
        for (int i = pos.next.length - 1; i >= 0; i--) {
            str.append((char) (i + START_CHAR));
            if (i + START_CHAR == 'Q')
                str.append('U');
            DFS(pos.next[i], ans, str);
            str.delete(str.length() - (i + START_CHAR == 'Q' ? 2 : 1), str.length());
        }
        if (pos.isWord)
            ans.add(String.valueOf(str));
    }

    public Node root() {
        return root;
    }
}