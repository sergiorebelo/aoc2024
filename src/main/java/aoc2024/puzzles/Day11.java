package aoc2024.puzzles;

import aoc2024.utils.files.InputReader;
import aoc2024.utils.math.MathUtils;
import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day11 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "55312";
    public static String SOLUTION_TEST_2 = "65601038650482";
    public static String SOLUTION_INPUT_1 = "203953";
    public static String SOLUTION_INPUT_2 = "242090118578155";

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day11();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, 0, SOLUTION_TEST_2, 0));
        puzzle.hello();
    }
    public String first(String filePath) { return "" + (countStones(getInput(filePath), 25)); }

    public String second(String filePath) { return "" + (countStones(getInput(filePath), 75)); }

    public long countStones (List<Long> input, int numberOfBlinks) {
        Map<Long, Long> stoneCounts = new HashMap<>();
        for(Long l : input)  stoneCounts.merge(l, 1L, Long::sum);
        for (int i=0; i<numberOfBlinks; i++) {
            Map<Long, Long> nextStoneCounts = new HashMap<>();
            for (Map.Entry<Long, Long> entry: stoneCounts.entrySet() )
                blinkStone(entry.getKey(), entry.getValue(), nextStoneCounts);
            stoneCounts = nextStoneCounts;
        }
        return stoneCounts.values().stream().mapToLong(Long::longValue).sum();
    }

    private void blinkStone(long stone, long count, Map<Long, Long> nextStoneCounts) {
        if (stone == 0) nextStoneCounts.merge(1L, count, Long::sum);
        else if (evenNumberOfDigits(stone)) {
            nextStoneCounts.merge(extractLeftHalf(stone), count, Long::sum);
            nextStoneCounts.merge(extractRightHalf(stone), count, Long::sum);
        }
        else nextStoneCounts.merge(stone * 2024, count, Long::sum);
    }

    private long extractRightHalf(long stone) {
        String stoneLabel = String.valueOf(stone);
        return Long.parseLong(stoneLabel.substring(stoneLabel.length()/2));
    }

    private long extractLeftHalf(long stone) {
        String stoneLabel = String.valueOf(stone);
        return Long.parseLong(stoneLabel.substring(0,stoneLabel.length()/2));
    }

    private List<Long> getInput(String filePath) {
        return Arrays.stream(InputReader.getInputAsOneLine(filePath).split("\\s+")).map(Long::parseLong).toList();
    }

    private boolean evenNumberOfDigits(long stone) { return MathUtils.numberOfDigits(stone) %2 == 0; }
}
