package operations;

import model.Matrix;

/**
 * Provides static methods for core matrix operations used throughout the app.
 * Includes functionality for addition, subtraction, multiplication, inversion,
 * determinant calculation, and row reduction to RREF.
 *
 * This class serves as the computational engine for matrix-based tasks.
 *
 * @author Youssef Amin
 */

public class MatrixOperations {

    /**
     * Adds two matrices element-wise.
     * @param a the first matrix
     * @param b the second matrix
     * @return a new matrix that is the element-wise sum of a and b
     * @throws IllegalArgumentException if matrices have different dimensions
     */
    public static Matrix add(Matrix a, Matrix b) {
        validate(a, b);
        double[][] result = new double[a.getNumRow()][a.getNumCol()];
        double[][] dataA = a.getData();
        double[][] dataB = b.getData();

        for (int i = 0; i < a.getNumRow(); i++) {
            for (int j = 0; j < a.getNumCol(); j++) {
                result[i][j] = dataA[i][j] + dataB[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Subtracts matrix b from matrix a element-wise.
     * @param a the first matrix
     * @param b the second matrix to subtract from a
     * @return a new matrix that is the result of a - b
     * @throws IllegalArgumentException if matrices have different dimensions
     */
    public static Matrix subtract(Matrix a, Matrix b) {
        validate(a, b);
        double[][] result = new double[a.getNumRow()][a.getNumCol()];
        double[][] dataA = a.getData();
        double[][] dataB = b.getData();

        for (int i = 0; i < a.getNumRow(); i++) {
            for (int j = 0; j < a.getNumCol(); j++) {
                result[i][j] = dataA[i][j] - dataB[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Multiplies two matrices using standard matrix multiplication rules.
     * @param a the left matrix
     * @param b the right matrix
     * @return a new matrix that is the product of a and b
     * @throws IllegalArgumentException if a's column count does not match b's row count
     */
    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.getNumCol() != b.getNumRow()) {
            throw new IllegalArgumentException("Matrix dimensions do not allow multiplication.");
        }

        double[][] result = new double[a.getNumRow()][b.getNumCol()];
        double[][] dataA = a.getData();
        double[][] dataB = b.getData();

        for (int i = 0; i < a.getNumRow(); i++) {
            for (int j = 0; j < b.getNumCol(); j++) {
                for (int k = 0; k < a.getNumCol(); k++) {
                    result[i][j] += dataA[i][k] * dataB[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    /**
     * Returns the transpose of a matrix.
     * Swaps rows with columns.
     * @param m the matrix to transpose
     * @return the transposed matrix
     */
    public static Matrix transpose(Matrix m) {
        int rows = m.getNumRow();
        int cols = m.getNumCol();
        double[][] transposed = new double[cols][rows];
        double[][] data = m.getData();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = data[i][j];
            }
        }
        return new Matrix(transposed);
    }



    /**
     * Validates that two matrices have the same dimensions.
     * @param a the first matrix
     * @param b the second matrix
     * @throws IllegalArgumentException if matrix dimensions don't match
     */
    private static void validate(Matrix a, Matrix b) {
        if (a.getNumRow() != b.getNumRow() || a.getNumCol() != b.getNumCol()) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }
    }
}
