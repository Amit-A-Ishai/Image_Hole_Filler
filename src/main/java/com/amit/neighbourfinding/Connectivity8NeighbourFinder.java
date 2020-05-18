package com.amit.neighbourfinding;

public class Connectivity8NeighbourFinder extends IndexBasedNeighbourFinder {

    private final static int [][] INDEX_MASKS =
            {
                    {0, -1},
                    {-1, -1},
                    {-1, 0},
                    {-1, 1},
                    {0, 1},
                    {1, 1},
                    {1, 0},
                    {1, -1}
            };

    public Connectivity8NeighbourFinder(int height, int width) {
        super(height, width);
    }

    @Override
    protected int getMaskIndexOffsetForRelativeLeft() {
        return 2;
    }

    @Override
    int[][] getNeighbourIndexMasks() {
        return INDEX_MASKS;
    }

}
