package ua.yandex.shad.collections;

import java.util.ArrayList;

public class Node {
    public static final int ALPHABETSIZE = 26;

    private int weight = 0;
    private String word;
    private ArrayList<Node> leafs = new ArrayList<Node>(ALPHABETSIZE);

    public Node() {
        for (int i = 0; i < ALPHABETSIZE; i++) {
            leafs.add(null);
        }
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int val) {
        this.weight = val;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String str) {
        this.word = str;
    }

    public Node getLeaf(int x) {
        return this.leafs.get(x);
    }

    public void setLeaf(int x, Node leaf) {
        this.leafs.set(x, leaf);
    }
}
