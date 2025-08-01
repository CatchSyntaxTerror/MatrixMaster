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
     * Computes the inverse of a square matrix using Gauss-Jordan elimination.
     * The input matrix is not modified; a new Matrix object is returned.
     *
     * @param m the square matrix to invert
     * @return the inverse matrix
     * @throws IllegalArgumentException if the matrix is not square or not invertible
     */
    public static Matrix inverse(Matrix m) {
        if (!m.isSquare()) {
            throw new IllegalArgumentException("Matrix must be square to compute inverse.");
        }

        int n = m.getNumRow();
        double[][] augmented = buildAugmentedMatrix(m);
        GaussJordanElimination(augmented);
        double[][] inverse = extractInverse(augmented);

        return new Matrix(inverse);
    }

    /**
     * Builds an augmented matrix [A | I] from the input square matrix A,
     * where I is the identity matrix of the same size.
     *
     * @param m the input square matrix
     * @return a new 2D array representing the augmented matrix [A | I]
     */
    private static double[][] buildAugmentedMatrix(Matrix m) {
        int n = m.getNumRow();
        double[][] A = m.getData();
        double[][] augmented = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, augmented[i], 0, n);
            augmented[i][i + n] = 1.0;
        }

        return augmented;
    }

    /**
     * Performs Gauss-Jordan elimination on the given augmented matrix.
     * Converts the left half of the matrix to the identity matrix.
     *
     * @param augmented the augmented matrix [A | I], modified in-place
     * @throws IllegalArgumentException if the matrix is singular (non-invertible)
     */
    private static void GaussJordanElimination(double[][] augmented) {
        int n = augmented.length;

        for (int pivot = 0; pivot < n; pivot++) {
            double pivotVal = augmented[pivot][pivot];
            if (Math.abs(pivotVal) < 1e-10) {
                throw new IllegalArgumentException("Matrix is singular and cannot be inverted.");
            }

            for (int j = 0; j < 2 * n; j++) {
                augmented[pivot][j] /= pivotVal;
            }

            for (int i = 0; i < n; i++) {
                if (i != pivot) {
                    double factor = augmented[i][pivot];
                    for (int j = 0; j < 2 * n; j++) {
                        augmented[i][j] -= factor * augmented[pivot][j];
                    }
                }
            }
        }
    }

    /**
     * Extracts the inverse matrix from the right half of an augmented matrix [I | A^-1].
     *
     * @param augmented the augmented matrix after Gauss-Jordan elimination
     * @return a new 2D array representing the inverse matrix
     */
    private static double[][] extractInverse(double[][] augmented) {
        int n = augmented.length;
        double[][] inverse = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = augmented[i][j + n];
            }
        }

        return inverse;
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
     * Computes the determinant of a square matrix using Laplace expansion.
     * @param m the input square matrix
     * @return the determinant value
     * @throws IllegalArgumentException if the matrix is not square
     */
    public static double determinant(Matrix m) {
        if (!m.isSquare()) {
            throw new IllegalArgumentException("Determinant is only defined for square matrices.");
        }

        int n = m.getNumRow();
        double[][] data = m.getData();

        if (n == 1) return data[0][0];
        if (n == 2) return data[0][0] * data[1][1] - data[0][1] * data[1][0];

        double det = 0.0;
        for (int col = 0; col < n; col++) {
            double sign = (col % 2 == 0) ? 1 : -1;
            Matrix minor = getMinor(m, col);
            det += sign * data[0][col] * determinant(minor);
        }

        return det;
    }

    /**
     * Returns the minor of a matrix by removing the specified row and column.
     * @param matrix the original matrix
     * @param colToRemove the column to remove
     * @return the minor matrix
     */
    private static Matrix getMinor(Matrix matrix, int colToRemove) {
        int size = matrix.getNumRow();
        double[][] original = matrix.getData();
        double[][] minor = new double[size - 1][size - 1];

        int r = 0;
        for (int i = 0; i < size; i++) {
            if (i == 0) continue;
            int c = 0;
            for (int j = 0; j < size; j++) {
                if (j == colToRemove) continue;
                minor[r][c] = original[i][j];
                c++;
            }
            r++;
        }

        return new Matrix(minor);
    }

    /**
     * Computes the Reduced Row Echelon Form (RREF) of a matrix using Gauss-Jordan elimination.
     *
     * @param m the matrix to reduce
     * @return a new matrix in RREF
     */
    public static Matrix rref(Matrix m) {
        double[][] data = m.copy().getData();
        int rows = data.length;
        int cols = data[0].length;
        int lead = 0;

        for (int r = 0; r < rows; r++) {
            if (lead >= cols) break;

            int i = r;
            while (Math.abs(data[i][lead]) < 1e-10) {
                i++;
                if (i == rows) {
                    i = r;
                    lead++;
                    if (lead == cols) return new Matrix(data);
                }
            }

            swapRows(data, r, i);
            normalizeRow(data, r, lead);
            eliminateOtherRows(data, r, lead);

            lead++;
        }

        return new Matrix(data);
    }

    /**
     * Swaps two rows in a matrix.
     */
    private static void swapRows(double[][] data, int row1, int row2) {
        double[] temp = data[row1];
        data[row1] = data[row2];
        data[row2] = temp;
    }

    /**
     * Normalizes a row so that the pivot column has value 1.
     */
    private static void normalizeRow(double[][] data, int row, int pivotCol) {
        double pivot = data[row][pivotCol];
        int cols = data[0].length;
        for (int j = 0; j < cols; j++) {
            data[row][j] /= pivot;
        }
    }

    /**
     * Eliminates the pivot column values in all rows except the pivot row.
     */
    private static void eliminateOtherRows(double[][] data, int pivotRow, int pivotCol) {
        int rows = data.length;
        int cols = data[0].length;
        for (int i = 0; i < rows; i++) {
            if (i != pivotRow) {
                double factor = data[i][pivotCol];
                for (int j = 0; j < cols; j++) {
                    data[i][j] -= factor * data[pivotRow][j];
                }
            }
        }
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
