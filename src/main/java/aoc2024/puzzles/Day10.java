package aoc2024.puzzles;

import aoc2024.utils.files.InputReader;
import aoc2024.utils.matrix.IntMatrix;
import aoc2024.utils.matrix.MapUtils;
import aoc2024.utils.matrix.Position;
import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Day10 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "36";
    public static String SOLUTION_TEST_2 = "81";
    public static String SOLUTION_INPUT_1 = "709";
    public static String SOLUTION_INPUT_2 = "1326";

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day10();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, SOLUTION_INPUT_1, SOLUTION_TEST_2, SOLUTION_INPUT_2));
        puzzle.hello();
    }
    public String first(String filePath) {
        TrailMap map = new TrailMap(InputReader.getInputAsCharMatrix(filePath));
        return map.trailScore() + "";
    }
    public String second(String filePath) {
        TrailMap map = new TrailMap(InputReader.getInputAsCharMatrix(filePath));
        return map.trailScore2() + "";
    }
    private static class TrailMap {
        IntMatrix map;
        Set<TrailHead> trailheads;
        public TrailMap(char[][] input) {
            map = new IntMatrix(input);
            trailheads = new HashSet<>();
            findTrailHeads();
            findAllTrails();
        }

        private void findTrailHeads() {
            for (int x = 0; x < map.width(); x++)
                for (int y=0; y<map.height(); y++)
                    if (map.get(x,y)==0) trailheads.add(new TrailHead(new TopographicXY(new Position(x,y), 0)));
        }

        private void findAllTrails() {
            for (TrailHead head : trailheads) {
                TopographicXY[] trail = new TopographicXY[10]; trail[0] = new TopographicXY(head.pos, 0);
                findTrails(head, head.pos, trail, 0);
            }
        }

        private void findTrails(TrailHead head, Position pos, TopographicXY[] trail, int next) {
            if (next!=0) {
                if (map.isOutOfBounds(pos)) return;
                if (map.get(pos) != next) return;
                trail[next] = new TopographicXY(pos, next);
                if (next==9) {
                    head.addTrail(new Trail(trail));
                    return;
                }

            }
            next++;
            findTrails(head, pos.North(), trail, next);
            findTrails(head, pos.East(), trail, next);
            findTrails(head, pos.South(), trail, next);
            findTrails(head, pos.West(), trail, next);
        }

        public int trailScore() {
            return trailheads.stream()
                    .map(TrailHead::getScore)
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        public int trailScore2() {
            return trailheads.stream()
                    .map(TrailHead::getRating)
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        private record TopographicXY (Position pos, int altitude) {}
        private record Trail (TopographicXY[] paths) {}

        private class TrailHead {

            private Map<Position, Trail> trails = new HashMap<>();
            private Bag<Trail> allTrails = new HashBag<>();
            Position pos;

            public TrailHead(TopographicXY topo) {
                this.pos = topo.pos();
            }

            public void addTrail(Trail trail) {
                trails.put(trail.paths()[9].pos(), trail);
                allTrails.add(trail);
            }

            public int getScore() { return trails.size(); }

            public int getRating() {return allTrails.size(); }
        }
    }
}
