package com.amit;

import com.amit.model.Point;

/**
 * Inner body definition, that simply checks the pixel value.
 * */
public class NumericInnerBodyDefinition implements InnerBodyDefinition {

    private final float bodyValue;

    public NumericInnerBodyDefinition(float bodyValue) {
        this.bodyValue = bodyValue;
    }

    @Override
    public boolean belongs(Point point, float[][] matrix) {
        return matrix[point.getRow()][point.getCol()] == bodyValue;
    }
}
