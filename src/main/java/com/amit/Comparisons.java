package com.amit;

import org.opencv.core.Size;

public class Comparisons {
    public static boolean sizesEqual(Size size1, Size size2){
        return size1 != null && size2 != null &&
                size1.width == size2.width &&
                size1.height == size2.height;
    }
}
