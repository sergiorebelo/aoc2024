package aoc2024.models;

import aoc2024.utils.Position;
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

    public boolean isPositionInside(Position pos) { return pos.x() >= 0  && pos.x() < width && pos.y() >= 0 && pos.y() < height; }

    public char get(int x, int y) { return model[y][x]; }
}
