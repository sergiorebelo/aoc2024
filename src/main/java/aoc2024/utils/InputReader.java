package aoc2024.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {
    public static RulesAndUpdates GetInputAsRulesAndUpdates(String filePath) {
        List<String> lines = InputReader.readListOfLinesFromFile(filePath);
        int emptyLine = lines.indexOf("");
        List<String> rules = lines.subList(0, emptyLine);
        List<String> updates = lines.subList(emptyLine+1, lines.size());
        return new RulesAndUpdates(rules, updates);
    }

    // input data types

    public record TwoIntColumns(List<Integer> left, List<Integer> right) {}

    // getInput methods

    public static TwoIntColumns getInputAsTwoIntColumns(String filePath) {
        List<List<Integer>> lists = InputReader.convertInputToTwoIntColumns(filePath);
        TwoIntColumns input = new TwoIntColumns(lists.get(0), lists.get(1));
        return input;
    }

    public static List<List<Integer>> getInputAsIntLines(String filePath) {
        return convertInputToIntLines(filePath);
    }

    public static String getInputAsOneLine(String filePath) {
        return readOneLineFromFile(filePath);
    }

    public static TextSoup getInputAsTextMatrix(String filePath) {
        return new TextSoup(readListOfLinesFromFile(filePath).stream()
                .map(String::toCharArray)
                .toArray(char[][]::new));
    }

    // conversion methods

    private static List<List<Integer>> convertInputToTwoIntColumns(String inputFilePath)  {
        return readListOfLinesFromFile(inputFilePath).stream()
                .map(line -> line.split("\\s+"))
                .collect(Collectors.teeing(
                        Collectors.mapping(split -> Integer.valueOf(split[0]), Collectors.toList()),
                        Collectors.mapping(split -> Integer.valueOf(split[1]), Collectors.toList()),
                        List::of
                ));
    }

    private static List<List<Integer>> convertInputToIntLines(String inputFilePath) {
        return readListOfLinesFromFile(inputFilePath).stream()
                .map(line -> line.split("\\s+"))
                .map(line -> Arrays.stream(line).map(Integer::valueOf).toList())
                .toList();
    }

    // read from file methods

    private static List<String> readListOfLinesFromFile(String inputFilePath){

        ClassLoader classLoader = InputReader.class.getClassLoader();

        Path filePath = null;
        try {
            filePath = Path.of(Objects.requireNonNull(classLoader.getResource(inputFilePath)).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try(Stream<String> lines = Files.lines(filePath)) {
            return lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readOneLineFromFile(String inputFilePath) {
        try {
            ClassLoader classLoader = InputReader.class.getClassLoader();
            Path filePath = Path.of(classLoader.getResource(inputFilePath).toURI());

            return Files.readString(filePath);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
