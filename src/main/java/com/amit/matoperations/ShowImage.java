package com.amit.matoperations;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

/**
 * Show a Mat on screen
 * */
public class ShowImage implements MatOperation {

    private final String imageTitle;

    public ShowImage(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    @Override
    public void execute(Mat mat) {
        HighGui.imshow(imageTitle, mat);
    }
}
