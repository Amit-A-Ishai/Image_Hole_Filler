package com.amit;

import com.amit.neighbourfinding.Connectivity4NeighbourFinder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageProcessingTest {

    @org.junit.jupiter.api.Test
    public void testFindInnerContour() {

        float [][] image = new float[][]{
                {1,1,1,1,1},
                {1,-1,-1,-1,1},
                {1,-1,-1,-1,1},
                {1,-1,-1,-1,1},
                {1,1,1,1,1},
        };

        ArrayList<int[]> innerContour = ImageProcessing.findInnerContour(image, 1, 1,
                new NumericInnerBodyDefinition(-1), new Connectivity4NeighbourFinder(image.length, image[0].length));

        int[][] asArray = innerContour.toArray(new int[innerContour.size()][2]);

        int [][] expected = new int [][] {
                {1, 1},
                {1, 2},
                {1, 3},
                {2, 3},
                {3, 3},
                {3, 2},
                {3, 1},
                {2, 1},

        };

        assertArraysEqual(expected, asArray);
    }

    private void assertArraysEqual(int[][] expected, int[][] actual) {

        assertEquals(expected.length, actual.length);
        assertEquals(expected[0].length, actual[0].length);

        for(int i = 0; i < expected.length; i++){
            assertArrayEquals(expected[i], actual[i]);
        }

    }

}