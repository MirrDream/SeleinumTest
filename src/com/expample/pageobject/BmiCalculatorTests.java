package com.expample.pageobject;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by yujun on 2015/10/13.
 */
public class BmiCalculatorTests {
    @Test
    public void testBmiCalculatorTests(){
        BmiCalculatePage bmiCalculatePage = new BmiCalculatePage();
        bmiCalculatePage.load();

        bmiCalculatePage.calculateBmi("180", "80");
        assertEquals("24.7", bmiCalculatePage.getBmi());
        assertEquals("Normal", bmiCalculatePage.getBmiCategory());

        bmiCalculatePage.close();
    }
}
