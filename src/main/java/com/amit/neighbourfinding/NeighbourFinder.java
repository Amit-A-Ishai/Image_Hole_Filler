package com.amit.neighbourfinding;

import java.util.List;

public interface NeighbourFinder {
    List<int[]> find(int i, int j);
    List<int[]> directionRelativeClockwiseOrderedNeighbours(int curI, int curJ, int dirI, int dirJ, float[][] matrix);
}
