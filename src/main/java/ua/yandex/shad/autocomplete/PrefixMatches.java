package ua.yandex.shad.autocomplete;

import ua.yandex.shad.tries.RWayTrie;
import ua.yandex.shad.tries.Tuple;
import ua.yandex.shad.tries.Trie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Collections;
import java.io.Serializable;

public class PrefixMatches {

    private Trie trie;

    public PrefixMatches() {
        this.trie = new RWayTrie();
    }

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    private void load(String str) {
        String[] arr = str.split(" ");
        for (String word : arr) {
            trie.add(new Tuple(word, word.length()));
        }
    }

    public int load(String... strings) {
        for (String str : strings) {
            load(str);
        }

        return trie.size();
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    private static class WordLength
            implements Comparator<String>,  Serializable {
        private static final long serialVersionUID = 42L;
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        int k = 1 + 1 + 1;
        return wordsWithPrefix(pref, k);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() >= 2) {
            Iterable<String> res = trie.wordsWithPrefix(pref);
            ArrayList<String> ans = new ArrayList<String>();

            for (String x : res) {
                ans.add(x);
            }

            if (ans.size() > 0) {
                Collections.sort(ans, new WordLength());
            }

            int d = 0;
            ArrayList<String> cuttedAns = new ArrayList<String>();
            int was = -1;

            for (int i = 0; i < ans.size(); i++) {
                String x = ans.get(i);

                if (was != x.length()) {
                    d++;
                }
                if (d > k) {
                    break;
                }

                cuttedAns.add(x);

                was = x.length();
            }

            return cuttedAns;
        }
        throw new InputMismatchException();
    }

    public int size() {
        return trie.size();
    }
}
