package com.amit;

import com.amit.holefilling.ApproximatedHoleFiller;
import com.amit.holefilling.HoleFiller;
import com.amit.holefilling.HoleFillingConfig;
import com.amit.holefilling.NaiveHoleFiller;
import com.amit.matoperations.MatOperation;
import com.amit.matoperations.SaveImage;
import com.amit.matoperations.ShowImage;
import com.amit.pixelweightcalculation.EuclideanPixelWeightCalculator;
import org.opencv.highgui.HighGui;
import picocli.CommandLine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.amit.Constants.*;

/**
 * Main method, acts as the cli
 * */

@CommandLine.Command(description = "Fills a hole in an image and writes the result",
        name = "Image Hole Filling", mixinStandardHelpOptions = true, version = "Image Hole Filling 1.0")
public class Main implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Input image path")
    private String inputImagePath;

    @CommandLine.Parameters(index = "1", description = "Hole mask path. The hole is denoted by black pixels")
    private String inputMaskPath;

    @CommandLine.Parameters(index = "2", description = "z for pixel weight calculation")
    private int z;

    @CommandLine.Parameters(index = "3", description = "epsilon for pixel weight calculation")
    private float epsilon;

    @CommandLine.Parameters(index = "4", description = "Pixel connectivity")
    private int pixelConnectivity;

    @CommandLine.Option(names = {"-o", "--output"}, description = "Output path")
    private String outputPath = "";

    @CommandLine.Option(names = {"-hd", "--holeDetection"}, description = "naive, bfs")
    private String holeDetectionAlg = NAIVE;

    @CommandLine.Option(names = {"-hf", "--holeFillingAlg"}, description = "naive, approx")
    private String holeFillingAlg = NAIVE;

    @CommandLine.Option(names = {"-mp", "--maxPixels"}, description = "Max pixels for approximation hole filling algorithm")
    private int maxPixels = DEFAULT_MAX_PIXELS;

    @CommandLine.Option(names = {"-cs", "--colorScale"}, description = "Color Scale Factor")
    private float colorScaleFactor = DEFAULT_COLOR_SCALE_FACTOR;

    @CommandLine.Option(names = {"-hv", "--holeValue"}, description = "Hole Value")
    private float holeValue = DEFAULT_HOLE_VALUE;

    @CommandLine.Option(names = "-s", description = "Show result")
    boolean view;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {

        if(outputPath.isEmpty()){
            outputPath = generateOutputPath(inputImagePath);
        }

        HoleFillingConfig holeFillingConfig = generateHoleFillingConfig();
        List<MatOperation> matOperationList = new ArrayList();
        matOperationList.add(new SaveImage(outputPath));

        if(view){
            matOperationList.add(new ShowImage(IMAGE_WINDOW_TITLE));
        }

        try{
            ImageManipulation.fillHole(inputImagePath, inputMaskPath, holeFillingConfig, matOperationList);
        } catch (FileNotFoundException e){
            System.exit(-1);
        }

        if(view){
            HighGui.waitKey();
        }

        return 0;
    }

    private String generateOutputPath(String inputPath) {
        StringBuilder stringBuilder = new StringBuilder();

        int lastPeriodInString = inputPath.lastIndexOf(".");

        if(lastPeriodInString < 0){
            throw new IllegalArgumentException("Invalid input file name: " + inputPath);
        }

        stringBuilder.append(inputPath.substring(0, lastPeriodInString));
        stringBuilder.append(DEFAULT_OUTPUT_FILE_SUFFIX);
        stringBuilder.append(inputPath.substring(lastPeriodInString));

        return stringBuilder.toString();
    }

    // TODO add switches
    private HoleFillingConfig generateHoleFillingConfig() {
        ImageProcessing.PixelConnectivity pc = pixelConnectivity == 4 ?
                ImageProcessing.PixelConnectivity.FOUR : ImageProcessing.PixelConnectivity.EIGHT;

        EuclideanPixelWeightCalculator calculator = new EuclideanPixelWeightCalculator(z, epsilon);

        HoleFillingConfig.DetectionAlgorithm da = holeDetectionAlg.equals(NAIVE) ?
                HoleFillingConfig.DetectionAlgorithm.NAIVE : HoleFillingConfig.DetectionAlgorithm.BFS_AND_FIND_CONTOUR;

        HoleFiller holeFiller = holeFillingAlg.equals(NAIVE) ? new NaiveHoleFiller() : new ApproximatedHoleFiller(maxPixels);

        HoleFillingConfig holeFillingConfig = new HoleFillingConfig()
                .setPixelConnectivity(pc)
                .setDetectionAlgorithm(da)
                .setPixelWeightCalculator(calculator)
                .setColorScaleFactor(colorScaleFactor)
                .setHoleValue(holeValue)
                .setHoleFiller(holeFiller);

        return holeFillingConfig;
    }
}
