package com.amit;

import com.amit.model.Point;
import com.amit.neighbourfinding.NeighbourFinder;

import java.util.ArrayList;

public class ImageProcessing {

    public enum PixelConnectivity { FOUR, EIGHT }

    public static ArrayList<Point> findInnerContour(float[][] matrix, Point start, InnerBodyDefinition innerBodyDefinition,
                                                    NeighbourFinder neighbourFinder) throws IllegalArgumentException {

        if(!innerBodyDefinition.belongs(start, matrix)){
            throw new IllegalArgumentException("Start pixel doesn't belong to inner body!");
        }

        ArrayList<Point> innerBoundary = new ArrayList<>();
        ImageProcessing.findInnerContour(matrix, start, start, 0, 1, innerBoundary,innerBodyDefinition, neighbourFinder);
        return innerBoundary;
    }

    // TODO -
    //  1. ASSUMING THE SHAPE IS CLOSED!
    //  2. DOESN'T CORRECTLY HANDLE 1-PIXEL-WIDE BRIDGES
    private static void findInnerContour(float [][] matrix, Point start, Point cur, int dirI, int dirJ,
                                        ArrayList<Point> contour, InnerBodyDefinition innerBodyDefinition, NeighbourFinder neighbourFinder) {

        if(contour.size() > 0 && start.equals(cur)){
            // Finished, currently purposely disregarding edge case of starting at 1-pixel-wide bridges
            return;
        }

        contour.add(cur);

        for(Point neighbour : neighbourFinder.findClockwiseNeighboursFromRelativeLeft(cur, dirI, dirJ)){

            // TODO add memoization to not re-traverse pixels
            if(innerBodyDefinition.belongs(neighbour, matrix)){
                findInnerContour(matrix, start, neighbour, neighbour.getRow() - cur.getRow(), neighbour.getCol() - cur.getCol(),
                        contour, innerBodyDefinition, neighbourFinder);
                break;
            }

        }
    }

}
