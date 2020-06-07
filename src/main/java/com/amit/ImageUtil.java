package com.amit;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Utility class for image loading, saving, pre and post processing
 * */
public class ImageUtil {

    private static final int CV_TYPE = CvType.CV_8UC1;

    public static Mat load(String filename){
        Mat mat = Imgcodecs.imread(filename);

        // TODO error handling

        return mat;
    }

    public static boolean save(String filename, Mat mat){
        return Imgcodecs.imwrite(filename, mat);
    }

    public static Mat loadRGBAsGreyScale(String filename){
        Mat rgb = load(filename);
        Mat greyscale = new Mat(rgb.height(), rgb.width(), CV_TYPE);
        Imgproc.cvtColor(rgb, greyscale, Imgproc.COLOR_RGB2GRAY);
        return greyscale;
    }
    
    public static Mat matrixToMat(float [][] matrix, float colorScaleFactor){
        float [] asArray = ArrayUtil.flatten2Dto1D(matrix);

        // TODO find better solution for all these conversions!
        double [] f = new double[asArray.length];

        for(int i = 0; i < asArray.length; i++){
            f[i] = asArray[i] * colorScaleFactor;
        }

        Mat mat = new Mat(matrix.length, matrix[0].length, CV_TYPE);
        mat.put(0, 0, f);
        return mat;
    }

    /**
     * Carve a hole out of the image, and scale
     *
     * @param image a grey scale input image
     * @param mask hole mask (hole values are denoted by a 0 value. As a side note,
     *             some image creating programs (e.g., Microsoft Paint) do not use an exact 0 while coloring,
     *             hence these values are taken with an empirical tolerance.)
     * @param holeValue the value to fill holes with
     * @param scaleFactor the scale factor for the greyscale values
     * */
    public static float[][] carveHole(Mat image, Mat mask, float holeValue, float scaleFactor){

        assert Comparisons.sizesEqual(image.size(), mask.size()) : "Image size doesn't equal mask size";

        float [][] ans = new float[image.rows()][image.cols()];

        for(int i = 0; i < image.rows(); i++){
            for(int j = 0; j < image.cols(); j++){

                if(isHole(mask.get(i, j))){
                    ans[i][j] = holeValue;
                } else {
                    // Normalize to 0 - 1 range
                    ans[i][j] = (float) (image.get(i, j)[0] * scaleFactor);
                }

            }
        }

        return ans;
    }

    // TODO can be customized
    // TODO For some reason, when creating images with "Paint", it shades on the edges with values close to 0, rather than exact 0
    private static boolean isHole(double[] colors) {
        return colors[0] < 10;
    }
}
