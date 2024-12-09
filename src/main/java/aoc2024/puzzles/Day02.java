package aoc2024.puzzles;

import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;
import aoc2024.utils.files.InputReader;

import java.util.List;
import java.util.stream.IntStream;

public class Day02 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "2";
    public static String SOLUTION_TEST_2 = "4";
    public static String SOLUTION_INPUT_1 = "660";
    public static String SOLUTION_INPUT_2 = "689";

    public static void main(String[] args) {

        DailyPuzzle puzzle = new Day02();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, SOLUTION_INPUT_1, SOLUTION_TEST_2, SOLUTION_INPUT_2));
        puzzle.hello();
    }

    public String first(String filePath)  {
        return Long.toString(InputReader.getInputAsIntLines(filePath).stream().filter(Day02::isReportSafe).count());
    }

    public String second(String filePath)  {
        return Long.toString(InputReader.getInputAsIntLines(filePath).stream()
                .filter(Day02::isReportSafeWithTolerance).count());
    }

     static boolean isReportSafeWithTolerance(List<Integer> report) {
        if (isReportSafe(report)) return true;
        for (int i=0; i<report.size(); i++) {
            if (isReportSafe(reportWithoutDeffect(report, i))) return true;
        }
        return false;
    }

     static List<Integer> reportWithoutDeffect(List<Integer> report, int deffectIndex) {
        return IntStream.range(0, report.size())
                .filter(i -> i != deffectIndex)
                .mapToObj(report::get)
                .toList();
    }

     static boolean isReportSafe( List<Integer> report) {
        return (report.get(1) > report.get(0) ? isReportSafeAscending(report): isReportSafeDescending(report));
    }

     static boolean isReportSafeDescending(List<Integer> report) {
        return IntStream.range(1, report.size())
                .allMatch(i-> report.get(i-1) - report.get(i) > 0  && report.get(i-1) - report.get(i) < 4);
    }

     static boolean isReportSafeAscending(List<Integer> report) {
        return IntStream.range(1, report.size())
                .allMatch(i-> report.get(i) - report.get(i-1) > 0 &&  report.get(i) - report.get(i-1) < 4);
    }
}