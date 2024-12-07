package aoc2024.puzzles;

import aoc2024.models.Calibration;
import aoc2024.utils.BaseDailyPuzzle;
import aoc2024.utils.DailyPuzzle;
import aoc2024.utils.InputReader;
import aoc2024.utils.MathUtils.LongsOperation;

import java.util.List;

public class Day07 extends BaseDailyPuzzle {

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day07();
        puzzle.hello();
    }
    public String first(String filePath) {
        List<Calibration> input = InputReader.getInputAsCalibrations(filePath);
        return getTotalCalibrationResult(input, List.of(LongsOperation.ADD, LongsOperation.MULTIPLY));
    }

    public String second(String filePath) {
        List<Calibration> input = InputReader.getInputAsCalibrations(filePath);
        return getTotalCalibrationResult(input, List.of(LongsOperation.ADD, LongsOperation.MULTIPLY, LongsOperation.CONCATENATE));
    }

    public PuzzleSolution getExpectedSolution() {
        return new PuzzleSolution("3749", "4122618559853", "11387", "227615740238334");
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
}