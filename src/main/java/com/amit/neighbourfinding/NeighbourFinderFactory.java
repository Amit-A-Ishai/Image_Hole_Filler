package com.amit.neighbourfinding;

import com.amit.ImageProcessing;

/**
 * Factor for creating a neighbour finder
 * */
public class NeighbourFinderFactory {

    public static NeighbourFinder create(ImageProcessing.PixelConnectivity pixelConnectivity, int height, int width) {

        switch (pixelConnectivity) {
            case EIGHT: return new Connectivity8NeighbourFinder(height, width);
            default: return new Connectivity4NeighbourFinder(height, width);
        }

    }

}
