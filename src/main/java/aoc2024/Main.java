package aoc2024;

import aoc2024.puzzles.Day02;
import aoc2024.puzzles.Day03;

public class Main {
    public static void main(String[] args) {

        System.out.print("Hello and welcome!");

        String filePathExample = "inputs/examples/day03.txt";
        String filePathInput = "inputs/input_day03.txt";

        System.out.println("### PART ONE ##########");
        System.out.println("Test  : " + Day03.first(filePathExample));
        System.out.println("Result: " + Day03.first(filePathInput));
        System.out.println("### PART TWO ##########");
        System.out.println("Test  : " + Day03.second("inputs/examples/day03_02.txt"));
        System.out.println("Result: " + Day03.second(filePathInput));

        //96666868 is too low
    }
}