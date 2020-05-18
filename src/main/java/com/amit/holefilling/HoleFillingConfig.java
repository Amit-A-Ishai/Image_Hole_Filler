package com.amit.holefilling;

import com.amit.ImageProcessing;
import com.amit.pixelweightcalculation.EuclidianPixelWeightCalculator;
import com.amit.pixelweightcalculation.PixelWeightCalculator;

public class HoleFillingConfig {

    public enum DetectionAlgorithm { NAIVE, BFS_AND_FIND_CONTOUR }

    private final static int Z_DEFAULT = 18;
    private final static float EPSILON_DEFAULT = 0.00001f;

    private DetectionAlgorithm detectionAlgorithm = DetectionAlgorithm.NAIVE;
    private HoleFiller holeFiller = new NaiveHoleFiller();
    private ImageProcessing.PixelConnectivity pixelConnectivity = ImageProcessing.PixelConnectivity.FOUR;
    private float holeValue = -1; // default
    private PixelWeightCalculator pixelWeightCalculator = new EuclidianPixelWeightCalculator(Z_DEFAULT, EPSILON_DEFAULT);
    private float colorScaleFactor = 255f;

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
