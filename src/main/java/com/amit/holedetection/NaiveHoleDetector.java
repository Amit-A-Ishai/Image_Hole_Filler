package com.amit.holedetection;

import com.amit.model.Hole;
import com.amit.neighbourfinding.NeighbourFinder;

import java.util.ArrayList;

public class NaiveHoleDetector extends HoleDetector {

    public NaiveHoleDetector(float holeValue) {
        super(holeValue);
    }

    @Override
    public Hole detect(float [][] matrix, NeighbourFinder neighbourFinder) {

        ArrayList<int[]> boundary = new ArrayList<>();
        ArrayList<int[]> body = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){

                if(isHoleBody(i, j, matrix)){
                    body.add(new int[]{i, j});
                } else if (isBoundary(i, j, matrix, neighbourFinder)){
                    boundary.add(new int[]{i, j});
                }

            }
        }

        return new Hole(boundary, body);
    }

    private boolean isBoundary(int i, int j, float[][] matrix, NeighbourFinder neighbourFinder) {

        if(isHoleBody(i, j, matrix)){
            // TODO raise exception
            return false;
        }

        for(int [] neighbour : neighbourFinder.find(i, j)){
            if(isHoleBody(neighbour[0], neighbour[1], matrix)){
                return true;
            }
        }

        return false;
    }
}
