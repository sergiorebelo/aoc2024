package aoc2024.utils.matrix;

import aoc2024.puzzles.Day10;

public class IntMatrix {


    private final int[][] model;
    private final int width;
    private final int height;






    public IntMatrix(char[][] input) {

        this.width=input[0].length;
        this.height= input.length;
        model = new int[height][width];
        for (int x = 0; x < width(); x++) {
            for (int y=0; y<height(); y++) {
                model[y][x] = input[y][x]-'0';
            }
        }
    }

    public boolean isPositionInside(Position pos) { return isXYInside(pos.x(), pos.y()); }

    public boolean isXYInside(int x, int y) { return x >= 0 && x < width && y >= 0 && y < height; }

    public boolean isOutOfBounds(int x, int y) { return !isXYInside(x,y); }

    public int get(int x, int y) { return model[y][x]; }

    public void set(int x, int y, int i) { model[y][x] = i; }

    public int width() { return width; }

    public int height() { return height; }

    public int get(Position pos) { return get(pos.x(), pos.y()); }

    public boolean isOutOfBounds(Position pos) { return isOutOfBounds(pos.x(), pos.y()); }

    public int[][] getModel() { return model;}
}
