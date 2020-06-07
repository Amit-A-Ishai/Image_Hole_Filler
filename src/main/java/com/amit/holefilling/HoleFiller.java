package com.amit.holefilling;

import com.amit.model.Hole;
import com.amit.model.Point;
import com.amit.pixelweightcalculation.PixelWeightCalculator;

import java.util.ArrayList;

/**
 * Interface for filling a hole in an image
 * */
public interface HoleFiller {

    /**
     * Fill a given hole in an image
     *
     * @param matrix image data
     * @param hole the hole to fill
     * @param calculator the pixel weight calculator
     * */
    void fill(float[][] matrix, Hole hole, PixelWeightCalculator calculator);

    /**
     * Compute the value for a given pixel
     *
     * @param matrix image data
     * @param holeCoordinate the pixel to calculate for
     * @param boundary the boundary pixels to calculate against
     * @param calculator the pixel weight calculator
     *
     * @return the computed pixel value
     * */
    static float computeHoleValue(float[][] matrix, Point holeCoordinate, ArrayList<Point> boundary, PixelWeightCalculator calculator) {

        float weightedColorSum = 0;
        float weightSum = 0;
        float tmpWeight;

        for(Point boundaryCoordinate : boundary){
            tmpWeight = calculator.calculate(holeCoordinate.getRow(), holeCoordinate.getCol(), boundaryCoordinate.getRow(), boundaryCoordinate.getCol());
            weightedColorSum += tmpWeight * matrix[boundaryCoordinate.getRow()][boundaryCoordinate.getCol()];
            weightSum += tmpWeight;
        }

        // Prevent 0 division
        if(weightSum == 0) {
            // TODO throw exception?
            return 0;
        }

        return weightedColorSum / weightSum;
    }

}
