package aoc2024.puzzles;

import aoc2024.utils.matrix.CharMatrix;
import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;
import aoc2024.utils.files.InputReader;

public class Day04 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "18";
    public static String SOLUTION_TEST_2 = "9";
    public static String SOLUTION_INPUT_1 = "2464";
    public static String SOLUTION_INPUT_2 = "1982";

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day04();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, SOLUTION_INPUT_1, SOLUTION_TEST_2, SOLUTION_INPUT_2));
        puzzle.hello();
    }

    @Override
    public String first(String filePath) { return new TextSoup(InputReader.getInputAsCharMatrix(filePath)).scanForWord("XMAS") + ""; }

    @Override
    public String second(String filePath) { return new TextSoup(InputReader.getInputAsCharMatrix(filePath)).scanForCrossMas() + ""; }

    //

    private static class TextSoup {
        private final CharMatrix board;
        public TextSoup(char[][] board) {
            this.board = new CharMatrix(board);
        }

        public int scanForWord(String word) {
            String[] directions = new String[] {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
            int wordsFound = 0;
            char firstChar = word.charAt(0);
            for (int x = 0; x < board.getWidth() ; x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    if (board.get(x,y) == firstChar) {
                        for (String direction:directions) {
                            if (checkWord(word, x, y, direction)) wordsFound++;
                        }
                    }
                }
            }
            return wordsFound;
        }

        private boolean checkWord(String word, int x, int y, String direction) {
            for (int i = 0; i < word.length(); i++) {
                if (board.isOutOfBounds(x,y)) return false;
                if (word.charAt(i) != board.get(x,y)) return false;
                x += moveHorizontally(direction);
                y += moveVertically(direction);
            }
            return true;
        }

        private int moveHorizontally(String direction) {
            return (direction.endsWith("E")? 1 : (direction.endsWith("W") ? -1: 0));
        }

        private int moveVertically(String direction) {
            return (direction.startsWith("S")? 1 : (direction.startsWith("N") ? -1: 0));
        }

        /////////////////////////////////////////////////////////////// PART 2

        public int scanForCrossMas() {
            int wordsFound = 0;
            for (int x = 1; x < board.getWidth()-1; x++) {
                for (int y = 1; y < board.getHeight()-1; y++) {
                    if (board.get(x,y) == 'A' && checkCross(x,y)) wordsFound++;
                }
            }
            return wordsFound;
        }

        private boolean isMs(char[] word) {
            return (word[0]=='M' && word[1]=='S') || (word[1]=='M' && word[0]=='S') ;
        }

        private boolean checkCross(int x, int y) {
            char[] nesw = new char[] { board.get(x-1, y+1), board.get(x+1,y-1) };
            char[] nwse = new char[] { board.get(x-1,y-1), board.get(x+1,y+1) };
            return  (isMs(nesw) && isMs(nwse));
        }

        private boolean isCross(int x, int y, int dx1, int dy1, int dx2, int dy2) {
            char c1 = board.get(x+dx1,y+dy1);
            char c2 = board.get(x+dx2,y+dy2);
            return (c1 == 'M' && c2 == 'S') || (c1 == 'S' && c2 == 'M');
        }
    }
}