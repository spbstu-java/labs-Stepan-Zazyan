package second.lab.matrixes.console;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleMatrixes {

    private static final Logger LOGGER = Logger.getLogger(ConsoleMatrixes.class.getName());

    public int[][] readMatrix(String path) {
        if (path == null || path.isBlank()) {
            throw new InputOutputException();
        }
        File file = new File(path);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            throw new InputOutputException(path);
        }
        try (Scanner scanner = new Scanner(file)) {
            int n = scanner.nextInt();
            if (n < 1 || n > 1_000_000) {
                throw new InputOutputException();
            }
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            return matrix;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            throw new InputOutputException(path);
        } catch (OutOfMemoryError e) {
            LOGGER.log(Level.WARNING, "Память закончилась");
            return new int[0][0];
        }
    }

    public int[][] createMatrix(int arraySize) {
        if (arraySize < 1 || arraySize > 1_000_000) {
            throw new BigNumException();
        }
        int[][] matrix = new int[arraySize][arraySize];
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                matrix[i][j] = (int) (Math.random() * arraySize * 2) - arraySize;
            }
        }
        return matrix;
    }

    public int[][] rotate90(int[][] matrix) {
        int[][] rotatedMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < rotatedMatrix.length; i++) {
            for (int j = 0; j < rotatedMatrix.length; j++) {
                rotatedMatrix[j][i] = matrix[matrix.length - i - 1][j];
            }
        }
        return rotatedMatrix;
    }

    public int[][] rotate180(int[][] matrix) {
        int[][] rotatedMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < rotatedMatrix.length; i++) {
            for (int j = 0; j < rotatedMatrix.length; j++) {
                rotatedMatrix[j][i] = matrix[matrix.length - j - 1][matrix.length - i - 1];
            }
        }
        return rotatedMatrix;
    }

    public int[][] rotate270(int[][] matrix) {
        int[][] rotatedMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < rotatedMatrix.length; i++) {
            for (int j = 0; j < rotatedMatrix.length; j++) {
                rotatedMatrix[i][j] = matrix[j][matrix.length - i - 1];
            }
        }
        return rotatedMatrix;
    }

    public int[][] devide(int[][] matrix) {
        int[][] rotatedMatrix = matrix.clone();
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix.length - 1; j++) {
                int summ = rotatedMatrix[(i - 1 + matrix.length) % matrix.length][j]
                        + rotatedMatrix[(i + 1) % matrix.length][j]
                        + rotatedMatrix[i][(j - 1 + matrix.length) % matrix.length]
                        + rotatedMatrix[i][(j + 1) % matrix.length];
                if (summ == 0) {
                    throw new DivideByZeroException();
                }
                rotatedMatrix[i][j] = rotatedMatrix[i][j] / summ;
            }
        }
        return rotatedMatrix;
    }

    public void printMatrix(int[][] matrix, String path, boolean append) {
        try (FileWriter writer = new FileWriter(path, append)) {
            for (int[] ints : matrix) {
                for (int j = 0; j < matrix.length; j++) {
                    writer.write(ints[j] + " ");
                }
                writer.write("\n");
            }
            writer.write("---------------------------------\n");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            throw new InputOutputException(path);
        }
    }
}