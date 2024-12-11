package aoc2024.utils.puzzles;

public interface DailyPuzzle {

    public record PuzzleSolution(String result1, long time1, String result2, long time2) { }

    String first(String filePath);

    String second(String filePath);

    PuzzleSolution solve(String filepath);

    PuzzleSolution getExpectedSolution();

    void setExpectedSolution(PuzzleSolution solution);

    String getDay();

    void hello();
}
