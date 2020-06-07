package com.amit.neighbourfinding;

/**
 * Finds the 4-connected neighbours
 * */
public class Connectivity4NeighbourFinder extends NeighbourFinder {

    private final static int [][] INDEX_MASKS =
            {
                    {0, -1},
                    {-1, 0},
                    {0, 1},
                    {1, 0}
            };

    public Connectivity4NeighbourFinder(int height, int width) {
        super(height, width);
    }

    @Override
    protected int getMaskIndexOffsetForRelativeLeft() {
        return 1;
    }

    @Override
    protected int[][] getNeighbourIndexMasks() {
        return INDEX_MASKS;
    }

}
