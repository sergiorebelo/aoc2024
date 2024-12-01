package aoc2024.puzzles;

import aoc2024.utils.InputReader.TwoIntListsInput;

import java.util.List;
import java.util.stream.IntStream;

import static aoc2024.utils.InputReader.getInputFromFile;

public class DayOne {

    public static int first(String filePath)  {

        TwoIntListsInput input = sortInputLists(getInputFromFile(filePath));

        return  IntStream.range(0, input.list1().size())
                .map(i -> Math.abs(input.list1().get(i) - input.list2().get(i)))
                .sum();
    }

    public static long second(String filePath)  {

        TwoIntListsInput input = sortInputLists(getInputFromFile(filePath));

        return IntStream.range(0,input.list1().size())
                .mapToObj(i -> findSimilarity(input.list1().get(i), input.list2()))
                .mapToLong(Long::longValue).sum();
    }

    private static long findSimilarity(int x, List<Integer> sortedList) {
        long count = sortedList.stream()
                .takeWhile(y -> y <= x)
                .filter(y -> y == x)
                .count();
        return count*x;
    }

    private static TwoIntListsInput sortInputLists(TwoIntListsInput input) {

        return new TwoIntListsInput(input.list1().stream().sorted().toList(), input.list2().stream().sorted().toList());
    }
}