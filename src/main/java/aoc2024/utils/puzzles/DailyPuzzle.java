package aoc2024.utils.puzzles;

public interface DailyPuzzle {

    public record PuzzleSolution(String firstTest, String firstResult, String secondTest, String secondResult) { }

    String first(String filePath);

    String second(String filePath);

    PuzzleSolution solve();

    PuzzleSolution getExpectedSolution();

    void setExpectedSolution(PuzzleSolution solution);

    String getDay();

    void hello();
}
