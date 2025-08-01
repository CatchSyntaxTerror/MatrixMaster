package model;

import java.util.Arrays;

/**
 * Wrapper class for a matrix represented as a 2D array of doubles.
 * Provides structure and utility for passing and manipulating matrix
 * data throughout the application.
 *
 * This class supports retrieving rows, columns, dimensions, cloning,
 * and string representation for easy testing and debugging.
 *
 * @author Youssef Amin
 */
public class Matrix {

    private final double[][] data;

    public Matrix(double[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0)
            throw new IllegalArgumentException("Matrix cannot be null or empty.");

        int colLength = data[0].length;
        for (double[] row : data) {
            if (row.length != colLength)
                throw new IllegalArgumentException("All rows must have the same number of columns.");
        }

        this.data = new double[data.length][colLength];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = Arrays.copyOf(data[i], colLength);
        }
    }

    /**
     * @return a copy of the matrix
     */
    public double[][] getData() {
        double[][] copy = new double[getNumRow()][getNumCol()];
        for (int i = 0; i < getNumRow(); i++) {
            copy[i] = Arrays.copyOf(data[i], getNumCol());
        }
        return copy;
    }

    /**
     * @return Number of rows in the matrix
     */
    public int getNumRow() {
        return data.length;
    }

    /**
     * @return Number of columns in the matrix
     */
    public int getNumCol() {
        return data[0].length;
    }

    /**
     * @param rowIndex the index of the row you would like to get
     * @return the row associated with the given index
     */
    public double[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= getNumRow()) throw new IndexOutOfBoundsException("Invalid row index.");
        return Arrays.copyOf(data[rowIndex], getNumCol());
    }

    /**
     * @param colIndex the index of the column you would like to get
     * @return a copy of the column as a 1d array
     */
    public double[] getColumn(int colIndex) {
        if (colIndex < 0 || colIndex >= getNumCol()) throw new IndexOutOfBoundsException("Invalid column index.");
        double[] column = new double[getNumRow()];
        for (int i = 0; i < getNumRow(); i++) {
            column[i] = data[i][colIndex];
        }
        return column;
    }

    /**
     * makes a copy of the matrix
     * @return a new matrix object with the same content as the original matrix
     */
    public Matrix copy() {
        return new Matrix(getData());
    }

    /**
     * Checks if the matrix in question is a square matrix
     * @return true if number of rows equals number of columns
     */
    public boolean isSquare() {
        return getNumRow() == getNumCol();
    }

    /**
     * checks if the two matrices are equivalent
     * @param o the object this is to be compared too
     * @return true if the same object or same contents
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix other)) return false;
        if (getNumRow() != other.getNumRow() || getNumCol() != other.getNumCol()) return false;

        for (int i = 0; i < getNumRow(); i++) {
            if (!Arrays.equals(data[i], other.data[i])) return false;
        }
        return true;
    }

    /**
     * simple toString
     * @return a string representation of the matrix
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] row : data) {
            sb.append(Arrays.toString(row)).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
