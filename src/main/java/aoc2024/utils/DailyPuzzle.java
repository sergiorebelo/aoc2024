package aoc2024.utils;

public interface DailyPuzzle {

    public record PuzzleSolution(String firstTest, String firstResult, String secondTest, String secondResult) { }

    int TEST_FIRST_VALUE = 0;
    int TEST_SECOND_VALUE = 0;
    int RESULT_FIRST_VALUE = 0;
    int RESULT_SECOND_VALUE = 0;

    String first(String filePath);

    String second(String filePath);

    PuzzleSolution solve();

    PuzzleSolution getExpectedSolution();

    String getDay();

    void hello();
}
