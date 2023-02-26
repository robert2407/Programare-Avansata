                                                    // Tema 1
public class H1 {
    public static void main(String[] args) {
        String numar = args[0];

        try {
            int n = Integer.parseInt(numar);
            System.out.println("Numar corect " + n);
        } catch (NumberFormatException e) {
            System.out.println("Numar incorect " + numar);
        }

        int n = Integer.parseInt(numar);
        int m[][] = new int[n][n];
        
        if (n < 100){                           // sau 30_000
            for (int i = 0; i < n; i++) 
                for (int j = 0; j < n; j++) 
                    m[i][j] = (i + j) % n + 1;  

            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(m[i][j] + " ");
                }
                System.out.println("\n");
            }

            for (int i = 0; i < n; i++) {
                String linie = "";
                for (int j = 0; j < n; j++) {
                    linie += m[i][j];
                }
                System.out.println("Linia " + (i+1) + " este " + linie);
            }
            
            for (int j = 0; j < n; j++) {
                String coloana = "";
                for (int i = 0; i < n; i++) {
                    coloana += m[i][j];
                }
                System.out.println("Coloana " + (j+1) + " este " + coloana);
            }
        } else {
            long t1 = System.currentTimeMillis();

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
    }
}

//javac H1.java
//java -Xms4G -Xmx4G H1.java 9  
//java -Xms4G -Xmx4G H1.java 1500  