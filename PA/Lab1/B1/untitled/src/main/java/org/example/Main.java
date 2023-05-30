package org.example;

public class Main {
    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Fara argumente");
//            return;
//        }

//        String numar = args[0];

        try {
//            int n = Integer.parseInt(numar);
            int n = (int) (Math.random() * 1000);
            System.out.println("Numar corect " + n);

            if (n < 100) {
                int[][] m = new int[n][n];

                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        m[i][j] = (i + j) % n + 1;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(m[i][j] + " ");
                    }
                    System.out.println();
                }

                for (int i = 0; i < n; i++) {
                    String linie = "";
                    for (int j = 0; j < n; j++) {
                        linie += m[i][j];
                    }
                    System.out.println("Linia " + (i + 1) + " este " + linie);
                }

                for (int j = 0; j < n; j++) {
                    String coloana = "";
                    for (int i = 0; i < n; i++) {
                        coloana += m[i][j];
                    }
                    System.out.println("Coloana " + (j + 1) + " este " + coloana);
                }
            } else {
                long t1 = System.currentTimeMillis();

                int[][] m = new int[n][n];

                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        m[i][j] = (i + j) % n + 1;

                for (int i = 0; i < n; i++) {
                    String linie = "";
                    for (int j = 0; j < n; j++) {
                        linie += m[i][j];
                    }
                }

                for (int j = 0; j < n; j++) {
                    String coloana = "";
                    for (int i = 0; i < n; i++) {
                        coloana += m[i][j];
                    }
                }

                long t2 = System.currentTimeMillis();
                System.out.println("Milisecunde " + (t2 - t1));
            }
        } catch (NumberFormatException e) {
            System.out.println("Numar incorect ");
        }
    }
}
