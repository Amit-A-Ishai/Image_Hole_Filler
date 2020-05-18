package com.amit.neighbourfinding;

import java.util.ArrayList;
import java.util.List;

public abstract class IndexBasedNeighbourFinder implements NeighbourFinder {

    private final int height;
    private final int width;

    protected IndexBasedNeighbourFinder(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public List<int[]> find(int i, int j) {
        return find(i, j, 0);
    }

    public List<int[]> find(int i, int j, int offset) {

        List<int[]> ans = new ArrayList<>();

        int tmpI;
        int tmpJ;

        int [][] indexMasks = getNeighbourIndexMasks();
        int [] indexMask;

        for(int index = 0; index < indexMasks.length; index++){

            indexMask = indexMasks[(index + offset) % indexMasks.length];

            tmpI = i + indexMask[0];
            tmpJ = j + indexMask[1];

            if(inBounds(tmpI, tmpJ)){
                ans.add(new int[]{tmpI, tmpJ});
            }
        }

        return ans;
    }

    @Override
    public List<int[]> directionRelativeClockwiseOrderedNeighbours(int curI, int curJ, int dirI, int dirJ, float[][] matrix) {
        int indexInMask = findIndexInMasksForDirection(dirI, dirJ);
        int [][] indexMasks = getNeighbourIndexMasks();
        int startIndex = (indexInMask + (indexMasks.length - getMaskIndexOffsetForRelativeLeft())) % indexMasks.length;

        return find(curI, curJ, startIndex);
    }


    private int findIndexInMasksForDirection(int dirI, int dirJ) {
        int index = 0;
        for(int [] mask : getNeighbourIndexMasks()){
            if(mask[0] == dirI && mask[1] == dirJ){
                return index;
            }

            index++;
        }

        // TODO error handling
        return -1;
    }

    private boolean inBounds(int i, int j) {
        return i >= 0 && i < height && j >= 0 && j < width;
    }

    // Start from left, traverse clockwise
    abstract int[][] getNeighbourIndexMasks();

    abstract int getMaskIndexOffsetForRelativeLeft();
}
