package com.amit;

import com.amit.holedetection.HoleDetector;
import com.amit.holedetection.HoleDetectorFactory;
import com.amit.holefilling.HoleFillingConfig;
import com.amit.matoperations.MatOperation;
import com.amit.model.Hole;
import com.amit.neighbourfinding.NeighbourFinder;
import com.amit.neighbourfinding.NeighbourFinderFactory;
import org.opencv.core.Mat;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The library main entry point
 * */
public class ImageManipulation {

    static {
        nu.pattern.OpenCV.loadShared();
    }

    public static void fillHole(String imageFile, String maskFile, HoleFillingConfig config, List<MatOperation> postProcessOperations) throws FileNotFoundException {
        Mat imageGreyscale = ImageUtil.loadRGBAsGreyScale(imageFile);
        Mat maskGreyscale = ImageUtil.loadRGBAsGreyScale(maskFile);

        if(imageGreyscale.empty() || maskGreyscale.empty()){
            throw new FileNotFoundException("Pic or mask are empty or weren't found");
        }

        assert Comparisons.sizesEqual(imageGreyscale.size(), maskGreyscale.size()) : "Image size doesn't match mask size";

        // TODO maybe should have used a 1d array to avoid flattening?
        // Could have used special object to encapsulate
        float [][] matrix = ImageUtil.carveHole(imageGreyscale, maskGreyscale, config.getHoleValue(), (1f / config.getColorScaleFactor()));

        fillHole(matrix, config, postProcessOperations);
    }

    public static void fillHole(float [][] matrix) {
        fillHole(matrix, new HoleFillingConfig(), new ArrayList<>());
    }

    public static void fillHole(float [][] matrix, HoleFillingConfig config) {
        fillHole(matrix, config, new ArrayList<>());
    }

    /**
     * Fill a hole in an image
     *
     * @param matrix image data - should be float values between 0 and 1, or (generally) -1, denoting a hole
     * @param config Hole filling config
     * @param postProcessOperations A list of additional operations to be performed on the resulting image
     * */
    public static void fillHole(float [][] matrix, HoleFillingConfig config, List<MatOperation> postProcessOperations) {
        // TODO Error handling: matrix == null? matrix is empty?

        HoleDetector holeDetector = HoleDetectorFactory.create(config.getDetectionAlgorithm(), config.getHoleValue());
        NeighbourFinder neighbourFinder = NeighbourFinderFactory.create(config.getPixelConnectivity(), matrix.length, matrix[0].length);

        Hole hole = holeDetector.detect(matrix, neighbourFinder);

        config.getHoleFiller().fill(matrix, hole, config.getPixelWeightCalculator());

        Mat holeFilledImage = ImageUtil.matrixToMat(matrix, config.getColorScaleFactor());

        for(MatOperation matOperation : postProcessOperations){
            matOperation.execute(holeFilledImage);
        }
    }

}
