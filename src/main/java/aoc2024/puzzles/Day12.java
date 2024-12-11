package aoc2024.puzzles;

import aoc2024.utils.files.InputReader;
import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;


public class Day12 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "0";
    public static String SOLUTION_TEST_2 = "0";
    public static String SOLUTION_INPUT_1 = "0";
    public static String SOLUTION_INPUT_2 = "0";

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day12();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, 0, SOLUTION_TEST_2, 0));
        puzzle.hello();
    }
    public String first(String filePath) { return ""; }

    public String second(String filePath) {
        return null;
    }


}
