package com.amit.holedetection;

import com.amit.model.Hole;
import com.amit.model.Point;
import com.amit.neighbourfinding.NeighbourFinder;

/**
 * Interface for finding a hole in an image
 * Current work assumption is that there is only one hole per image
 * */
public abstract class HoleDetector {

    // Value representing a pixel belonging to hole
    public final float holeValue;

    protected HoleDetector(float holeValue) {
        this.holeValue = holeValue;
    }

    /**
     * Detect the hole in an image.
     *
     * @param matrix the image data
     * @param neighbourFinder
     *
     * @return The found hole
     * */
    public abstract Hole detect(float[][] matrix, NeighbourFinder neighbourFinder);

    /**
     * Tests whether a pixel belongs to hole
     * */
    protected boolean isHoleBody(Point point, float[][] matrix) {
        return isHoleBody(point.getRow(), point.getCol(), matrix);
    }

    protected boolean isHoleBody(int row, int col, float[][] matrix) {
        return matrix[row][col] == holeValue;
    }
}
