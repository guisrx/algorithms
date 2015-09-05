package com.selau.algorithms.p706;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

class LCDisplayBuilder {

    private HashMap<Character, char[][]> buildtemplates() {
        final HashMap<Character, char[][]> templates = new HashMap<Character, char[][]>();

        templates.put('0', new char[][] {
            {' ', '-', ' '},
            {'|', ' ', '|'},
            {' ', ' ', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '}});

        templates.put('1', new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', '|'},
            {' ', ' ', ' '},
            {' ', ' ', '|'},
            {' ', ' ', ' '}});

        templates.put('2', new char[][] {
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', '-', ' '},
            {'|', ' ', ' '},
            {' ', '-', ' '}});

        templates.put('3', new char[][] {
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', '-', ' '}});

        templates.put('4', new char[][] {
            {' ', ' ', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', ' ', ' '}});

        templates.put('5', new char[][] {
            {' ', '-', ' '},
            {'|', ' ', ' '},
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', '-', ' '}});

        templates.put('6', new char[][] {
            {' ', '-', ' '},
            {'|', ' ', ' '},
            {' ', '-', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '}});

        templates.put('7', new char[][] {
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', ' ', ' '},
            {' ', ' ', '|'},
            {' ', ' ', ' '}});

        templates.put('8', new char[][] {
            {' ', '-', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '}});

        templates.put('9', new char[][] {
            {' ', '-', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', '-', ' '}});

        return templates;
    }

    public char[][] build(final int size, final String number) {
        final HashMap<Character, char[][]> templates = buildtemplates();

        final int columns = (size + 3) * number.length();
        final int rows = 2 * size + 3;

        final char[][] display = new char[rows][columns];

        for (int row = 0; row < rows; row++) {
            Arrays.fill(display[row], ' ');
        }

        for (int position = 0; position < number.length(); position++) {

            buildAlgarism(display, number.charAt(position), position, size, templates);
        }
        return display;
    }

    private void buildAlgarism(final char[][] display, final char algarism, final int position, final int size, final HashMap<Character,char[][]> templates) {
        final char[][] template = templates.get(algarism);

        final int displayStartColumn = (size + 3) * position;

        expandAlgarism(display, template, size, displayStartColumn);
    }

    private void expandAlgarism(final char[][] display, final char[][] template, final int size, final int startColumn) {
        int displayRow = 0;

        final char[] row0 = expandRow(template[0], size);
        copyAllto(row0, display[displayRow], startColumn);
        displayRow++;

        final char[] row1 = expandRow(template[1], size);
        for (int copyLine = 0; copyLine < size; copyLine++) {
            copyAllto(row1, display[displayRow], startColumn);
            displayRow++;
        }

        final char[] row2 = expandRow(template[2], size);
        copyAllto(row2, display[displayRow], startColumn);
        displayRow++;

        final char[] row3 = expandRow(template[3], size);
        for (int copyLine = 0; copyLine < size; copyLine++) {
            copyAllto(row3, display[displayRow], startColumn);
            displayRow++;
        }

        final char[] row4 = expandRow(template[4], size);
        copyAllto(row4, display[displayRow], startColumn);
    }

    private void copyAllto(final char[] source, final char[] target, final int startingIndex) {
        int targetIndex = startingIndex;

        for (int sourceIndex = 0; sourceIndex < source.length; sourceIndex++) {
            target[targetIndex] = source[sourceIndex];
            targetIndex++;
        }
    }

    private char[] expandRow(final char[] baseRow, final int size) {
        final int columns = size + 2;
        final char[] expandedRow = new char[columns];

        expandedRow[0] = baseRow[0];
        Arrays.fill(expandedRow, 1, size +1, baseRow[1]);
        expandedRow[size +1] = baseRow[2];

        return expandedRow;
    }

    private void print(final char[][] display) {

        for (int line = 0; line < display.length; line++) {
            System.out.println(display[line]);
        }
    }

    @Test
    public void templateTestCase() {
        final HashMap<Character, char[][]> templates = buildtemplates();

        print(templates.get('1'));
    }

    @Test
    public void displayTestCases() {

        print(build(10, "0123456789"));
    }
}

class Main {

    public static void main(final String args[]) throws FileNotFoundException {
        final LCDisplayBuilder builder = new LCDisplayBuilder();

        final Scanner inputScanner = new Scanner(new FileInputStream(args[0]));

        int size = 0;
        String number = "";

        do {
            size = inputScanner.nextInt();
            number = inputScanner.next();

            builder.build(size, number);
            System.out.println("");

        } while((size != 0) && (number != "0"));

        inputScanner.close();
    }
}
