package aoc2024;

import aoc2024.puzzles.Day02;

public class Main {
    public static void main(String[] args) {


        System.out.print("Hello and welcome!");

        String filePathExample = "inputs/examples/day02.txt";
        String filePathInput = "inputs/input_day02.txt";

        System.out.println("### PART ONE ##########");
        System.out.println("Test  : " + Day02.first(filePathExample));
        System.out.println("Result: " + Day02.first(filePathInput));
        System.out.println("### PART TWO ##########");
        System.out.println("Test  : " + Day02.second(filePathExample));
        System.out.println("Result: " + Day02.second(filePathInput));
    }
}