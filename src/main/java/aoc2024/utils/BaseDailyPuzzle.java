package aoc2024.utils;

public abstract class BaseDailyPuzzle implements DailyPuzzle {



    @Override
    public PuzzleSolution solve() {
        return new PuzzleSolution(
                first(testFilePath()),
                first(inputFilePath()),
                second(testFilePath()),
                second(inputFilePath()));
    }

    private String inputFilePath() {
        return "inputs/input_day" + getDay() + ".txt";
    }

    private String testFilePath() {
        return "inputs/examples/day" + getDay() + ".txt";
    }

    @Override
    public String getDay() {
        String className = this.getClass().getSimpleName();
        return className.substring(className.length()-2);
    }

    @Override
    public void hello() {

        String day = getDay();
        PuzzleSolution solution = solve();
        PuzzleSolution expected = getExpectedSolution();

        System.out.println("\n\nHello and welcome to day " + day + "!\n\n");
        System.out.println("## PART ONE ##");
        System.out.println("Test  : " + solution.firstTest() + " (" + expected.firstTest() + ")");
        System.out.println("Result: " + solution.firstResult() + " (" + expected.firstResult() + ")");
        System.out.println("\n### PART TWO ##");
        System.out.println("Test  : " + solution.secondTest() + " (" + expected.secondTest() + ")");
        System.out.println("Result: " + solution.secondResult() + " (" + expected.secondResult() + ")");
    }
}
