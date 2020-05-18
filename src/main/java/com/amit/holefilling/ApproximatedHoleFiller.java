package com.amit.holefilling;

import com.amit.model.Hole;
import com.amit.pixelweightcalculation.PixelWeightCalculator;

import java.util.ArrayList;

public class ApproximatedHoleFiller implements HoleFiller {

    private final int maxPixels;

    public ApproximatedHoleFiller(int maxPixels){
        this.maxPixels = maxPixels;
    }

    @Override
    public void fill(float[][] matrix, Hole hole, PixelWeightCalculator calculator) {

        ArrayList<int[]> boundary = hole.getBoundary();
        ArrayList<int[]> resized = new ArrayList<>();
        int size = boundary.size();

        if(size > maxPixels){

            // TODO play around with the math here, to make it exact
            // Purposely not using (maxPixels - 1) as divisor, since the boundary list is cyclic.
            double skip = ((float)size - 1) / (float)maxPixels;

            for(int i = 0; i < boundary.size() && resized.size() < maxPixels; i += skip){
                resized.add(boundary.get(i));
            }

        }

        for(int [] holeCoordinate : hole.getBody()){
             matrix[holeCoordinate[0]][holeCoordinate[1]] = HoleFiller.computeHoleValue(matrix, holeCoordinate, resized, calculator);
         }
    }

}
