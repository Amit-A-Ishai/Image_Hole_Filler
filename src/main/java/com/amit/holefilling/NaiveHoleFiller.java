package com.amit.holefilling;

import com.amit.model.Hole;
import com.amit.pixelweightcalculation.PixelWeightCalculator;

public class NaiveHoleFiller implements HoleFiller {

    @Override
    public void fill(float[][] matrix, Hole hole, PixelWeightCalculator calculator) {
         for(int [] holeCoordinate : hole.getBody()){
             matrix[holeCoordinate[0]][holeCoordinate[1]] = HoleFiller.computeHoleValue(matrix, holeCoordinate, hole.getBoundary(), calculator);
         }
    }

}
