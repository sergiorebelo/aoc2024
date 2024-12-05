package aoc2024.puzzles;

import aoc2024.models.TwoIntColumns;
import aoc2024.utils.BaseDailyPuzzle;
import aoc2024.utils.DailyPuzzle;
import aoc2024.utils.InputReader;

import java.util.List;
import java.util.stream.IntStream;

public class Day01 extends BaseDailyPuzzle {

    public static void main(String[] args) {

        DailyPuzzle puzzle = new Day01();
        puzzle.hello();
    }

    public String first(String filePath) { return Integer.toString(solvePartOne(filePath)); }
    public String second(String filePath) { return Long.toString(solvePartTwo(filePath)); }

    public PuzzleSolution getExpectedSolution() {
        return new PuzzleSolution("11", "3569916", "31", "26407426");
    }

    public static int solvePartOne(String filePath)  {
        TwoIntColumns input = sortInputLists(getInputFromFile(filePath));
        return  IntStream.range(0, input.left().size())
                .map(i -> Math.abs(input.left().get(i) - input.right().get(i)))
                .sum();
    }

    public  long solvePartTwo(String filePath)  {
        TwoIntColumns input = sortInputLists(getInputFromFile(filePath));
        return input.left().stream()
                .map(integer -> findSimilarity(integer, input.right()))
                .mapToLong(Long::longValue)
                .sum();
    }

    private static long findSimilarity(int x, List<Integer> sortedList) {
        return x * sortedList.stream()
                .takeWhile(y -> y <= x)
                .filter(y -> y == x)
                .count();
    }

    private static TwoIntColumns sortInputLists(TwoIntColumns input) {
        return new TwoIntColumns(input.left().stream().sorted().toList(), input.right().stream().sorted().toList());
    }

    private static TwoIntColumns getInputFromFile(String filePath) {
        return InputReader.getInputAsTwoIntColumns(filePath);
    }
}