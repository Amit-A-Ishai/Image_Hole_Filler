package com.amit;

public class NumericInnerBodyDefinition implements InnerBodyDefinition {

    private final float bodyValue;

    public NumericInnerBodyDefinition(float bodyValue) {
        this.bodyValue = bodyValue;
    }

    @Override
    public boolean belongs(int i, int j, float[][] matrix) {
        return matrix[i][j] == bodyValue;
    }
}
