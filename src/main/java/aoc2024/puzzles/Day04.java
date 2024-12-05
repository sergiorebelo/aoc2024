package aoc2024.puzzles;

import aoc2024.utils.BaseDailyPuzzle;
import aoc2024.utils.DailyPuzzle;
import aoc2024.utils.InputReader;

public class Day04 extends BaseDailyPuzzle {

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day04();
        puzzle.hello();
    }

    @Override
    public String first(String filePath) { return InputReader.getInputAsTextMatrix(filePath).scanForWord("XMAS") + ""; }

    @Override
    public String second(String filePath) { return InputReader.getInputAsTextMatrix(filePath).scanForCrossMas() + ""; }

    public PuzzleSolution getExpectedSolution() {
        return new PuzzleSolution("18", "2464", "9", "1982");
    }
}