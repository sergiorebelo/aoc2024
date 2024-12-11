package aoc2024.puzzles;

import aoc2024.utils.data.Pair;
import aoc2024.utils.files.InputReader;
import aoc2024.utils.puzzles.BaseDailyPuzzle;
import aoc2024.utils.puzzles.DailyPuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class Day09 extends BaseDailyPuzzle {

    public static String SOLUTION_TEST_1 = "1928";
    public static String SOLUTION_TEST_2 = "2858";
    public static String SOLUTION_INPUT_1 = "6344673854800";
    public static String SOLUTION_INPUT_2 = "6360363199987";

    public static void main(String[] args) {
        DailyPuzzle puzzle = new Day09();
        puzzle.setExpectedSolution(new PuzzleSolution(SOLUTION_TEST_1, 0, SOLUTION_TEST_2, 0));
        puzzle.hello();
    }
    public String first(String filePath) {

        map = new ArrayList<Block>();
        String line = InputReader.getInputAsOneLine(filePath);
        createMap(line);
        fillEmptySpaces();
        return checksum() + "" ;
    }

    private void createMap(String line) {
        boolean emptySpace = false;
        int id=0;
        int position=0;
        for(char c: line.toCharArray()) {
            int blocks =  c-'0';
            if (emptySpace) {
                emptySpace = false;
                position = fillSpaces(position, blocks);
                continue;
            }
            position = addUp(position, blocks, id);
            emptySpace = true;
            id++;
        }
    }

    private long checksum() {
        return IntStream.range(0, map.size())
                .filter(i -> !map.get(i).empty())
                .mapToLong(i -> map.get(i).id * i)
                .sum();
    }

    private void fillEmptySpaces() {
        int i = 0, j = map.size() - 1;
        while (i < j) {
            if (map.get(i).empty()) {
                while (map.get(j).empty()) { j--; }
                map.set(i, map.get(j));
                map.remove(j);
                j--;
            }
            i++;
        }
    }

    private int findEmptySpace(int neededSize, int leftTo) {
        for (int i=0, size=0; i < leftTo; i++) {
            if (map.get(i).empty) {
                size++;
                if (size >= neededSize) { return i-size + 1; }
            } else size = 0;
        }
        return -1;
    }

    private Pair<Integer> findBlockSizeAndPosition(int id) {
        boolean found = false;
        int size=0;
        for (int j = map.size()-1; j>=0; j--)  {
            if (map.get(j).id == id ) {
                found = true;
                size++;
            }
            else if (found) return new Pair<>(Integer.valueOf(size), Integer.valueOf(j+1));
        }
        return new Pair<>(size, 0);
    }

    List<Block> map;

    private int fillSpaces(int position, int howMany) {
        for (int i=0; i<howMany; i++ ) {
            map.add(position+i, new Block(0, true));
        }
        return position + howMany;
    }

    private int addUp(int position, int howMany, int id) {
        for (int i=0; i<howMany; i++ ) {
            map.add(position+i, new Block(id, false));
        }
        return position + howMany;
    }


    public String second(String filePath) {
        map = new ArrayList<Block>();
        String line = InputReader.getInputAsOneLine(filePath);
        createMap(line);
        fillEmptySpacesDefrag();
        return checksum() + "" ;
    }

    private void fillEmptySpacesDefrag() {
        int biggestId = map.get(map.size()-1).id;
        while (biggestId > 0) { biggestId = moveBlock(biggestId)-1; }
    }

    private int moveBlock(int id) {
        if (id == 0) return -1;

        Pair<Integer> sizeAndPosition = findBlockSizeAndPosition(id);
        int blockSize = sizeAndPosition.getOne(), blockPosition = sizeAndPosition.getTwo(), emptyPos = findEmptySpace(blockSize, blockPosition);

        if (emptyPos == -1) { return moveBlock(id-1); }
        if (emptyPos < blockPosition) {
            int  j = emptyPos + blockSize;
            blockPosition += blockSize;
            while ( emptyPos < j ) {
                blockPosition--;
                map.set(emptyPos, map.get(blockPosition));
                map.set(blockPosition, new Block(0,true));
                emptyPos++;
            }
            return id;
        }
        else return -1;
    }

    private record Block(int id, boolean empty) {


        @Override
        public String toString() {
            return empty? "." : "" + id;
        }

    }
}
