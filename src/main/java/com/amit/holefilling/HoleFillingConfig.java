package com.amit.holefilling;

import com.amit.ImageProcessing;
import com.amit.pixelweightcalculation.EuclideanPixelWeightCalculator;
import com.amit.pixelweightcalculation.PixelWeightCalculator;

import static com.amit.Constants.*;

/**
 * Configuration object for hole filling
 * */
public class HoleFillingConfig {

    public enum DetectionAlgorithm { NAIVE, BFS_AND_FIND_CONTOUR }

    private DetectionAlgorithm detectionAlgorithm = DEFAULT_DETECTION_ALGORITHM;
    private HoleFiller holeFiller = DEFAULT_HOLE_FILLER;
    private ImageProcessing.PixelConnectivity pixelConnectivity = DEFAULT_PIXEL_CONNECTIVITY;
    private float holeValue = DEFAULT_HOLE_VALUE;
    private PixelWeightCalculator pixelWeightCalculator = new EuclideanPixelWeightCalculator(DEFAULT_Z, DEFAULT_EPSILON);
    private float colorScaleFactor = DEFAULT_COLOR_SCALE_FACTOR;

    public HoleFillingConfig setDetectionAlgorithm(DetectionAlgorithm detectionAlgorithm) {
        this.detectionAlgorithm = detectionAlgorithm;
        return this;
    }

    public HoleFillingConfig setHoleFiller(HoleFiller holeFiller) {
        this.holeFiller = holeFiller;
        return this;
    }

    public HoleFillingConfig setPixelConnectivity(ImageProcessing.PixelConnectivity pixelConnectivity) {
        this.pixelConnectivity = pixelConnectivity;
        return this;
    }

    public HoleFillingConfig setHoleValue(float holeValue) {
        this.holeValue = holeValue;
        return this;
    }

    public HoleFillingConfig setPixelWeightCalculator(PixelWeightCalculator pixelWeightCalculator) {
        this.pixelWeightCalculator = pixelWeightCalculator;
        return this;
    }

    public HoleFillingConfig setColorScaleFactor(float colorScaleFactor) {
        this.colorScaleFactor = colorScaleFactor;
        return this;
    }

    public DetectionAlgorithm getDetectionAlgorithm() {
        return detectionAlgorithm;
    }

    public HoleFiller getHoleFiller() {
        return holeFiller;
    }

    public ImageProcessing.PixelConnectivity getPixelConnectivity() {
        return pixelConnectivity;
    }

    public float getHoleValue() {
        return holeValue;
    }

    public PixelWeightCalculator getPixelWeightCalculator() {
        return pixelWeightCalculator;
    }

    public float getColorScaleFactor() {
        return colorScaleFactor;
    }
}
