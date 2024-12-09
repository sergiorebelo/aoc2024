package aoc2024.utils.files;

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

    public static String getInputAsOneLine(String filePath) {
        return readOneLineFromFile(filePath);
    }

    public static List<String> getInputAsLines(String filePath) {
        return readListOfLinesFromFile(filePath);
    }

    public static List<List<Integer>> getInputAsIntLines(String filePath) {
        return readListOfLinesFromFile(filePath).stream()
                .map(line -> line.split("\\s+"))
                .map(line -> Arrays.stream(line).map(Integer::valueOf).toList())
                .toList();
    }

    public static char[][] getInputAsCharMatrix(String filePath) {
        return readListOfLinesFromFile(filePath).stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static List<List<String>> getInputAsColumnsWithSeparator(String filePath, String separator) {
        return readListOfLinesFromFile(filePath).stream()
                .map(line -> line.split(separator))
                .collect(Collectors.teeing(
                        Collectors.mapping(split -> split[0], Collectors.toList()),
                        Collectors.mapping(split -> split[1], Collectors.toList()),
                        List::of));
    }
}