package model;
/**
 * Wrapper class for a matrix represented as a 2D array of doubles.
 * Provides structure and utility for passing and manipulating matrix
 * data throughout the application.
 *
 * This class will eventually support common operations such as
 * retrieving rows, columns, dimensions, and internal validation.
 *
 * @author Youssef Amin
 */

public class Matrix {
        private double[][] data;

        public Matrix(double[][] data) {
            this.data = data;
        }

        public double[][] getData() {
            return data;
        }

        public int getNumRow() {
            return data.length;
        }

        public int getNumCol() {
            return data[0].length;
        }

        // TODO: Add methods like getRow(), getColumn(), toString(), etc.
    }

