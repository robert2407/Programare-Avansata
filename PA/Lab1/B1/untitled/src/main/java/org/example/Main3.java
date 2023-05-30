package org.example;

public class Main3 {
    public static void main(String[] args) {
        int n = 6;
        int degree = 3;

        try {

            System.out.println("Numbar de noduri: " + n);
            System.out.println("Numar de muchii: " + degree);

            if (degree >= n) {
                System.out.println("Imposibil");
                return;
            }

            int[][] adjacencyMatrix = creareMatrice(n, degree);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error");
        }
    }

    private static int[][] creareMatrice(int n, int degree) {
        int[][] adjacencyMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= degree / 2; j++) {
                int previous = (i - j + n) % n;
                int next = (i + j) % n;
                adjacencyMatrix[i][previous] = 1;
                adjacencyMatrix[i][next] = 1;
            }
        }

        return adjacencyMatrix;
    }
}

