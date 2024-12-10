package aoc2024.utils.matrix;

public class MapUtils {

    public static String print(char[][] map) {
        String result = "";
        for (char[] chars : map) {
            for (int x = 0; x < map[0].length; x++)
                result += (chars[x] == 0 ? "." : chars[x]) + " \n";
            result += "\n";
        }
        return result;
    }
    public static String print(int[][] map) {
        String result = "";
        for (int[] ints : map) {
            for (int x = 0; x < map[0].length; x++)
                result += ints[x] + " ";
            result += "\n";
        }
        return result;
    }

    public static void printAOverBOverC(char[][] a, char[][] b, char[][] c) {
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[0].length; x++)
                System.out.print((a[y][x] == 0 ? (b[y][x] == 0 ? (c[y][x] == 0 ? " " : c[y][x]) : b[y][x]) : a[y][x]));
            System.out.print("\n");
        }
    }
}
