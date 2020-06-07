package com.amit.neighbourfinding;

import com.amit.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for finding the neighbours of a pixel
 * */
public abstract class NeighbourFinder {

    private final int height;
    private final int width;

    protected NeighbourFinder(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public List<Point> find(Point point) {
        return find(point, 0);
    }

    public List<Point> find(Point point, int offset) {
        return find(point.getRow(), point.getCol(), offset);
    }

    public List<Point> find(int row, int col) {
        return find(row, col, 0);
    }

    public List<Point> find(int row, int col, int offset) {

        List<Point> ans = new ArrayList<>();

        int tmpI;
        int tmpJ;

        int[][] indexMasks = getNeighbourIndexMasks();
        int[] indexMask;

        for (int index = 0; index < indexMasks.length; index++) {

            indexMask = indexMasks[(index + offset) % indexMasks.length];

            tmpI = row + indexMask[0];
            tmpJ = col + indexMask[1];

            if (inBounds(tmpI, tmpJ)) {
                ans.add(new Point(tmpI, tmpJ));
            }
        }

        return ans;
    }

    /**
     * Helper function for finding contours.
     * Find the neighbours of a pixel, in clockwise order, starting from 'left' relative to the current direction
     * */
    public List<Point> findClockwiseNeighboursFromRelativeLeft(Point current, int dirI, int dirJ) {
        int indexInMask = findIndexInMasksForDirection(dirI, dirJ);
        int[][] indexMasks = getNeighbourIndexMasks();
        int startIndex = (indexInMask + (indexMasks.length - getMaskIndexOffsetForRelativeLeft())) % indexMasks.length;

        return find(current, startIndex);
    }


    protected int findIndexInMasksForDirection(int dirI, int dirJ) {
        int index = 0;
        for (int[] mask : getNeighbourIndexMasks()) {
            if (mask[0] == dirI && mask[1] == dirJ) {
                return index;
            }

            index++;
        }

        // TODO error handling
        return -1;
    }

    public boolean inBounds(int i, int j) {
        return i >= 0 && i < height && j >= 0 && j < width;
    }

    // Start from left, traverse clockwise
    protected abstract int[][] getNeighbourIndexMasks();

    protected abstract int getMaskIndexOffsetForRelativeLeft();
}
