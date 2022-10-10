package second.lab.matrixes.console;

import org.junit.jupiter.api.Test;

class ConsoleMatrixesTest {
    private static final String writePath = "./outputMatrixes.txt";

    @Test
    void test() {
        ConsoleMatrixes matrixReader = new ConsoleMatrixes();
        String path = "src/test/java/second/lab/matrixes/console/input.txt";

        // 1
        int[][] matrix = matrixReader.readMatrix(path);
        matrixReader.printMatrix(matrix, writePath, Boolean.FALSE);
        // 2
        matrix = matrixReader.createMatrix(5);
        matrixReader.printMatrix(matrix, writePath, Boolean.TRUE);
        // 3
        matrix = matrixReader.rotate90(matrix);
        matrix = matrixReader.devide(matrix);
        matrix = matrixReader.rotate180(matrix);
        matrix = matrixReader.devide(matrix);
        matrix = matrixReader.rotate270(matrix);
        matrix = matrixReader.devide(matrix);
        matrixReader.printMatrix(matrix, writePath, Boolean.TRUE);
    }
}