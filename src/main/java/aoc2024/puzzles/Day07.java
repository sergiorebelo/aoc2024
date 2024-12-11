package aoc2024.puzzles;


import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;
import aoc2024.utils.files.InputReader;
import aoc2024.utils.math.MathUtils.LongsOperation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day07 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "3749";
    public static String SOLUTION_TEST_2 = "11387";
    public static String SOLUTION_INPUT_1 = "4122618559853";
    public static String SOLUTION_INPUT_2 = "227615740238334";

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day07();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, 0, SOLUTION_TEST_2, 0));
        puzzle.hello();
    }

    public String first(String filePath) {
        List<Calibration> input = getInputAsCalibrations(filePath);
        return getTotalCalibrationResult(input, List.of(LongsOperation.ADD, LongsOperation.MULTIPLY));
    }

    public String second(String filePath) {
        List<Calibration> input = getInputAsCalibrations(filePath);
        return getTotalCalibrationResult(input, List.of(LongsOperation.ADD, LongsOperation.MULTIPLY, LongsOperation.CONCATENATE));
    }

    private static String getTotalCalibrationResult(List<Calibration> input, List<LongsOperation> ops) {
        long result = 0;
        for(Calibration calibration: input) {
            calibration.setSupportedOperations(ops);
            if (calibration.isValid(0,0))
                result+=calibration.testValue();
        }
        return result + "";
    }

    public  List<Calibration> getInputAsCalibrations(String filePath) {
        return InputReader.getInputAsLines(filePath).stream().map(Calibration::new).collect(Collectors.toList());
    }


    public static class Calibration {
        private final long testValue;
        private final long[] numbers;
        List<LongsOperation> ops;

        public Calibration( String line) {
            String[] parts = line.split(":");
            this.testValue = Long.parseLong(parts[0].trim());
            this.numbers = Arrays.stream(parts[1].trim().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
        }

        public long testValue() { return testValue; }

        public void setSupportedOperations(List<LongsOperation> ops) { this.ops=ops; }

        public boolean isValid(long soFar, int pos) {
            return pos == numbers.length-1
                    ? ops.stream().anyMatch(op -> op.apply(soFar, numbers[pos]) == testValue)
                    : ops.stream().anyMatch(op -> isValid(op.apply(soFar, numbers[pos]), pos + 1));
        }
    }
}