package aoc2024.puzzles;

import aoc2024.utils.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    public static int first(String filePath)  {

        Matcher matcher = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)").matcher(getInputFromFile(filePath));
        int sum = 0;
        while (matcher.find()) {
            sum += getMultiplication(matcher);
        }
        return sum;
    }

    public static long second(String filePath)  {

        String input = getInputFromFile(filePath);
        Matcher matcher = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)").matcher(input);
        Actions actions = getActions(input);
        int sum = 0;

        while (matcher.find()) {
            int index = matcher.start();
            if (actions.mostRecentActionBefore(index)) sum += getMultiplication(matcher);
        }
        return sum;
    }

    private static int getMultiplication(Matcher matcher) {

        String mul = matcher.group();
        String[] numbers = mul.substring(4, mul.length() - 1).split(",");
        return Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
    }

    static String getInputFromFile(String filePath) {
        return InputReader.readOneLine(filePath);
    }

    public record Actions(ArrayList<Integer> actionsDo, ArrayList<Integer> actionsDont) {

        private boolean mostRecentActionBefore(int index) {
            return this.actionsDo.stream().filter(i-> i <index).max(Integer::compareTo).orElse(0) >=
                    this.actionsDont.stream().filter(i -> i < index).max(Integer::compareTo).orElse(0);
        }
    }

    private static Actions getActions(String input) {

        Matcher matcherDo = Pattern.compile("do\\(\\)").matcher(input);
        ArrayList<Integer> actionsDo = new ArrayList<Integer>();
        while(matcherDo.find()) { actionsDo.add(matcherDo.start()); }

        Matcher matcherDont = Pattern.compile("don\\'t\\(\\)").matcher(input);
        ArrayList<Integer> actionsDont = new ArrayList<Integer>();
        while(matcherDont.find()) { actionsDont.add(matcherDont.start()); }

        return new Actions(actionsDo, actionsDont);
    }
}