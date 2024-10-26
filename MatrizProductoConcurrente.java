class MatrizMultiplicacionThread extends Thread {
    private int[][] A, B, C;
    private int inicio, fin, size;

    public MatrizMultiplicacionThread(int[][] A, int[][] B, int[][] C, int inicio, int fin) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.inicio = inicio;
        this.fin = fin;
        this.size = A.length;
    }

    public void run() {
        for (int i = inicio; i < fin; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = 0;
                for (int k = 0; k < size; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}

public class MatrizProductoConcurrente {
    public static void main(String[] args) throws InterruptedException {
        int size = 1000; // Tamaño de la matriz
        int[][] matrizA = new int[size][size];
        int[][] matrizB = new int[size][size];
        int[][] resultado = new int[size][size];

        // Inicializar matrices A y B con valores aleatorios
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrizA[i][j] = (int) (Math.random() * 10);
                matrizB[i][j] = (int) (Math.random() * 10);
            }
        }

        long inicio = System.currentTimeMillis();

        // Crear y lanzar hilos
        int numThreads = 4;
        MatrizMultiplicacionThread[] threads = new MatrizMultiplicacionThread[numThreads];
        int filasPorHilo = size / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int inicioFilas = i * filasPorHilo;
            int finFilas = (i == numThreads - 1) ? size : inicioFilas + filasPorHilo; // Último hilo toma el resto
            threads[i] = new MatrizMultiplicacionThread(matrizA, matrizB, resultado, inicioFilas, finFilas);
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (MatrizMultiplicacionThread thread : threads) {
            thread.join();
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo concurrente: " + (fin - inicio) + " ms");
    }
}
