package com.amit;

import com.amit.holefilling.HoleFiller;
import com.amit.holefilling.HoleFillingConfig;
import com.amit.holefilling.NaiveHoleFiller;

/**
 * Library constants
 * */
public class Constants {
    public final static float DEFAULT_HOLE_VALUE = -1f;
    public final static float DEFAULT_COLOR_SCALE_FACTOR = 255f;
    public final static int DEFAULT_Z = 18;
    public final static float DEFAULT_EPSILON = 0.00001f;
    public final static int DEFAULT_MAX_PIXELS = 40;
    public final static HoleFillingConfig.DetectionAlgorithm DEFAULT_DETECTION_ALGORITHM = HoleFillingConfig.DetectionAlgorithm.NAIVE;
    public final static ImageProcessing.PixelConnectivity DEFAULT_PIXEL_CONNECTIVITY = ImageProcessing.PixelConnectivity.FOUR;
    public final static HoleFiller DEFAULT_HOLE_FILLER = new NaiveHoleFiller();
    public static final String DEFAULT_OUTPUT_FILE_SUFFIX = "_HOLE_FILLED";
    public static final String IMAGE_WINDOW_TITLE = "Hole Filling Result";
    public static final String NAIVE = "naive";
}
