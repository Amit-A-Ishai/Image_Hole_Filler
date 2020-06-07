package com.amit.holedetection;

import com.amit.holefilling.HoleFillingConfig;

/**
 * A factory for creating Hole Detectors
 * */
public class HoleDetectorFactory {

    public static HoleDetector create(HoleFillingConfig.DetectionAlgorithm holeDetectionAlgorithm, float holeValue) {

        switch (holeDetectionAlgorithm) {
            case BFS_AND_FIND_CONTOUR: return new BFSAndFindContourHoleDetector(holeValue);
            default: return new NaiveHoleDetector(holeValue);
        }

    }

}
