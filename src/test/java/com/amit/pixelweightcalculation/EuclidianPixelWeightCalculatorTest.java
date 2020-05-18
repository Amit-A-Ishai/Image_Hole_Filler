package com.amit.pixelweightcalculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EuclidianPixelWeightCalculatorTest {

    @org.junit.jupiter.api.Test
    public void testCalculate() {
        EuclidianPixelWeightCalculator calculator = new EuclidianPixelWeightCalculator(1, 0);
        float weight = calculator.calculate(0,0, 2, 2);
        assertEquals(weight, (float)(1/Math.sqrt(8)));
    }
}