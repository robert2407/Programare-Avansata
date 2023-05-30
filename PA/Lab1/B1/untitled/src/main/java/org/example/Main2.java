package org.example;
public class Main2 {        //graf ciclic
    public static void main(String[] args) {
        try {
            int n = 20;
            System.out.println("Numar corect " + n);

            if (n < 100) {
                int[][] m = new int[n][n];

                for (int i = 0; i < n; i++) {
                    m[i][(i + 1) % n] = 1;
                    m[(i + 1) % n][i] = 1;
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(m[i][j] + " ");
                    }
                    System.out.println();
                }

                int[][][] powers = new int[n + 1][n][n];
                powers[1] = m;
                for (int i = 2; i <= n; i++) {
                    powers[i] = inmultireDeMatrici(powers[i - 1], m);
                }

                for (int i = 1; i <= n; i++) {      //elementul lui A^i de pe linia j si coloana k
                    System.out.println("A^" + i + ":");
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < n; k++) {
                            System.out.print(powers[i][j][k] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Numar incorect ");
        }
    }

    private static int[][] inmultireDeMatrici(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }
}
