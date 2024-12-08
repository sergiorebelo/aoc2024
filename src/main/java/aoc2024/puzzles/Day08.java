package aoc2024.puzzles;

import aoc2024.models.CharMatrix;
import aoc2024.utils.BaseDailyPuzzle;
import aoc2024.utils.DailyPuzzle;
import aoc2024.utils.InputReader;
import aoc2024.utils.Pair;
import aoc2024.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day08 extends BaseDailyPuzzle {


    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day08();
        puzzle.hello();
    }
    public String first(String filePath) {
        AntennaMap input = getInputAsAntennaMap(filePath);
        return input.getNumberOfAntinodes() + "";
    }

    private AntennaMap getInputAsAntennaMap(String filePath) {
        return new AntennaMap(InputReader.getInputAsCharMatrix(filePath));
    }


    public String second(String filePath) {
        AntennaMap input = getInputAsAntennaMap(filePath);
        return input.getNumberOfNewAntinodes() + "";
    }

    public PuzzleSolution getExpectedSolution() {
        return new PuzzleSolution("14", "308", "34", "1147");
    }

    private class AntennaMap {

        CharMatrix map;
        Map<Character, Set<Position>> frequencies = new HashMap<>();
        private Set<Position> antinodes, newAntinodes;

        public AntennaMap(char[][] input) {
            map = new CharMatrix(input);
            this.antinodes = new HashSet<Position>();
            this.newAntinodes = new HashSet<Position>();
            saveFrequenciesMap();
            calculateAntiNodes();
        }

        private void calculateAntiNodes() {
            for (Character c:frequencies.keySet()) {
                List<Pair<Position>> posPairs = createPositionPairs(frequencies.get(c));
                for(Pair pair:posPairs) {
                    createAntiNodes(pair);
                }
            }
        }

        private void createAntiNodesForPair(Pair pair) {
            int dx=  ((Position) pair.getOne()).x() - ((Position) pair.getTwo()).x();
            int dy=  ((Position) pair.getOne()).y() - ((Position) pair.getTwo()).y();
            Position antinode1 = new Position (((Position) pair.getOne()).x() + dx, ((Position) pair.getOne()).y() +dy );
            Position antinode2 = new Position (((Position) pair.getTwo()).x() - dx, ((Position) pair.getTwo()).y() -dy );
            if (map.isPositionInside(antinode1)) antinodes.add(antinode1);
            if (map.isPositionInside(antinode2)) antinodes.add(antinode2);
        }

        private void createAntiNodes(Pair pair) {
            Position pos1 = (Position) pair.getOne();
            Position pos2 = (Position) pair.getTwo();
            int dx = pos1.x() - pos2.x();
            int dy = pos1.y() - pos2.y();
            //Part 1
            addSingleAntinode(pos1, dx, dy);
            addSingleAntinode(pos2, -dx, -dy);
            //Part 2
            addExtendedAntinodes(pos1, dx, dy);
            addExtendedAntinodes(pos2, -dx, -dy);
        }

        private void addSingleAntinode(Position start, int dx, int dy) {
            Position antinode = new Position(start.x() + dx, start.y() + dy);
            if (map.isPositionInside(antinode)) { antinodes.add(antinode); }
        }

        private void addExtendedAntinodes(Position start, int dx, int dy) {
            Position antinode = start;
            while (map.isPositionInside(antinode)) {
                newAntinodes.add(antinode);
                antinode = new Position(antinode.x() + dx, antinode.y() + dy);
            }
        }

        public int getNumberOfAntinodes () { return antinodes.size(); }

        public int getNumberOfNewAntinodes () { return newAntinodes.size(); }

        private List<Pair<Position>> createPositionPairs(Set<Position> positionsSet) {
            List posPairs = new ArrayList();
            Position[] positions = positionsSet.toArray(new Position[0]);
            for(int i=0; i<positions.length-1 ;  i++)
                for(int j = i+1; j < positions.length ; j++)
                    posPairs.add(new Pair(positions[i], positions[j]));
            return posPairs;
        }

        private void saveFrequenciesMap() {
            for(int x=0; x<map.getWidth(); x++) {
                for(int y=0; y<map.getHeight(); y++) {
                    char freq = map.get(x,y);
                    if (freq == '.') continue;
                    Set<Position> antennas = frequencies.get(Character.valueOf(freq));
                    if (antennas == null) {
                        antennas = new HashSet<Position>();
                        frequencies.put(freq, antennas);
                    }
                    antennas.add(new Position(x,y));
                }
            }
        }
    }
}
