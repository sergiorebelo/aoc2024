package aoc2024.utils;

public class TextSoup {
    private final char[][] board;

    public TextSoup(char[][] board) {
        this.board = board;
    }

    public int scanForWord(String word) {
        String[] directions = new String[] {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        int wordsFound = 0;
        char firstChar = word.charAt(0);
        for (int x = 0; x < boardWidth() ; x++) {
            for (int y = 0; y < boardHeight(); y++) {
                if (board[x][y] == firstChar) {
                    for (String direction:directions) {
                        if (checkWord(word, x, y, direction)) wordsFound++;
                    }
                }
            }
        }
        return wordsFound;
    }

    private int boardWidth() {
        return board.length;
    }

    private int boardHeight() {
        return board[0].length;
    }

    private boolean checkWord(String word, int x, int y, String direction) {
        for (int i = 0; i < word.length(); i++) {
            if (outOfBounds(x,y)) return false;
            if (word.charAt(i) != board[x][y]) return false;
            x += moveHorizontally(direction);
            y += moveVertically(direction);
        }
        return true;
    }

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x >= boardWidth() || y < 0 || y >= boardHeight();
    }

    private int moveHorizontally(String direction) {
        return (direction.endsWith("E")? 1 : (direction.endsWith("W") ? -1: 0));
    }

    private int moveVertically(String direction) {
        return (direction.startsWith("S")? 1 : (direction.startsWith("N") ? -1: 0));
    }

    /////////////////////////////////////////////////////////////// PART 2

    public int scanForCrossMas() {
        int wordsFound = 0;
        for (int x = 1; x < boardWidth()-1; x++) {
            for (int y = 1; y < boardHeight()-1; y++) {
                if (board[x][y] == 'A' && checkCross(x,y)) wordsFound++;
            }
        }
        return wordsFound;
    }

    private boolean isMs(char[] word) {
        return (word[0]=='M' && word[1]=='S') || (word[1]=='M' && word[0]=='S') ;
    }

    private boolean checkCross(int x, int y) {
        char[] nesw = new char[] { board[x-1][y+1], board[x+1][y-1] };
        char[] nwse = new char[] { board[x-1][y-1], board[x+1][y+1] };
        return  (isMs(nesw) && isMs(nwse));
    }

    private boolean isCross(int x, int y, int dx1, int dy1, int dx2, int dy2) {
        char c1 = board[x+dx1][y+dy1];
        char c2 = board[x+dx2][y+dy2];
        return (c1 == 'M' && c2 == 'S') || (c1 == 'S' && c2 == 'M');
    }
}