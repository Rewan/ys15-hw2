package ua.yandex.shad.tries;

import ua.yandex.shad.collections.Node;
import ua.yandex.shad.collections.NodeQueue;

import java.util.ArrayList;

public class RWayTrie implements Trie {

    public static final int RSIZE = 26;

    private Node root = new Node();
    private int containsWords = 0;

    private void addNode(Node t, int pos, Tuple val) {
        if (pos == val.getTerm().length()) {
            t.setWeight(val.getWeight());
            t.setWord(val.getTerm());
            return;
        }

        int l = val.getTerm().charAt(pos) - 'a';

        Node next = t.getLeaf(l);
        if (next == null) {
            next = new Node();
            t.setLeaf(l, next);
            t.setWord(val.getTerm().substring(0, pos));
        }

        addNode(next, pos + 1, val);
    }

    private boolean containsNode(Node t, int pos, String word) {
        if (t == null) {
            return false;
        }

        if (pos == word.length()) {
            return t.getWeight() > 0;
        }

        int l = word.charAt(pos) - 'a';
        return containsNode(t.getLeaf(l), pos + 1, word);
    }

    private void deleteNode(Node t, int pos, String word) {
        if (pos == word.length()) {
            t.setWeight(0);
            return;
        }

        int l = word.charAt(pos) - 'a';
        deleteNode(t.getLeaf(l), pos + 1, word);
    }

    private Iterable<String> wordsNode(Node t) {
        NodeQueue q = new NodeQueue();
        NodeQueue ans = new NodeQueue();

        q.push(t);

        while (!q.isEmpty()) {
            Node next = q.front();
            q.pop();

            if (next.getWeight() > 0) {
                ans.push(next);
            }

            for (int i = 0; i < RSIZE; i++) {
                if (next.getLeaf(i) != null) {
                    q.push(next.getLeaf(i));
                }
            }
        }

        ArrayList<String> wordList = new ArrayList<String>();
        for (int i = 0; i < ans.size(); i++) {
            wordList.add(ans.getNode(i).getWord());
        }

        return wordList;
    }

    @Override
    public void add(Tuple t) {
        if (!contains(t.getTerm())) {
            containsWords++;
            addNode(root, 0, t);
        }
    }

    @Override
    public boolean contains(String word) {
        return containsNode(root, 0, word);
    }

    @Override
    public boolean delete(String word) {
        if (contains(word)) {
            containsWords--;
            deleteNode(root, 0, word);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> words() {
        return wordsNode(root);
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node t = root;
        int pos = 0;

        while (pos < s.length()) {
            if (t == null) {
                return new ArrayList<String>();
            }

            int l = s.charAt(pos) - 'a';
            t = t.getLeaf(l);
            pos++;
        }

        return wordsNode(t);
    }

    @Override
    public int size() {
        return containsWords;
    }
}
