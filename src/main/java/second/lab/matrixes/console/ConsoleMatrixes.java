package second.lab.matrixes.console;

import java.io.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleMatrixes {
    public static void main(String[] args) throws IOException {
        String file = "/home/stepan/Computer engineering/5th Semester/Object-oriented programming/labs_Stepan_Zazyan/src/main/java/second/lab/matrixes/console/inputMatrixSize.txt";
        checkIOFile(new File(file));
        int arraySize = 0;
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                arraySize = Integer.parseInt(line);
                checkNum(arraySize);
            }
        } catch (InputOutputException | BigNumException e) {
            e.printStackTrace();
        }
        System.out.println(arraySize);
        Logger logger = Logger.getAnonymousLogger();
/*        OutOfMemoryError eLog = new OutOfMemoryError();
        logger.log(Level.SEVERE, "Логирование исключения по памяти", eLog);*/
        DecimalFormat dF = new DecimalFormat("#.##");
        FileWriter fileWriter = new FileWriter("/home/stepan/Computer engineering/5th Semester/Object-oriented programming/labs_Stepan_Zazyan/src/main/java/second/lab/matrixes/console/output.txt");
        BufferedWriter outputWriter = new BufferedWriter(fileWriter);
        try {
            double[][] matrixOrig = new double[arraySize][arraySize];
            double[][] matrixTemp = new double[arraySize][arraySize];

            for (int i = 0; i < arraySize; i++) {
                for (int j = 0; j < arraySize; j++) {
                    matrixOrig[i][j] = (int) (Math.random() * arraySize * 2) - arraySize;
                    System.out.print(matrixOrig[i][j] + " ");
                }
                System.out.println();
            }
            try {
                outputWriter.write("Поворот на 90 градусов");
                outputWriter.newLine();
            } catch (InputOutputException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < arraySize; i++) {
                for (int j = 0; j < arraySize; j++) {
                    try {
                        if (j == 0) {
                            matrixTemp[i][j] = (divide(matrixOrig[j][matrixOrig.length - i - 1], matrixOrig[i][j + 1]));
                        } else if (j == arraySize - 1) {
                            matrixTemp[i][j] = (divide(matrixOrig[j][matrixOrig.length - i - 1], matrixOrig[i][j - 1]));
                        } else {
                            matrixTemp[i][j] = (divide(matrixOrig[j][matrixOrig.length - i - 1], matrixOrig[i][j + 1] + matrixOrig[i][j - 1]));
                        }
                    } catch (DivideByZeroException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputWriter.write(dF.format(matrixTemp[i][j]) + " ");
                    } catch (InputOutputException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    outputWriter.newLine();
                } catch (InputOutputException e) {
                    e.printStackTrace();
                }
            }
            try {
                outputWriter.write("Поворот на 180 градусов");
                outputWriter.newLine();
            } catch (InputOutputException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < arraySize; i++) {
                for (int j = 0; j < arraySize; j++) {
                    try {
                        if (j == 0) {
                            matrixOrig[i][j] = divide(matrixTemp[arraySize - i - 1][arraySize - j - 1], matrixTemp[i][j + 1]);
                        } else if (j == arraySize - 1) {
                            matrixOrig[i][j] = divide(matrixTemp[arraySize - i - 1][arraySize - j - 1], matrixTemp[i][j - 1]);
                        } else {
                            matrixOrig[i][j] = divide(matrixTemp[arraySize - i - 1][arraySize - j - 1], matrixTemp[i][j + 1] + matrixTemp[i][j - 1]);
                        }
                    } catch (DivideByZeroException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputWriter.write(dF.format(matrixTemp[i][j]) + " ");
                    } catch (InputOutputException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    outputWriter.newLine();
                } catch (InputOutputException e) {
                    e.printStackTrace();
                }
            }
            try {
                outputWriter.write("Поворот на 270 градусов");
                outputWriter.newLine();
            } catch (InputOutputException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < arraySize; i++) {
                for (int j = 0; j < arraySize; j++) {
                    try {
                        if (j == 0) {
                            matrixTemp[i][j] = divide(matrixOrig[matrixOrig.length - j - 1][i], (matrixOrig[i][j + 1]));
                        } else if (j == arraySize - 1) {
                            matrixTemp[i][j] = divide(matrixOrig[matrixOrig.length - j - 1][i], (matrixOrig[i][j - 1]));
                        } else {
                            matrixTemp[i][j] = divide(matrixOrig[matrixOrig.length - j - 1][i], (matrixOrig[i][j + 1] + matrixOrig[i][j - 1]));
                        }
                    } catch (DivideByZeroException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputWriter.write(dF.format(matrixTemp[i][j]) + " ");
                    } catch (InputOutputException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    outputWriter.newLine();
                } catch (InputOutputException e) {
                    e.printStackTrace();
                }
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (OutOfMemoryError eLogTrue) {
            logger.log(Level.SEVERE, "Логирование исключения по памяти", eLogTrue);
        }
    }

    public static double divide(double a, double b) throws DivideByZeroException {
        if (b == 0) {
            throw new DivideByZeroException("нельзя делить на ноль ");
        }
        return a / b;
    }

    public static void checkNum(int a) throws BigNumException {
        if (a > 1000000) {
            throw new BigNumException("Число больше 1000000");
        }
    }

    public static void checkIOFile(File file) throws InputOutputException {
        if (!file.exists()) {
            throw new InputOutputException("Файла нет");
        }
        if (!file.canWrite()) {
            throw new InputOutputException("Только для чтения");
        }
    }
}