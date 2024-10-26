public class MatrizProductoSecuencial {
    public static void main(String[] args) {
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

        // Calcular el producto de matrices
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultado[i][j] = 0;
                for (int k = 0; k < size; k++) {
                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo secuencial: " + (fin - inicio) + " ms");
    }
}
