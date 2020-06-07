package com.amit;

import com.amit.model.Point;

/**
 * Interface for defining the 'inside' of a shape.
 * Currently used for finding contours
 * */
public interface InnerBodyDefinition {

    /**
     * Checks if a pixel belongs to the shape body.
     *
     * @param i row
     * @param j col
     * @param matrix image data
     *
     * @return true if belongs, false otherwise
     * */
    boolean belongs(Point point, float[][] matrix);
}
