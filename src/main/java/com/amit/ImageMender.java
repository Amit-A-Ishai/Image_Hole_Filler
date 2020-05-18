package com.amit;

import com.amit.holedetection.BFSAndFindContourHoleDetector;
import com.amit.holedetection.HoleDetector;
import com.amit.holedetection.NaiveHoleDetector;
import com.amit.holefilling.HoleFillingConfig;
import com.amit.matoperations.MatOperation;
import com.amit.model.Hole;
import com.amit.neighbourfinding.Connectivity4NeighbourFinder;
import com.amit.neighbourfinding.Connectivity8NeighbourFinder;
import com.amit.neighbourfinding.NeighbourFinder;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.List;

public class ImageMender {

    static {
        nu.pattern.OpenCV.loadShared();
//        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    }

    public static void fillHole(String imageFile, String maskFile, HoleFillingConfig config, List<MatOperation> postProcessOperations) {
        // TODO Error handling: matrix == null? matrix is empty?

        Mat imageGreyscale = ImageUtil.loadRGBAsGreyScale(imageFile);
        Mat maskGreyscale = ImageUtil.loadRGBAsGreyScale(maskFile);

        if(imageGreyscale.empty() || maskGreyscale.empty()){
            System.out.println("Pic or mask are empty");
            System.exit(-1);
        }

        assert Comparisons.sizesEqual(imageGreyscale.size(), maskGreyscale.size()) : "Image size doesn't match mask size";

        float [][] matrix = ImageUtil.carveHole(imageGreyscale, maskGreyscale, config.getHoleValue(), (1f / config.getColorScaleFactor()));

        fillHole(matrix, config, postProcessOperations);
     }

    public static void fillHole(float [][] matrix, HoleFillingConfig config, List<MatOperation> postProcessOperations) {
        // TODO Error handling: matrix == null? matrix is empty?

        HoleDetector holeDetector = createHoleDetector(config.getDetectionAlgorithm(), config.getHoleValue());
        NeighbourFinder neighbourFinder = createNeighbourFinder(config.getPixelConnectivity(), matrix.length, matrix[0].length);

        Hole hole = holeDetector.detect(matrix, neighbourFinder);

        config.getHoleFiller().fill(matrix, hole, config.getPixelWeightCalculator());

        Mat holeFilledImage = ImageUtil.matrixToMat(matrix, config.getColorScaleFactor());

        for(MatOperation matOperation : postProcessOperations){
            matOperation.execute(holeFilledImage);
        }
    }

    private static NeighbourFinder createNeighbourFinder(ImageProcessing.PixelConnectivity pixelConnectivity, int height, int width) {

        switch (pixelConnectivity) {
            case FOUR: return new Connectivity4NeighbourFinder(height, width);
            case EIGHT: return new Connectivity8NeighbourFinder(height, width);
            default: return new Connectivity4NeighbourFinder(height, width);
        }

    }

    private static HoleDetector createHoleDetector(HoleFillingConfig.DetectionAlgorithm holeDetectionAlgorithm, float holeValue) {

        switch (holeDetectionAlgorithm){
            case BFS_AND_FIND_CONTOUR: return new BFSAndFindContourHoleDetector(holeValue);
            default: return new NaiveHoleDetector(holeValue);
        }

    }

}
