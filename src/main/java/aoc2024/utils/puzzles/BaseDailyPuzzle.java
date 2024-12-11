package aoc2024.utils.puzzles;

import java.time.LocalDateTime;

public abstract class BaseDailyPuzzle implements DailyPuzzle {

    public PuzzleSolution solve(String filePath1,  String filePath2 ) {

        long now = System.currentTimeMillis();
        String result1 = first(filePath1);
        long firstTime = System.currentTimeMillis()-now;
        now = System.currentTimeMillis();
        String result2 = second(filePath2);
        long secondTime  = System.currentTimeMillis()-now;
        
        return new PuzzleSolution(result1, firstTime, result2,  secondTime );
    }

    @Override
    public PuzzleSolution solve(String filePath) { return solve(filePath, filePath); }

    PuzzleSolution solution;

    public PuzzleSolution getExpectedSolution() {
        return solution;
    }

    public void setExpectedSolution(PuzzleSolution solution) {this.solution = solution;}

    private String inputFilePath() {
        return "aoc2024/inputs/input_day" + getDay() + ".txt";
    }

    protected String testFilePath() {
        return "aoc2024/examples/day" + getDay() + ".txt";
    }

    @Override
    public String getDay() {
        String className = this.getClass().getSimpleName();
        return className.substring(className.length()-2);
    }

    @Override
    public void hello() {

        String day = getDay();
        PuzzleSolution solution  = solve(testFilePath());

        System.out.println("\nHello and welcome to day " + day + "!\n");
        System.out.println("\t## TEST ##");
        System.out.println("\tPart 1 : " + solution.result1() + " " + (solution.result1().equals(getExpectedSolution().result1())? "OK" : "NOK") + " (" + solution.time1() + " milliseconds)");
        System.out.println("\tPart 2 : " + solution.result2() + " " + (solution.result2().equals(getExpectedSolution().result2())? "OK" : "NOK") + " (" + solution.time2() + " milliseconds)");

        solution  = solve(inputFilePath());

        System.out.println("\t## My Input ##");
        System.out.println("\tPart 1 : " + solution.result1() + " (" + solution.time1() + " milliseconds)");
        System.out.println("\tPart 2 : " + solution.result2() + " (" + solution.time2() + " milliseconds)");
    }
}
