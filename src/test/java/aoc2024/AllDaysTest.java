package aoc2024;

import aoc2024.puzzles.Day01;
import aoc2024.puzzles.Day02;
import aoc2024.puzzles.Day03;
import aoc2024.puzzles.Day04;
import aoc2024.puzzles.Day05;
import aoc2024.utils.DailyPuzzle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AllDaysTest {

    @Test
    void tesAllDays() {

        List<DailyPuzzle> daysToTest = new ArrayList<DailyPuzzle>();

        daysToTest.add(new Day01());
        daysToTest.add(new Day02());
        daysToTest.add(new Day03());
        daysToTest.add(new Day04());
        daysToTest.add(new Day05());
        //daysToTest.add(new Day06());
        //daysToTest.add(new Day07());
        //daysToTest.add(new Day08());
        //daysToTest.add(new Day09());
        //daysToTest.add(new Day10());
        //daysToTest.add(new Day11());
        //daysToTest.add(new Day12());
        //daysToTest.add(new Day13());
        //daysToTest.add(new Day14());
        //daysToTest.add(new Day15());
        //daysToTest.add(new Day16());
        //daysToTest.add(new Day17());
        //daysToTest.add(new Day18());
        //daysToTest.add(new Day19());
        //daysToTest.add(new Day20());
        //daysToTest.add(new Day21());
        //daysToTest.add(new Day22());
        //daysToTest.add(new Day23());
        //daysToTest.add(new Day24());

        for (DailyPuzzle puzzle : daysToTest) {
            DailyPuzzle.PuzzleSolution solution = puzzle.solve();
            DailyPuzzle.PuzzleSolution expected = puzzle.getExpectedSolution();

            assert (solution.firstTest().equals(expected.firstTest()));
            assert (solution.firstResult().equals(expected.firstResult()));
            assert (solution.secondTest().equals(expected.secondTest()));
            assert (solution.secondResult().equals(expected.secondResult()));

            System.out.println("Day " + puzzle.getDay() + " OK!");
        }
    }
}