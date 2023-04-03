package org.example;

import org.example.Cell;

import java.awt.*;
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

    public int[] getNewPosition() {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!matrix[i][j].isVisited()) {
                    return new int[] {i, j};
                }
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
