package org.example;

import java.util.Arrays;

public class ExplorationMap {
    public final Cell[][] matrix;

    public ExplorationMap(int n) {
        matrix = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = new Cell();
            }
        }
    }

    public synchronized boolean visit(int row, int col, Robot robot) {
        Cell cell = matrix[row][col];
        if (!cell.isVisited()) {
            cell.setVisited(true);
            System.out.println(robot.getName() + " merge la " + row + "-" + col);
            return true;
        }
        return false;
    }

    public int[] getNewPosition(int currentRow, int currentColoumn) {
        int n = matrix.length;
        int[] locationSelectedDirection = {-1, 0, 1};
        for (int i : locationSelectedDirection) {
            for (int j : locationSelectedDirection) {
                if ((currentRow + i) >= 0 && (currentRow + i) < n && (currentColoumn + j) >= 0 && (currentColoumn + j) < n && !matrix[currentRow + i][currentColoumn + j].isVisited() && (i != 0 || j != 0)) //nu viziteaza aceasi celula de doua ori{
                    return new int[] {currentRow + i, currentColoumn + j};
                }
            }
        }
        return null; // vizitate toate
    }


    @Override
    public String toString() {
        return "ExplorationMap{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
}
