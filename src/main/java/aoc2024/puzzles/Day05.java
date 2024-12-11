package aoc2024.puzzles;

import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;
import aoc2024.utils.files.InputReader;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;


public class Day05 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "143";
    public static String SOLUTION_TEST_2 = "123";
    public static String SOLUTION_INPUT_1 = "5964";
    public static String SOLUTION_INPUT_2 = "4719";

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day05();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, 0, SOLUTION_TEST_2, 0));
        puzzle.hello();
    }

    public String first(String filePath) {
        RulesAndUpdates input = getInputAsRulesAndUpdates(filePath);
        return input.getValidUpdates().stream()
                .map(RulesAndUpdates.Update::getMiddlePage)
                .mapToInt(Integer::intValue)
                .sum() + "";
    }

    public String second(String filePath) {
        RulesAndUpdates input = getInputAsRulesAndUpdates(filePath);
        return input.getInvalidUpdates().stream()
                .map(input::fix)
                .map(RulesAndUpdates.Update::getMiddlePage)
                .mapToInt(Integer::intValue)
                .sum() + "";
    }

    public RulesAndUpdates getInputAsRulesAndUpdates(String filePath) {
        List<String> lines = InputReader.getInputAsLines(filePath);
        int emptyLine = lines.indexOf("");
        List<String> rules = lines.subList(0, emptyLine);
        List<String> updates = lines.subList(emptyLine+1, lines.size());
        return new RulesAndUpdates(rules, updates);
    }

    @Getter
    @ToString
    public class RulesAndUpdates {
        private final List<Rule> rules;
        private final List<Update> updates;

        public RulesAndUpdates(List<String> rules, List<String> updates) {
            this.rules = rules.stream()
                    .map(Rule::new)
                    .collect(Collectors.toList());
            this.updates = updates.stream()
                    .map(Update::new)
                    .collect(Collectors.toList());
        }

        @ToString
        public class Rule {
            private final int lower;
            private final int higher;

            public Rule(String rule) {
                int[] pages = Arrays.stream(rule.split("\\|")).mapToInt(Integer::parseInt).toArray();
                this.lower = pages[0];
                this.higher = pages[1];
            }
        }

        @ToString
        public class Update {
            private final int[] pages;

            public Update(String update) {
                this.pages = Arrays.stream(update.split(",")).mapToInt(Integer::parseInt).toArray();
            }

            public int getMiddlePage() {
                return pages[pages.length / 2];
            }

            public boolean breaks(Rule rule) {
                int indexOfLower = -1;
                int indexOfHigher = -1;
                for (int i = 0; i < pages.length; i++) {
                    if (pages[i] == rule.lower) indexOfLower = i;
                    else if (pages[i] == rule.higher) indexOfHigher = i;
                    if (indexOfLower != -1 && indexOfHigher != -1) return indexOfLower > indexOfHigher;
                }
                return false;
            }

            /////////////

            private void fix(Rule r) {
                int higherIndex = -1;
                int lowerIndex = -1;
                for (int i = 0; i < pages.length; i++) {
                    if (pages[i] == r.higher) higherIndex = i;
                    else if (pages[i] == r.lower) lowerIndex = i;
                }
                moveElementRight(pages, higherIndex, lowerIndex);
            }
        }

        /////////////////////////////////////////////////

        public boolean isValid(Update update) {
            return rules.stream().filter(update::breaks).count() == 0;
        }

        public List<Update> getValidUpdates() {
            return updates.stream().filter(this::isValid).collect(Collectors.toList());
        }

        //////////////////////////////////////////////////

        public List<Update> getInvalidUpdates() {
            return updates.stream().filter(not(this::isValid)).collect(Collectors.toList());
        }

        public Update fix(Update update) {
            for (Rule r : rules) {
                if (update.breaks(r)) update.fix(r);
            }
            return isValid(update) ? update : fix(update);
        }

        public static void moveElementRight(int[] array, int from, int to) {
            int value = array[from];
            System.arraycopy(array, from + 1, array, from, to - from);
            array[to] = value;
        }
    }
}