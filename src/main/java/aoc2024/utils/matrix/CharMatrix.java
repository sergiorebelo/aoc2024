package aoc2024.utils.matrix;

import aoc2024.utils.matrix.Position;
import lombok.Getter;

@Getter
public class CharMatrix {


    private final char[][] model;
    private final int width;
    private final int height;

    public CharMatrix(char[][] input) {
        this.model = input;
        this.width=input[0].length;
        this.height= input.length;
    }

    public boolean isPositionInside(Position pos) { return isXYInside(pos.x(), pos.y()); }

    public boolean isXYInside(int x, int y) { return x >= 0 && x < width && y >= 0 && y < height; }

    public boolean isOutOfBounds(int x, int y) { return !isXYInside(x,y); }

    public char get(int x, int y) { return model[y][x]; }

    public void set(int x, int y, char c) { model[y][x] = c; }

    public String toSTring() { return MapUtils.print(model); }

}
