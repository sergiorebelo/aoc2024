package aoc2024.puzzles;

import aoc2024.models.LabMap;
import aoc2024.models.LabMap.LoopException;
import aoc2024.utils.BaseDailyPuzzle;
import aoc2024.utils.InputReader;

public class Day06 extends BaseDailyPuzzle {

    public static void main(String[] args) {
        Day06 puzzle = new Day06();
        puzzle.hello();
    }
    public String first(String filePath) {
        LabMap map = InputReader.getInputAsLabMap(filePath);
        try { map.moveGuardUntilEnd(); } catch (LoopException e) { }
        return map.countVisited() + "";
    }

    public String second(String filePath) {
        return InputReader.getInputAsLabMap(filePath).findObstructions() + "";
    }

    public PuzzleSolution getExpectedSolution() {
        return new PuzzleSolution("41", "5531", "6", "2165");
    }
}
