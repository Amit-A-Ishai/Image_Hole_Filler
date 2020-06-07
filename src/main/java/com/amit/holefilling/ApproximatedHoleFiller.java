package com.amit.holefilling;

import com.amit.model.Hole;
import com.amit.model.Point;
import com.amit.pixelweightcalculation.PixelWeightCalculator;

import java.util.ArrayList;

/**
 * An approximation in constant time for the hole filling algorithm.
 * */
public class ApproximatedHoleFiller implements HoleFiller {

    private final int maxPixels;

    public ApproximatedHoleFiller(int maxPixels){

        // TODO if maxPixels <= 0 throw IllegalArgumentException

        this.maxPixels = maxPixels;
    }

    /**
     * Find 'maxpixels' pixels in the border of the hole, equally spaced, and use those to calculate the pixel values.
     * */
    @Override
    public void fill(float[][] matrix, Hole hole, PixelWeightCalculator calculator) {

        ArrayList<Point> boundary = hole.getBoundary();
        ArrayList<Point> resized = new ArrayList<>();
        int size = boundary.size();

        // Already made sure maxPixels > 0
        if(size > maxPixels){

            int [] indices = calculateSpacedIndexes(size, maxPixels);

            for(int i = 0; i < indices.length; i++){
                resized.add(boundary.get(i));
            }

        } else {
            resized = boundary;
        }

        for(Point holeCoordinate : hole.getBody()){
            matrix[holeCoordinate.getRow()][holeCoordinate.getCol()] = HoleFiller.computeHoleValue(matrix, holeCoordinate, resized, calculator);
        }
    }

    public static int[] calculateSpacedIndexes(int listSize, int numElements){

        if(numElements <= 0){
            throw new IllegalArgumentException("Number of elements must be greater than 0");
        }

        // limit numElements
        numElements = Math.min(listSize, numElements);

        float space = (float)listSize/(float)numElements;

        int [] ans = new int[numElements];

        for(int i = 0; i < numElements; i++){
            ans[i] = (int) (i * space);
        }

        return ans;
    }

}
