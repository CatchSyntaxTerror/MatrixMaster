package model;
/**
 * Wrapper class for a mathematical vector represented as a 1D array.
 * Used to structure and pass vector data between components in the
 * linear algebra calculator.
 *
 * Future functionality may include dimension checks, normalization,
 * and convenience methods for common vector operations.
 *
 * @author Youssef Amin
 */
public class Vector {
    private double[] data;

    public Vector(double[] data) {
        this.data = data;
    }

    public double[] getData() {
        return data;
    }

    public int getSize() {
        return data.length;
    }

    // TODO: Add helper methods like dot(), magnitude(), toString(), etc.
}
