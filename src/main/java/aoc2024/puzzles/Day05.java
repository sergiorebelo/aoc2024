package aoc2024.puzzles;

import aoc2024.utils.BaseDailyPuzzle;
import aoc2024.utils.DailyPuzzle;
import aoc2024.utils.InputReader;
import aoc2024.models.RulesAndUpdates;


public class Day05 extends BaseDailyPuzzle {


    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day05();
        puzzle.hello();
    }
    public String first(String filePath) {
        RulesAndUpdates input = InputReader.GetInputAsRulesAndUpdates(filePath);
        return input.getValidUpdates().stream()
                .map(RulesAndUpdates.Update::getMiddlePage)
                .mapToInt(Integer::intValue)
                .sum() + "";
    }

    public String second(String filePath) {
        RulesAndUpdates input = InputReader.GetInputAsRulesAndUpdates(filePath);
        return input.getInvalidUpdates().stream()
                .map(input::fix)
                .map(RulesAndUpdates.Update::getMiddlePage)
                .mapToInt(Integer::intValue)
                .sum() + "";
    }

    public PuzzleSolution getExpectedSolution() {
        return new PuzzleSolution("143", "5964", "123", "4719");
    }
}