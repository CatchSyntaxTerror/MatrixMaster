package utils;

import model.Matrix;
import operations.MatrixOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Tests the methods in the MatrixOperations class.
 * Prints expected and actual results for each operation.
 *
 * @author Youssef Amin
 */
public class MatrixTester {

    static int passed = 0;
    /**
     * Runs all matrix operation tests and writes output to docs/matrix_test_output.txt.
     */

    public static void main(String[] args) {
        PrintStream originalOut = System.out;

        try {
            // Ensure the 'docs' directory exists (optional)
            File docsDir = new File("docs");
            if (!docsDir.exists()) {
                docsDir.mkdirs();
            }

            // Redirect output to the file
            PrintStream fileOut = new PrintStream("docs/matrix_test_output.txt");
            System.setOut(fileOut);

            testAddition();
            testSubtraction();
            testMultiplication();
            testInverse();
            testDeterminant();
            testRREF();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.setOut(originalOut);
        }
        System.out.println("All tests complete. Output written to docs/matrix_test_output.txt");
    }

    /**
     * Tests matrix addition.
     */
    private static void testAddition() {
        Matrix a = new Matrix(new double[][]{
                {1, 2},
                {3, 4}
        });
        Matrix b = new Matrix(new double[][]{
                {4, 5},
                {6, 7}
        });
        Matrix expected = new Matrix(new double[][]{
                {5, 7},
                {9, 11}
        });

        Matrix result = MatrixOperations.add(a, b);
        checkMatrixEquals(expected, result, "Addition", a, b);
    }

    /**
     * Tests matrix subtraction.
     */
    private static void testSubtraction() {
        Matrix a = new Matrix(new double[][]{
                {5, 7},
                {9, 11}
        });
        Matrix b = new Matrix(new double[][]{
                {1, 2},
                {3, 4}
        });
        Matrix expected = new Matrix(new double[][]{
                {4, 5},
                {6, 7}
        });

        Matrix result = MatrixOperations.subtract(a, b);
        checkMatrixEquals(expected, result, "Subtraction", a, b);
    }

    /**
     * Tests matrix multiplication.
     */
    private static void testMultiplication() {
        Matrix a = new Matrix(new double[][]{
                {1, 2},
                {3, 4}
        });
        Matrix b = new Matrix(new double[][]{
                {2, 0},
                {1, 2}
        });
        Matrix expected = new Matrix(new double[][]{
                {4, 4},
                {10, 8}
        });

        Matrix result = MatrixOperations.multiply(a, b);
        checkMatrixEquals(expected, result, "Multiplication", a, b);
    }

    /**
     * Tests matrix inversion.
     */
    private static void testInverse() {
        Matrix m = new Matrix(new double[][]{
                {4, 7},
                {2, 6}
        });
        Matrix expected = new Matrix(new double[][]{
                {0.6, -0.7},
                {-0.2, 0.4}
        });

        Matrix result = MatrixOperations.inverse(m);
        checkMatrixClose(expected, result, "Inverse", m, null);
    }

    /**
     * Tests determinant calculation.
     */
    private static void testDeterminant() {

        Matrix m = new Matrix(new double[][]{
                {1, 2},
                {3, 4}
        });
        double expected = -2.0;
        double result = MatrixOperations.determinant(m);
        double end = System.nanoTime();
        System.out.println("=== Determinant Test ===");
        System.out.println("Input: \n" + m);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + result);
        System.out.println((Math.abs(expected - result) < 1e-6 ? "PASSED" : "FAILED") + "\n");
    }

    /**
     * Tests reduced row echelon form (RREF).
     */
    private static void testRREF() {
        Matrix m = new Matrix(new double[][]{
                {1, 2, -1, -4},
                {2, 3, -1, -11},
                {-2, 0, -3, 22}
        });

        Matrix expected = new Matrix(new double[][]{
                {1, 0, 0, -8},
                {0, 1, 0, 1},
                {0, 0, 1, -2}
        });

        Matrix result = MatrixOperations.rref(m);
        checkMatrixClose(expected, result, "RREF", m, null);
    }

    /**
     * Checks if two matrices are exactly equal and prints results.
     */
    private static void checkMatrixEquals(Matrix expected, Matrix actual, String label, Matrix input1, Matrix input2) {
        prompt(expected, actual, label, input1, input2);
        System.out.println(expected.equals(actual) ? "PASSED\n" : "FAILED\n");
    }

    /**
     * Checks if two matrices are approximately equal.
     */
    private static void checkMatrixClose(Matrix expected, Matrix actual, String label, Matrix input1, Matrix input2) {
        prompt(expected, actual, label, input1, input2);

        double[][] e = expected.getData();
        double[][] a = actual.getData();
        boolean match = true;

        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e[0].length; j++) {
                if (Math.abs(e[i][j] - a[i][j]) > 1e-6) {
                    match = false;
                    break;
                }
            }
        }

        System.out.println(match ? "PASSED\n" : "FAILED\n");
    }

    private static void prompt(Matrix expected, Matrix actual, String label, Matrix input1, Matrix input2) {
        System.out.println("=== " + label + " Test ===");
        if (input1 != null) System.out.println("Input A: \n" + input1);
        if (input2 != null) System.out.println("Input B: \n" + input2);
        System.out.println("Expected: \n" + expected);
        System.out.println("Actual: \n" + actual);
    }
}
