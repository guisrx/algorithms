package com.selau.algorithms.skiena.p10267;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/** Rules:
*
* I M N => Creates a new table M x N. All the pixels are colored in white (O).
* C => Clears the table. The size remains the same. All the pixels became white (O).
* L X Y C => Colors the pixel with coordinates (X, Y) in colour C.
* V X Y1 Y2 C => Draws the vertical segment in the column X between the rows Y1 and Y2 inclusive in colour C.
* H X1 X2 Y C => Draws the horizontal segment in the row Y between the columns X1 and X2 inclusive in colour C.
* K X1 Y1 X2 Y2 C => Draws the filled rectangle in colour C. (X1, Y1) is the upper left corner, (X2, Y2) is the
*      lower right corner of the rectangle.
* F X Y C => Fills the region with the colour C. The region R to be filled is defined as follows. The pixel (X, Y)
*      belongs to this region. The other pixel belongs to the region R if and only if it has the same colour as
*      pixel (X, Y) and a common side with any pixel which belongs to this region.
* S Name => Writes the picture in the file Name.
* X => Terminates the session.
*
* @author selau
*
*/
class Main {


    private static class Point {

        final int x;
        final int y;

        public Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Point))
                return false;
            Point other = (Point) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
    }

    static class GraphicalEditor {

        public static final char WHITE = 'O';


        public char[][] create(final int m, final int n) {
            if (! ((m > 0) && (n > 0)))
                throw new IllegalArgumentException("Picture with invalid dimensions: " + m + " x " + n);

            final char[][] picture = new char[m][n];

            return clear(picture);
        }

        public char[][] clear(final char[][] picture) {
            validatePicture(picture);

            for (int index = 0; index < picture.length; index++)
                Arrays.fill(picture[index], WHITE);

            return picture;
        }

        public char[][] color(final char[][] picture, final int x, final int y, final char color) {
            validatePictureAndCoordinates(picture, x, y);

            picture[x][y] = color;

            return picture;
        }

        public char[][] drawVerticalSegment(
                final char[][] picture,
                final int column,
                final int line1,
                final int line2,
                final char color) {

            validateColumnAndLines(picture, column, line1, line2);

            if (line1 <= line2)
                for (int index = line1; index <= line2; index++)
                    picture[index][column] = color;
            else
                for (int index = line2; index <= line1; index++)
                    picture[index][column] = color;

            return picture;
        }

        public char[][] drawHorizontalSegment(
                final char[][] picture,
                final int line,
                final int column1,
                final int column2,
                final char color) {

            validatePictureLineAndColumns(picture, line, column1, column2);

            if (column1 <= column2)
                for (int index = column1; index <= column2; index++)
                    picture[line][index] = color;
            else
                for (int index = column2; index <= column1; index++)
                    picture[line][index] = color;

            return picture;
        }

        public char[][] drawFilledRectangle(
                final char[][] picture,
                final int point1x,
                final int point1y,
                final int point2x,
                final int point2y,
                final char color) {

            validatePictureAndPoints(picture, point1x, point1y, point2x, point2y);

            final int fromLine = (point1x < point2x) ? point1x : point2x;
            final int toLine = (point1x < point2x) ? point2x : point1x;

            final int fromColumn = (point1y < point2y) ? point1y : point2y;
            final int toColumn = (point1y < point2y) ? point2y : point1y;

            for (int lineIndex = fromLine; lineIndex < toLine; lineIndex++)
                for (int columnIndex = fromColumn; columnIndex < toColumn; columnIndex++)
                    picture[lineIndex][columnIndex] = color;

            return picture;
        }

        public char[][] fillRegion(final char[][] picture, final int pointX, final int pointY, final char color) {

            validatePictureAndCoordinates(picture, pointX, pointY);

            final int originalColor = picture[pointX][pointY];

            final int maxX = picture.length;
            final int maxY = picture[0].length;
            final int pictureSize = maxX * maxY;

            final Set<Point> visited = new HashSet<Point>(pictureSize);
            final List<Point> adjacents = new ArrayList<Point>(pictureSize);

            adjacents.add(new Point(pointX, pointY));

            while (adjacents.size() > 0) {

                final Point p = adjacents.remove(0);

                picture[p.x][p.y] = color;
                visited.add(p);

                final Point quad1 = new Point(p.x +1, p.y -1);
                final Point quad2 = new Point(p.x +1, p.y);
                final Point quad3 = new Point(p.x +1, p.y +1);

                final Point quad4 = new Point(p.x, p.y -1);
                final Point quad5 = new Point(p.x, p.y +1);

                final Point quad6 = new Point(p.x -1, p.y -1);
                final Point quad7 = new Point(p.x -1, p.y);
                final Point quad8 = new Point(p.x -1, p.y +1);

                if (p.x +1 < maxX) {

                    if ((p.y -1 >= 0) && (picture[p.x +1][p.y -1] == originalColor) && (! visited.contains(quad1)))
                        adjacents.add(quad1);

                    if ((picture[p.x +1][p.y] == originalColor) && (! visited.contains(quad2)))
                        adjacents.add(quad2);

                    if ((p.y +1 < maxY) && (picture[p.x +1][p.y +1] == originalColor) && (! visited.contains(quad3)))
                        adjacents.add(quad3);
                }

                if ((p.y -1 >= 0) && (picture[p.x][p.y -1] == originalColor) && (! visited.contains(quad4)))
                    adjacents.add(quad4);

                if ((p.y +1 < maxY) && (picture[p.x][p.y +1] == originalColor) && (! visited.contains(quad5)))
                    adjacents.add(quad5);

                if (p.x -1 >= 0) {

                    if ((p.y -1 >= 0) && (picture[p.x -1][p.y -1] == originalColor) && (! visited.contains(quad6)))
                        adjacents.add(quad6);

                    if ((picture[p.x -1][p.y] == originalColor) && (! visited.contains(quad7)))
                        adjacents.add(quad7);

                    if ((p.y +1 < maxY) && (picture[p.x -1][p.y +1] == originalColor) && (! visited.contains(quad8)))
                        adjacents.add(quad8);
                }

                visited.add(quad1);
                visited.add(quad2);
                visited.add(quad3);
                visited.add(quad4);
                visited.add(quad5);
                visited.add(quad6);
                visited.add(quad7);
                visited.add(quad8);
            }
            return picture;
        }

        public char[][] writePicture(final char[][] picture, final String name) {
             validatePicture(picture);

             if (name == null)
                 throw new IllegalArgumentException("Invalid file name: " + name);

             System.out.println(name);

             for (int lineIndex = 0; lineIndex < picture.length; lineIndex++) {
                 final StringBuilder builder = new StringBuilder(picture[0].length);

                 for (int columnIndex = 0; columnIndex < picture[0].length; columnIndex++)
                     builder.append(picture[lineIndex][columnIndex]);

                 System.out.println(builder.toString());
             }
             return picture;
        }

        private void validatePictureAndPoints(
                final char[][] picture,
                final int point1x,
                final int point1y,
                final int point2x,
                final int point2y) {

            validatePicture(picture);
            validateLine(picture, point1x);
            validateColumn(picture, point1y);
            validateLine(picture, point2x);
            validateColumn(picture, point2y);
        }

        private void validateColumnAndLines(final char[][] picture, final int column, final int line1, final int line2) {
            validatePicture(picture);
            validateColumn(picture, column);
            validateLine(picture, line1);
            validateLine(picture, line2);
        }

        private void validatePictureLineAndColumns(final char[][] picture, final int line, final int column1, final int column2) {
            validatePicture(picture);
            validateLine(picture, line);
            validateColumn(picture, column1);
            validateColumn(picture, column2);
        }

        private void validatePictureAndCoordinates(final char[][] picture, final int x, final int y) {
            validatePicture(picture);
            validateLine(picture, x);
            validateColumn(picture, y);
        }

        private void validateLine(final char[][] picture, final int line1) {
            if ((line1 < 0) || (line1 >= picture.length))
                throw new IllegalArgumentException("Invalid line: " + line1);
        }

        private void validateColumn(final char[][] picture, final int column) {
            if ((column < 0) || (column >= picture[0].length))
                throw new IllegalArgumentException("Invalid column: " + column);
        }

        private void validatePicture(final char[][] picture) {
            if (picture == null)
                throw new IllegalArgumentException("Invalid picture !");
        }
    }

