package com.amit.holefilling;

import com.amit.model.Hole;
import com.amit.model.Point;
import com.amit.pixelweightcalculation.PixelWeightCalculator;

/**
 * The naive, O(n^2) implementation for hole filling
 * */
public class NaiveHoleFiller implements HoleFiller {

    /**
     * For each pixel in the hole, compute and assign value
     * */
    @Override
    public void fill(float[][] matrix, Hole hole, PixelWeightCalculator calculator) {
         for(Point holeCoordinate : hole.getBody()){
             matrix[holeCoordinate.getRow()][holeCoordinate.getCol()] = HoleFiller.computeHoleValue(matrix, holeCoordinate, hole.getBoundary(), calculator);
         }
    }

}
