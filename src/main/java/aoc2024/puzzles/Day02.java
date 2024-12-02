package aoc2024.puzzles;

import aoc2024.utils.InputReader;

import java.util.List;
import java.util.stream.IntStream;

public class Day02 {

    public static long first(String filePath)  {

        List<List<Integer>> input = getInputFromFile(filePath);
        return input.stream().filter(Day02::isReportSafe).count();
    }

    public static long second(String filePath)  {

        List<List<Integer>> input = getInputFromFile(filePath);
        return input.stream().filter(Day02::isReportSafeWithTolerance).count();

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


     static List<List<Integer>> getInputFromFile(String filePath) {
        return InputReader.readLinesOfIntsFromFile(filePath);
    }
}