package aoc2024.utils;

import aoc2024.models.Calibration;
import aoc2024.models.LabMap;
import aoc2024.models.RulesAndUpdates;
import aoc2024.models.TextSoup;
import aoc2024.models.TwoIntColumns;

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

    static ClassLoader classLoader = InputReader.class.getClassLoader();

    /////////////////////////////////////////////////////////////////////////////////
    // read from File methods
    /////////////////////////////////////////////////////////////////////////////////

    private static List<String> readListOfLinesFromFile(String inputFilePath){
        try(Stream<String> lines = Files.lines(getPath(inputFilePath))) {
            return lines.toList();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readOneLineFromFile(String inputFilePath) {
        try {
            return Files.readString(getPath(inputFilePath));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getPath(String inputFilePath) throws URISyntaxException {
        return Path.of(Objects.requireNonNull(classLoader.getResource(inputFilePath)).toURI());
    }

    /////////////////////////////////////////////////////////////////////////////////
    // getInput methods are used by the Puzzles to get the input in the desired model
    /////////////////////////////////////////////////////////////////////////////////

    public static RulesAndUpdates GetInputAsRulesAndUpdates(String filePath) {
        List<String> lines = InputReader.readListOfLinesFromFile(filePath);
        int emptyLine = lines.indexOf("");
        List<String> rules = lines.subList(0, emptyLine);
        List<String> updates = lines.subList(emptyLine+1, lines.size());
        return new RulesAndUpdates(rules, updates);
    }

    public static TwoIntColumns getInputAsTwoIntColumns(String filePath) {
        List<List<Integer>> lists = readListOfLinesFromFile(filePath).stream()
                .map(line -> line.split("\\s+"))
                .collect(Collectors.teeing(
                        Collectors.mapping(split -> Integer.valueOf(split[0]), Collectors.toList()),
                        Collectors.mapping(split -> Integer.valueOf(split[1]), Collectors.toList()),
                        List::of
                ));
        return new TwoIntColumns(lists.get(0), lists.get(1));
    }

    public static List<List<Integer>> getInputAsIntLines(String filePath) {
        return readListOfLinesFromFile(filePath).stream()
                .map(line -> line.split("\\s+"))
                .map(line -> Arrays.stream(line).map(Integer::valueOf).toList())
                .toList();
    }

    public static String getInputAsOneLine(String filePath) {
        return readOneLineFromFile(filePath);
    }



    public static TextSoup getInputAsTextMatrix(String filePath) {
        return new TextSoup(getInputAsCharMatrix(filePath));
    }

    public static char[][] getInputAsCharMatrix(String filePath) {
        return readListOfLinesFromFile(filePath).stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static LabMap getInputAsLabMap(String filePath) {
        return new LabMap(readListOfLinesFromFile(filePath));
    }

    public static List<Calibration> getInputAsCalibrations(String filePath) {
        return readListOfLinesFromFile(filePath).stream().map(Calibration::new).collect(Collectors.toList());
    }
}