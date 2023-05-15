package org.example;

public class Board {
    private static final int SIZE = 15;
    private Player[][] grid;

    public Board() {
        grid = new Player[SIZE][SIZE];
    }

    public void placeMove(int row, int col, Player player) {
        grid[row][col] = player;
    }

    public boolean isValidMove(int row, int col) {
        return grid[row][col] == null;
    }

    public boolean hasStraightLineOfLength(int length) {
        return checkRows(length) || checkColumns(length) || checkDiagonals(length);
    }

    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRows(int length) { //am pus de la 0 la 14 pt ambele(linii si coloane)
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col <= SIZE - length; col++) {
                Player firstPlayer = grid[row][col];
                if (firstPlayer != null) {
                    boolean foundLine = true;
                    for (int i = 1; i < length; i++) {
                        if (grid[row][col + i] != firstPlayer) {
                            foundLine = false;
                            break;
                        }
                    }
                    if (foundLine) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkColumns(int length) {
        for (int col = 0; col < SIZE; col++) {
            for (int row = 0; row <= SIZE - length; row++) {
                Player firstPlayer = grid[row][col];
                if (firstPlayer != null) {
                    boolean foundLine = true;
                    for (int i = 1; i < length; i++) {
                        if (grid[row + i][col] != firstPlayer) {
                            foundLine = false;
                            break;
                        }
                    }
                    if (foundLine) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals(int length) {
        for (int row = 0; row <= SIZE - length; row++) {
            for (int col = 0; col <= SIZE - length; col++) {    //din stg sus in drpt jos
                Player firstPlayer = grid[row][col];
                if (firstPlayer != null) {  //pe diagonale (asemanatoare la toate 3 )
                    boolean foundLine = true;
                    for (int i = 1; i < length; i++) {
                        if (grid[row + i][col + i] != firstPlayer) {
                            foundLine = false;
                            break;
                        }
                    }
                    if (foundLine) {
                        return true;
                    }
                }
            }
        }

        for (int row = length - 1; row < SIZE; row++) {
            for (int col = 0; col <= SIZE - length; col++) {
                Player firstPlayer = grid[row][col];
                if (firstPlayer != null) {  //din stg jos in drpt sus
                    boolean foundLine = true;
                    for (int i = 1; i < length; i++) {
                        if (grid[row - i][col + i] != firstPlayer) {
                            foundLine = false;
                            break;
                        }
                    }
                    if (foundLine) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(grid[i][j]).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
