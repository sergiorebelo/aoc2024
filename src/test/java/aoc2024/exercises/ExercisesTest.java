package aoc2024.exercises;

import aoc2024.puzzles.DayOne;
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
}