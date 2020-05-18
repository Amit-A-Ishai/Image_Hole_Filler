package com.amit;

import com.amit.neighbourfinding.NeighbourFinder;

import java.util.ArrayList;

public class ImageProcessing {

    public enum PixelConnectivity { FOUR, EIGHT }

    public static ArrayList<int[]> findInnerContour(float[][] matrix, int i, int j, InnerBodyDefinition innerBodyDefinition, NeighbourFinder neighbourFinder) {
        ArrayList<int[]> innerBoundary = new ArrayList<>();
        ImageProcessing.findInnerContour(matrix, i, j, i, j, 0, 1, innerBoundary,innerBodyDefinition, neighbourFinder);
        return innerBoundary;
    }

    public static void findInnerContour(float [][] matrix, int startI, int startJ, int curI, int curJ, int dirI, int dirJ,
                                        ArrayList<int[]> contour, InnerBodyDefinition innerBodyDefinition, NeighbourFinder neighbourFinder) {

        if(contour.size() > 0 && startI == curI && startJ == curJ){
            // Finished, currently purposely disregarding edge case of starting of 1-pixel-width bridges
            return;
        }

        contour.add(new int[]{curI, curJ});

        for(int [] neighbour : neighbourFinder.directionRelativeClockwiseOrderedNeighbours(curI, curJ, dirI, dirJ, matrix)){

            // TODO add memoization to not re-traverse pixels
            if(innerBodyDefinition.belongs(neighbour[0], neighbour[1], matrix)){
                findInnerContour(matrix, startI, startJ, neighbour[0], neighbour[1], neighbour[0] - curI, neighbour[1] - curJ,
                        contour, innerBodyDefinition, neighbourFinder);
                break;
            }

        }
    }

    private static void colorImage(float[][] image, ArrayList<int[]> indices, float color) {
        for(int [] index : indices){
            image[index[0]][index[1]] = color;
        }
    }
}
