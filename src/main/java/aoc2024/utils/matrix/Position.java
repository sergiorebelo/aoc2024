package aoc2024.utils.matrix;

import java.util.Objects;

public class Position {
    int x, y;

    public Position(int x, int y) { this.x = x; this.y = y; }

    public int x() { return x; }
    public int y() { return y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() { return Objects.hash(x, y); }

    public String toString() { return "(" + x + "," + y + ")"; }

    public Position North() { return new Position(x,y-1); }

    public Position East() { return new Position(x+1,y); }

    public Position South() { return new Position(x,y+1); }

    public Position West() { return new Position(x-1,y); }
}