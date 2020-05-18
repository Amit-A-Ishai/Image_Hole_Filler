package com.amit.holefilling;

import com.amit.model.Hole;
import com.amit.pixelweightcalculation.PixelWeightCalculator;

import java.util.ArrayList;

public interface HoleFiller {

    void fill(float[][] matrix, Hole hole, PixelWeightCalculator calculator);

    static float computeHoleValue(float[][] matrix, int[] holeCoordinate, ArrayList<int[]> boundary, PixelWeightCalculator calculator) {

        float weightedColorSum = 0;
        float weightSum = 0;
        float tmpWeight;

        for(int [] boundaryCoordinate : boundary){
            tmpWeight = calculator.calculate(holeCoordinate[0], holeCoordinate[1], boundaryCoordinate[0], boundaryCoordinate[1]);
            weightedColorSum += tmpWeight * matrix[boundaryCoordinate[0]][boundaryCoordinate[1]];
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
