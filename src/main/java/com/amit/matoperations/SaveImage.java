package com.amit.matoperations;

import com.amit.ImageUtil;
import org.opencv.core.Mat;

public class SaveImage implements MatOperation {

    private final String savePath;

    public SaveImage(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public void execute(Mat mat) {
        ImageUtil.save(savePath, mat);
    }
}