//    @Test
//    public void fillTest() {
//        // given
//        final GraphicalEditor subject = new GraphicalEditor();
//        final int m = 100;
//        final int n = 100;
//
//        final char[][] picture = subject.create(m, n);
//
//        subject.writePicture(picture, "test.pic");
//
//        // when
//        subject.fillRegion(picture, 0, 4, '5');
//
//        // then
//        subject.writePicture(picture, "test.pic");
//    }
//
//    @Test
//    public void testColorPicture() {
//        // given
//        final GraphicalEditor subject = new GraphicalEditor();
//        final int m = 3;
//        final int n = 3;
//
//        final int colorX = 2;
//        final int colorY = 2;
//        final char color = '5';
//
//        final char[][] picture = subject.create(m, n);
//
//        // when
//        subject.color(picture, colorX, colorY, color);
//
//        // then
//        for (int i = 0; i < m; i++)
//            for (int j = 0; j < n; j++)
//                if ((i == colorX) && (j == colorY))
//                    Assert.assertEquals(color, picture[i][j]);
//                else
//                    Assert.assertEquals(GraphicalEditor.WHITE, picture[i][j]);
//    }
//
//    @Test
//    public void testCreatePicture() {
//        // given
//        final GraphicalEditor subject = new GraphicalEditor();
//        final int m = 3;
//        final int n = 2;
//
//        // when
//        final char[][] picture = subject.create(m, n);
//
//        // then
//        for (int i = 0; i < m; i++)
//            for (int j = 0; j < n; j++)
//                Assert.assertEquals(GraphicalEditor.WHITE, picture[i][j]);
//    }
//
//    @Test
//    public void testClearPicture() {
//        // given
//        final GraphicalEditor subject = new GraphicalEditor();
//        final int m = 3;
//        final int n = 2;
//
//        final char[][] picture = subject.create(m, n);
//
//        for (int i = 0; i < m; i++)
//            for (int j = 0; j < n; j++)
//                picture[i][j] = 'A';
//
//        // when
//        final char[][] finalPicture = subject.clear(picture);
//
//        // then
//        for (int i = 0; i < m; i++)
//            for (int j = 0; j < n; j++)
//                Assert.assertEquals(GraphicalEditor.WHITE, finalPicture[i][j]);
//    }
//
//    @Test
//    public void testValidCallToValidatePictureAndCoordinates() {
//        // given
//        final GraphicalEditor subject = new GraphicalEditor();
//        final int m = 3;
//        final int n = 2;
//
//        final char[][] picture = subject.create(m, n);
//
//        // when then should not throw exception
//        subject.validatePictureAndCoordinates(picture, 0, 1);
//    }
//
//    @Test(expected=IllegalArgumentException.class)
//    public void testInvalidPictureCallToValidatePictureAndCoordinates() {
//        // given
//        final GraphicalEditor subject = new GraphicalEditor();
//
//        // when then should not throw exception
//        subject.validatePictureAndCoordinates(null, 0, 1);
//    }
//
//    @Test(expected=IllegalArgumentException.class)
//    public void testInvalidCoordinatesCallToValidatePictureAndCoordinates() {
//        // given
//        final GraphicalEditor subject = new GraphicalEditor();
//        final int m = 3;
//        final int n = 2;
//
//        final char[][] picture = subject.create(m, n);
//
//        // when then should not throw exception
//        subject.validatePictureAndCoordinates(picture, 4, 4);
//    }


    public static void main(final String args[]) throws FileNotFoundException {
        final GraphicalEditor editor = new GraphicalEditor();
        char[][] picture = null;

        final Scanner inputScanner;
        if ((args != null) && (args.length == 1))
            inputScanner =  new Scanner(new FileInputStream(args[0]));
        else
            inputScanner = new Scanner(System.in);

        int column, line, columns, lines, line1, line2, column1, column2;
        char color;

        while (true) {

            final String operation = inputScanner.next();

            switch(operation) {
                case "I":
                    columns = inputScanner.nextInt();
                    lines = inputScanner.nextInt();

                    picture = editor.create(lines, columns);
                    break;

                case "C":
                    picture = editor.clear(picture);
                    break;

                case "L":
                    column = inputScanner.nextInt() -1;
                    line = inputScanner.nextInt() -1;
                    color = inputScanner.next().charAt(0);

                    picture = editor.color(picture, line, column, color);
                    break;

                case "V":
                    column = inputScanner.nextInt() -1;
                    line1 = inputScanner.nextInt() -1;
                    line2 = inputScanner.nextInt() -1;
                    color = inputScanner.next().charAt(0);

                    picture = editor.drawVerticalSegment(picture, column, line1, line2, color);
                    break;

                case "H":
                    column1 = inputScanner.nextInt() -1;
                    column2 = inputScanner.nextInt() -1;
                    line = inputScanner.nextInt() -1;
                    color = inputScanner.next().charAt(0);

                    picture = editor.drawHorizontalSegment(picture, line, column2, column1, color);
                    break;

                case "K":
                    column1 = inputScanner.nextInt() -1;
                    column2 = inputScanner.nextInt() -1;
                    line1 = inputScanner.nextInt() -1;
                    line2 = inputScanner.nextInt() -1;
                    color = inputScanner.next().charAt(0);

                    picture = editor.drawFilledRectangle(picture, line1, column1, line2, column2, color);
                    break;

                case "F":
                    column = inputScanner.nextInt() -1;
                    line = inputScanner.nextInt() -1;
                    color = inputScanner.next().charAt(0);

                    picture = editor.fillRegion(picture, line, column, color);
                    break;

                case "S":
                    final String filename = inputScanner.next();

                    picture = editor.writePicture(picture, filename);
                    break;

                case "X":
                    inputScanner.close();
                    System.exit(0);

                default:
                    inputScanner.nextLine();
                    // throw new IllegalArgumentException("Invalid operation: " + operation);
            }
        }
    }
}
