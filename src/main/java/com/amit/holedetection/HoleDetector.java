package com.amit.holedetection;

import com.amit.model.Hole;
import com.amit.neighbourfinding.NeighbourFinder;

public abstract class HoleDetector {

    public final float holeValue;

    protected HoleDetector(float holeValue) {
        this.holeValue = holeValue;
    }

    public abstract Hole detect(float[][] matrix, NeighbourFinder neighbourFinder);

    protected boolean isHoleBody(int i, int j, float[][] matrix) {
        return matrix[i][j] == holeValue;
    }

}
