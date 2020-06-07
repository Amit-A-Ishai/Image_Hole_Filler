package com.amit.pixelweightcalculation;

/**
 * Euclidean-distance-based pixel weight calculator
 * */
public class EuclideanPixelWeightCalculator implements PixelWeightCalculator {

    private int z;
    private float epsilon;

    public EuclideanPixelWeightCalculator(int z, float epsilon) {
        this.z = z;
        this.epsilon = epsilon;
    }

    @Override
    public float calculate(int i1, int j1, int i2, int j2) {
        float distance = euclideanDistance(i1, j1, i2, j2);
        return (float) (1f / (Math.pow(distance, z) + epsilon));
    }

    private float euclideanDistance(int i1, int j1, int i2, int j2) {
        return (float) (Math.sqrt(Math.pow(i2 - i1, 2) + Math.pow(j2 - j1, 2)));
    }
}
