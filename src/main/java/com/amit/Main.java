package com.amit;

import com.amit.holefilling.HoleFillingConfig;
import com.amit.holefilling.NaiveHoleFiller;
import com.amit.matoperations.MatOperation;
import com.amit.matoperations.SaveImage;
import com.amit.matoperations.ShowImage;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Reading the Image from the file
        String file = "C:\\Users\\Amit Ishai\\Desktop\\z1.jpg";
        String maskFile = "C:\\Users\\Amit Ishai\\Desktop\\zm4.bmp";

        HoleFillingConfig holeFillingConfig = new HoleFillingConfig()
                .setPixelConnectivity(ImageProcessing.PixelConnectivity.EIGHT)
                .setDetectionAlgorithm(HoleFillingConfig.DetectionAlgorithm.BFS_AND_FIND_CONTOUR)
                .setHoleFiller(new NaiveHoleFiller());

        List<MatOperation> matOperationList = new ArrayList();
        matOperationList.add(new SaveImage("C:\\Users\\Amit Ishai\\Desktop\\z1HoleFilledNaive.jpg"));
        matOperationList.add(new ShowImage("NAIVE YO YO DOS YO"));

        ImageMender.fillHole(file, maskFile, holeFillingConfig, matOperationList);
    }

}
