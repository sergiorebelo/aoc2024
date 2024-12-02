package aoc2024.exercises;

import aoc2024.puzzles.DayOne;
import aoc2024.puzzles.DayTwo;
import org.junit.jupiter.api.Test;

class ExercisesTest {

    @Test
    void testDayOne() {
        String exampleFilePath = "inputs/examples/day01.txt";
        assert(DayOne.first(exampleFilePath)==11);
        assert(DayOne.second(exampleFilePath)==31);

        String inputFilePath = "inputs/input_day01.txt";
        assert(DayOne.first(inputFilePath)==3569916);
        assert(DayOne.second(inputFilePath)==26407426);
    }

    @Test
    void testDayTwo() {
        String exampleFilePath = "inputs/examples/day02.txt";
        assert(DayTwo.first(exampleFilePath)==2);
        assert(DayTwo.second(exampleFilePath)==4);

        String inputFilePath = "inputs/input_day02.txt";
        assert(DayTwo.first(inputFilePath)==660);
        assert(DayTwo.second(inputFilePath)==689);
    }
}