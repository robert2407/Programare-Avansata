package org.example;

import java.util.Arrays;

public class ExplorationMap {
    public final Cell[][] matrix;
    public int currentRow;
    public int currentCol;
    public ExplorationMap(int n) {
        matrix = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = new Cell();
            }
        }
        currentRow = -1;
        currentCol = -1;
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

    /*
    public int[] getNewPosition(int currentRow, int currentColoumn) {
        int n = matrix.length;
        int[] locationSelectedDirection = {-1, 0, 1};
        for (int i : locationSelectedDirection) {
            for (int j : locationSelectedDirection) {
                if ((currentRow + i) >= 0 && (currentRow + i) < n && (currentColoumn + j) >= 0 && (currentColoumn + j) < n && !matrix[currentRow + i][currentColoumn + j].isVisited() && (i != 0 || j != 0)) //nu viziteaza aceasi celula de doua ori{
                    return new int[] {currentRow + i, currentColoumn + j};
                }
            }
        return null; // vizitate toate
    }*/

    public int[] getNewPosition(int currentRow, int currentColumn) { // systematic fashion algorithm
        int n = matrix.length;
        int[][] locationSelectedDirection = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // sus jos stg dr - daca urm patratel este in matrice si nu a fost vizitata ii returnez coord
        for (int[] dir : locationSelectedDirection) {                   //altfel incecrc alta directie . daca toate sunt vizitate si nu mai avem patratele adicente returnez null
            int nextRow = currentRow + dir[0];                          //astefel vizitatea e intr-un mod sistematic, asa cum cere enuntul, si asigura vizitarea tuturor patratelelor.
            int nextColumn = currentColumn + dir[1];
            if (nextRow >= 0 && nextRow < n && nextColumn >= 0 && nextColumn < n && !matrix[nextRow][nextColumn].isVisited()) {
                return new int[] {nextRow, nextColumn};
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "ExplorationMap{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
}
