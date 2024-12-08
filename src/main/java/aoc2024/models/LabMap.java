package aoc2024.models;

import aoc2024.utils.Position;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@ToString
public class LabMap {
    private final int height;
    private final int width;
    private Position guardPosition;
    List<String> initialMapInput;
    char[][] map;
    char[][] breadcrumbs;
    char[][] obstaclesMap;

    public LabMap(List<String> lines) {
        initialMapInput = lines;
        width = lines.getFirst().length();
        height = lines.size();
        resetMap();
    }

    private void resetMap() {
        map = initialMapInput.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
        findGuard();
        breadcrumbs = new char[height][width];
        breadcrumbs[guardPosition.y()][guardPosition.x()] = get(guardPosition.x(), guardPosition.y());
      //  obstaclesMap = new char[height][width];
    }

    private char get(int x, int y) { return map[y][x]; }

    private void set(int x, int y, char c) { map[y][x] = c; }

    public int findObstructions() {
        int obstacles = 0;
        //testObstacles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                resetMap();
                if (guardPosition.x() == x && guardPosition.y() == y) continue;
                if (get(x, y) == '#') continue;
                set(x,y,'#');
                try { moveGuardUntilEnd(); } catch (LoopException e) { obstacles++; }
            }
        }
        return obstacles;
    }

    private void findGuard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                char c = get(x, y);
                if (c == '^' || c == '>' || c == 'v' || c == '<') guardPosition = new Position(x, y);
            }
        }
    }

    private void saveBreadCrumbs(int x, int y) throws LoopException {
        char currentCrumb = breadcrumbsAt(x, y);
        if (currentCrumb == '+')  throw new LoopException();
        breadcrumbs[y][x] = '+';
    }

    private char breadcrumbsAt(int x, int y) { return breadcrumbs[y][x]; }

    public int countVisited() {
        int visited = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (get(x, y) == 'X') visited++;
            }
        }
        return visited;
    }

    /* MOVEMENT METHODS */

    public void moveGuardUntilEnd() throws LoopException {
      //  saveBreadCrumbs(guardPosition.x, guardPosition.y, 'N');
        while (guardPosition != null) moveGuard();
    }

    private void moveGuard() throws LoopException {
        int x = guardPosition.x(), y = guardPosition.y();
        Direction dir = Direction.fromChar(get(x, y));
        if (dir == null) return;
        int nextX = x + dir.dx, nextY= y + dir.dy;
        try {
            if (get(nextX, nextY) == '#') {
                if (dir.equals(Direction.SOUTH) || dir.equals(Direction.NORTH))  saveBreadCrumbs(x, y);
                set(x,y, Direction.rotate90(dir).symbol );
            } else {
                set(x, y, 'X');
                set(nextX, nextY, dir.symbol);
                guardPosition = new Position(nextX, nextY);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            set(x, y, 'X');
            guardPosition = null;
        }
    }

    /* INTERNAL CLASSES */

    private enum Direction {
        NORTH('^', 'N', 0, -1),
        EAST('>', 'E', 1, 0),
        SOUTH('v', 'S', 0, 1),
        WEST('<', 'W', -1, 0);

        final char symbol;
        final char breadcrumb;
        final int dx, dy;

        Direction(char symbol, char breadcrumb, int dx, int dy) {
            this.symbol = symbol;
            this.breadcrumb = breadcrumb;
            this.dx = dx;
            this.dy = dy;
        }

        static Direction fromChar(char c) {
            for (Direction dir : values()) {
                if (dir.symbol == c) return dir;
            }
            return null;
        }

        static Direction rotate90(Direction d) {
            return switch (d) {
                case NORTH -> EAST;
                case EAST -> SOUTH;
                case SOUTH -> WEST;
                case WEST -> NORTH;
            };
        }
    }



    public static class LoopException extends Throwable {}
}
