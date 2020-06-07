package com.amit.holedetection;

import com.amit.model.Hole;
import com.amit.model.Point;
import com.amit.neighbourfinding.NeighbourFinder;

import java.util.ArrayList;

/**
 * Naive implementation of hole detection
 *
 * For each pixel in the image:
 * If it belongs to a hole, add to hole.
 * Else, if it touches a hole pixel, add it to the boundary
 *
 * */
public class NaiveHoleDetector extends HoleDetector {

    public NaiveHoleDetector(float holeValue) {
        super(holeValue);
    }

    @Override
    public Hole detect(float [][] matrix, NeighbourFinder neighbourFinder) {

        ArrayList<Point> boundary = new ArrayList<>();
        ArrayList<Point> body = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){

                if(isHoleBody(i, j, matrix)){
                    body.add(new Point(i, j));
                } else if (isBoundary(i, j, matrix, neighbourFinder)){
                    boundary.add(new Point(i, j));
                }

            }
        }

        return new Hole(boundary, body);
    }

    /**
     * Checks if a pixel belongs to the hole boundary, meaning:
     * The pixel is not a hole, but has a neighbour that is.
     * */
    private boolean isBoundary(int row, int col, float[][] matrix, NeighbourFinder neighbourFinder) {

        if(isHoleBody(row, col, matrix)){
            // TODO raise exception
            return false;
        }

        for(Point neighbour : neighbourFinder.find(row, col)){
            if(isHoleBody(neighbour, matrix)){
                return true;
            }
        }

        return false;
    }
}
