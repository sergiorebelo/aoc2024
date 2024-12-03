package aoc2024.puzzles;

import org.junit.jupiter.api.Test;

class AllDaysTest {

    @Test
    void testDay01() {
        String exampleFilePath = "inputs/examples/day01.txt";
        assert(Day01.first(exampleFilePath)==11);
        assert(Day01.second(exampleFilePath)==31);

        String inputFilePath = "inputs/input_day01.txt";
        assert(Day01.first(inputFilePath)==3569916);
        assert(Day01.second(inputFilePath)==26407426);
    }

    @Test
    void testDay02() {
        String exampleFilePath = "inputs/examples/day02.txt";
        assert(Day02.first(exampleFilePath)==2);
        assert(Day02.second(exampleFilePath)==4);

        String inputFilePath = "inputs/input_day02.txt";
        assert(Day02.first(inputFilePath)==660);
        assert(Day02.second(inputFilePath)==689);
    }

    @Test
    void testDay03() {
        String exampleFilePath = "inputs/examples/day03.txt";
        assert(Day03.first(exampleFilePath)==161);
        exampleFilePath = "inputs/examples/day03_02.txt";
        assert(Day03.second(exampleFilePath)==48);

        String inputFilePath = "inputs/input_day03.txt";
        assert(Day03.first(inputFilePath)==192767529);
        assert(Day03.second(inputFilePath)==104083373);
    }

    @Test
    void testDay04() {
        String exampleFilePath = "inputs/examples/day04.txt";
        assert(Day04.first(exampleFilePath)==0);
        assert(Day04.second(exampleFilePath)==0);

        String inputFilePath = "inputs/input_day04.txt";
        assert(Day04.first(inputFilePath)==0);
        assert(Day04.second(inputFilePath)==0);
    }
}