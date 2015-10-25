package ua.yandex.shad.autocomplete;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;

import ua.yandex.shad.tries.Trie;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class PrefixMatchesTest {

    @Test
    public void sizeTestWithNotZeroSize() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.size()).thenReturn(10);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        int expResult = 10;
        int actResult = testP.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void sizeTestWithZeroSize() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.size()).thenReturn(0);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        int expResult = 0;
        int actResult = testP.size();

        assertEquals(expResult, actResult);
    }

    @Test
    public void loadTestWithEmptyInput() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.size()).thenReturn(0);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        int expResult = 0;
        int actResult = testP.load();

        assertEquals(expResult, actResult);
    }

    @Test
    public void loadTestWithWord() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.size()).thenReturn(1);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        int expResult = 1;
        int actResult = testP.load("test");

        assertEquals(expResult, actResult);
    }

    @Test
    public void loadTestWithString() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.size()).thenReturn(3);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        int expResult = 3;
        int actResult = testP.load("test word second");

        assertEquals(expResult, actResult);
    }

    @Test
    public void loadTestWithStringAndWord() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.size()).thenReturn(4);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        int expResult = 4;
        int actResult = testP.load("test word second", "commit");

        assertEquals(expResult, actResult);
    }

    @Test
    public void containsTestWithTrue() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.contains("test")).thenReturn(true);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        assertTrue(testP.contains("test"));
    }

    @Test
    public void containsTestWithFalse() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.contains("test")).thenReturn(false);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        assertFalse(testP.contains("test"));
    }

    @Test
    public void deleteTestWithTrue() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.delete("test")).thenReturn(true);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        assertTrue(testP.delete("test"));
    }

    @Test
    public void deleteTestWithFalse() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.delete("test")).thenReturn(false);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        assertFalse(testP.delete("test"));
    }

    @Test
    public void wordsWithPrefixTestWithEmptyOut() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.wordsWithPrefix("test")).thenReturn(new ArrayList<String>());
        PrefixMatches testP = new PrefixMatches(mockTrie);

        Iterable<String> actResult = testP.wordsWithPrefix("test");

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
    public void wordsWithPrefixWithParameterTestWithEmptyOut() {
        Trie mockTrie = mock(Trie.class);
        when(mockTrie.wordsWithPrefix("test")).thenReturn(new ArrayList<String>());
        PrefixMatches testP = new PrefixMatches(mockTrie);

        Iterable<String> actResult = testP.wordsWithPrefix("test", 1);

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
    public void wordsWithPrefixWithParameterTestWithWord() {
        Trie mockTrie = mock(Trie.class);

        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("test");

        when(mockTrie.wordsWithPrefix("test")).thenReturn(expResult);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        Iterable<String> actResult = testP.wordsWithPrefix("test", 1);

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsWithPrefixTestWithWordsWithDifferentLength() {
        Trie mockTrie = mock(Trie.class);

        ArrayList<String> ans = new ArrayList<String>();
        ans.add("tested");
        ans.add("test");

        when(mockTrie.wordsWithPrefix("test")).thenReturn(ans);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("test");
        expResult.add("tested");


        Iterable<String> actResult = testP.wordsWithPrefix("test");

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsWithPrefixWithParameterTestWithWordsWithDifferentLengthWithParameterThatNotCut() {
        Trie mockTrie = mock(Trie.class);

        ArrayList<String> ans = new ArrayList<String>();
        ans.add("tested");
        ans.add("test");

        when(mockTrie.wordsWithPrefix("test")).thenReturn(ans);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("test");
        expResult.add("tested");


        Iterable<String> actResult = testP.wordsWithPrefix("test", 4);

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test
    public void wordsWithPrefixWithParameterTestWithWordsWithDifferentLengthWithParameterThatCutAnswer() {
        Trie mockTrie = mock(Trie.class);

        ArrayList<String> ans = new ArrayList<String>();
        ans.add("tested");
        ans.add("test");

        when(mockTrie.wordsWithPrefix("test")).thenReturn(ans);
        PrefixMatches testP = new PrefixMatches(mockTrie);

        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("test");


        Iterable<String> actResult = testP.wordsWithPrefix("test", 1);

        int expLength = expResult.size();
        int actLength = 0;

        for (String x : actResult) {
            assertTrue(expResult.contains(x));
            actLength++;
        }

        assertEquals(expLength, actLength);
    }

    @Test(expected = InputMismatchException.class)
    public void wordsWithPrefixWithParameterTestWithWrongInput() {
        Trie mockTrie = mock(Trie.class);

        PrefixMatches testP = new PrefixMatches(mockTrie);

        testP.wordsWithPrefix("1");
    }

    @Test
    public void emptyConstructorTest() {
        PrefixMatches Pr = new PrefixMatches();
    }
}
