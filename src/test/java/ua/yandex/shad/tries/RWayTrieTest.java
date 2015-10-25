package ua.yandex.shad.tries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class RWayTrieTest {

    @Test
    public void containsTestOnEmptyTrieWithEmptyWord() {
        RWayTrie testTrie = new RWayTrie();

        assertFalse(testTrie.contains(""));
    }

    @Test
    public void containsTestOnEmptyTrie() {
        RWayTrie testTrie = new RWayTrie();

        assertFalse(testTrie.contains("test"));
    }

    @Test
    public void addTestWithOneWord() {
        Tuple testTuple = new Tuple("word", 4);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(testTuple);
    }

    @Test
    public void containsTestOnNotEmptyTrieWithWordThatInTrie() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(testTuple);

        assertTrue(testTrie.contains(testTuple.term));
    }

    @Test
    public void sizeTestOnEmptyTrie() {
        RWayTrie testTrie = new RWayTrie();

        int expResult = 0;
        int actResult = testTrie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void sizeTestOnTrieWithOneWord() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();
        testTrie.add(testTuple);

        int expResult = 1;
        int actResult = testTrie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void addTestWithAddingSameWord() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();

        int expResult = 1;

        testTrie.add(testTuple);
        testTrie.add(testTuple);
        int actResult = testTrie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void sizeTestOnTrieWithFewDifferentWords() {
        Tuple fTuple = new Tuple("test", 4),
                sTuple = new Tuple("second", 6),
                tTuple = new Tuple("word", 4),
                vTuple = new Tuple("tested", 6);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(fTuple);
        testTrie.add(sTuple);
        testTrie.add(tTuple);
        testTrie.add(vTuple);

        int expResult = 4;
        int actResult = testTrie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void deleteTestOnEmptyTrie() {
        RWayTrie testTrie = new RWayTrie();

        assertFalse(testTrie.delete("test"));
    }

    @Test
    public void deleteTestWithAddingAndDeletingOneWord() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(testTuple);

        assertTrue(testTrie.delete(testTuple.term));
    }

    @Test
    public void containsAfterDeleteTestWithAddingAndDeletingOneWord() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(testTuple);
        testTrie.delete(testTuple.term);

        assertFalse(testTrie.contains(testTuple.term));
    }

    @Test
    public void sizeAfterDeleteTestWithAddingAndDeletingOneWord() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();

        int expResult = 0;

        testTrie.add(testTuple);
        testTrie.delete(testTuple.term);
        int actResult = testTrie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void deleteTestWithNotEmptyTrieAndDeletingWordThatNotContained() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(testTuple);

        assertFalse(testTrie.delete("word"));
    }

    @Test
    public void containsAfterDeleteTestWithNotEmptyTrieAndDeletingWordThatNotContained() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(testTuple);
        testTrie.delete("word");

        assertTrue(testTrie.contains(testTuple.term));
    }

    @Test
    public void sizeAfterDeleteTestWithNotEmptyTrieAndDeletingWordThatNotContained() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();

        int expResult = 1;

        testTrie.add(testTuple);
        testTrie.delete("word");
        int actResult = testTrie.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void wordsTestOnEmptyTrie() {
        RWayTrie testTrie = new RWayTrie();

        Iterable<String> actResult = testTrie.words();

        ArrayList<String> expResult = new ArrayList<String>();

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsTestOnTrieWithOneWord() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();
        testTrie.add(testTuple);

        Iterable<String> actResult = testTrie.words();

        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("test");

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsTestOnTrieWithFewWords() {
        Tuple fTuple = new Tuple("test", 4),
                sTuple = new Tuple("second", 6),
                tTuple = new Tuple("word", 4),
                vTuple = new Tuple("tested", 6);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(fTuple);
        testTrie.add(sTuple);
        testTrie.add(tTuple);
        testTrie.add(vTuple);

        Iterable<String> actResult = testTrie.words();

        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("test");
        expResult.add("second");
        expResult.add("word");
        expResult.add("tested");

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsWithPrefixTestOnEmptyTrie() {
        RWayTrie testTrie = new RWayTrie();

        Iterable<String> actResult = testTrie.wordsWithPrefix("test");

        ArrayList<String> expResult = new ArrayList<String>();

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsWithPrefixTestOnNotEmptyTrieWithPrefixThatNotContained() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();
        testTrie.add(testTuple);

        Iterable<String> actResult = testTrie.wordsWithPrefix("word");

        ArrayList<String> expResult = new ArrayList<String>();

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsWithPrefixTestOnTrieWithOneWord() {
        Tuple testTuple = new Tuple("test", 4);
        RWayTrie testTrie = new RWayTrie();
        testTrie.add(testTuple);

        Iterable<String> actResult = testTrie.wordsWithPrefix("test");

        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("test");

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsWithPrefixTestOnTrieWithFewWords() {
        Tuple fTuple = new Tuple("test", 4),
                sTuple = new Tuple("second", 6),
                tTuple = new Tuple("word", 4),
                vTuple = new Tuple("tested", 6);
        RWayTrie testTrie = new RWayTrie();

        testTrie.add(fTuple);
        testTrie.add(sTuple);
        testTrie.add(tTuple);
        testTrie.add(vTuple);

        Iterable<String> actResult = testTrie.wordsWithPrefix("test");

        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("test");
        expResult.add("tested");

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }
}
