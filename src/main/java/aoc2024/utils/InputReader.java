package aoc2024.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    private static List<List<Integer>> readTwoIntListsInput(String inputFilePath) throws IOException, URISyntaxException {

        ClassLoader classLoader = InputReader.class.getClassLoader();
        Path filePath = Path.of(classLoader.getResource(inputFilePath).toURI());

        List<List<Integer>> lists = Files.lines(filePath)
                .map(line -> line.split("\\s+")) // Split on whitespace
                .collect(Collectors.teeing(
                        Collectors.mapping(split -> Integer.valueOf(split[0]), Collectors.toList()),
                        Collectors.mapping(split -> Integer.valueOf(split[1]), Collectors.toList()),
                        List::of
                ));

        return lists;
    }

    public static TwoIntListsInput getInputFromFile(String filePath) {

        List<List<Integer>> lists = null;
        try {
            lists = InputReader.readTwoIntListsInput(filePath);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        TwoIntListsInput input = new TwoIntListsInput(lists.get(0), lists.get(1));
        return input;
    }

    public record TwoIntListsInput(List<Integer> list1, List<Integer> list2) {}
}
