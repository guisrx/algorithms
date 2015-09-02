package com.selau.algorithms.amazon;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Problem A: Concatenate chars until word delimiter is found.
 * Then store the found and the already found words in a set
 * with access O(1) and also verify if this word was already added.
 *
 */

interface Stream {

    char next();

    boolean hasNext();
}

class CharStream implements Stream {

    private final String input;
    private int index = 0;

    public CharStream(final String input) {
        this.input = input;
    }

    @Override
    public char next() {
        if ((this.index >= this.input.length()) || (this.input == null))
            throw new IllegalArgumentException();

        final char character = input.charAt(this.index);
        this.index++;

        return character;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.input.length();
    }
}

public class QuestionA {

    private static final String EMPTY = "";
    private static final char SPACE = ' ';
    private static final char DOT = '.';

    public static String lastRepeated(final Stream input) {
        final Set<String> wordsRead = new HashSet<String>();

        String lastRepeated = EMPTY;
        StringBuffer wordBuffer = new StringBuffer();

        while (input.hasNext()) {
            final char nextCharacter = input.next();

            if ((nextCharacter == SPACE) || (nextCharacter == DOT)) {
                if (wordBuffer.length() > 0) {
                    final String newWord = wordBuffer.toString();

                    if (wordsRead.contains(newWord)) {
                        lastRepeated = newWord;
                    }
                    wordsRead.add(newWord);
                }
                wordBuffer = new StringBuffer();
            } else {
                wordBuffer.append(nextCharacter);
            }
        }
        return lastRepeated;
    }

    @Test
    public void testCases() {

        final Stream streamA = new CharStream("The angry dog was red. And the cat was angry too.");
        Assert.assertEquals("angry", QuestionA.lastRepeated(streamA));

        final Stream streamB = new CharStream("The angry dog was red.");
        Assert.assertEquals("", QuestionA.lastRepeated(streamB));

        final Stream streamC = new CharStream("The angry dog was red. The angry dog was red.");
        Assert.assertEquals("red", QuestionA.lastRepeated(streamC));

        final Stream streamD = new CharStream("");
        Assert.assertEquals("", QuestionA.lastRepeated(streamD));
    }

}
