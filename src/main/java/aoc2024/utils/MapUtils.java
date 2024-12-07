package aoc2024.utils;

public class MapUtils {

    private static void print(char[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++)
                System.out.print((map[y][x] == 0 ? "." : map[y][x]) + " ");
            System.out.print("\n");
        }
    }

    private static void printAOverBOverC(char[][] a, char[][] b, char[][] c) {
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[0].length; x++)
                System.out.print((a[y][x] == 0 ? (b[y][x] == 0 ? (c[y][x] == 0 ? " " : c[y][x]) : b[y][x]) : a[y][x]));
            System.out.print("\n");
        }
    }
}
