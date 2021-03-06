package com.amit;

/**
 * Miscellaneous array operations
 * */
public class ArrayUtil {

    /**
     * Flatten a 2d float array to 1d.
     * */
    public static float[] flatten2Dto1D(float [][] data){
        float [] ans = new float[data.length * data[0].length];
        int rows = data.length;
        int cols = data[0].length;

        for(int i = 0; i < rows; i++){
            System.arraycopy(data[i], 0, ans, i * cols, cols);
        }

        return ans;
    }


}
