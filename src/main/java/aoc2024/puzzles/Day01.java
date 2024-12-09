package aoc2024.puzzles;

import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;
import aoc2024.utils.files.InputReader;

import java.util.List;
import java.util.stream.IntStream;

public class Day01 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "11";
    public static String SOLUTION_TEST_2 = "31";
    public static String SOLUTION_INPUT_1 = "3569916";
    public static String SOLUTION_INPUT_2 = "26407426";

    public static void main(String[] args) {

        DailyPuzzle puzzle = new Day01();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, SOLUTION_INPUT_1, SOLUTION_TEST_2, SOLUTION_INPUT_2));
        puzzle.hello();
    }

    public String first(String filePath) {
        TwoIntColumns input = sortInputLists(getInputFromFile(filePath));
        return Integer.toString(
                IntStream.range(0, input.left().size())
                        .map(i -> Math.abs(input.left().get(i) - input.right().get(i)))
                        .sum());
    }

    public String second(String filePath) {

        TwoIntColumns input = sortInputLists(getInputFromFile(filePath));
        return Long.toString(
                input.left().stream()
                        .map(integer -> findSimilarity(integer, input.right()))
                        .mapToLong(Long::longValue)
                        .sum());

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
        List<List<Integer>> columns = InputReader.getInputAsColumnsWithSeparator(filePath, "\\s+").stream()
                .map(column -> column.stream().map(Integer::parseInt).toList())
                .toList();
        return new TwoIntColumns(columns.get(0), columns.get(1));
    }

    public record TwoIntColumns(List<Integer> left, List<Integer> right) {}
}