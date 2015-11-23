import org.junit.runners.Parameterized.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Franky on 2015/10/11.
 */
@RunWith(value = Parameterized.class)
public class simpleDDT {
    private String height;
    private String weight;
    private String bmi;
    private String bmiCategory;
    WebDriver driver;

    @Parameters
    public static Collection testDate(){
        return Arrays.asList(new Object[][]{
                {"160", "45", "17.6", "Underweight"},
                {"168", "70", "24.8", "Normal"},
                {"181", "89", "27.2", "Overweight"},
                {"178", "100", "31.6", "Obesity"},
        });
    }

    public simpleDDT(String height, String weight, String bmi, String bmiCategory){
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmiCategory = bmiCategory;
    }


    @Before
    public void setUp() {
        File file = new File("e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
    }


    @Test
    public void testBMICalculator() throws Exception{
        driver.get("http://127.0.0.1:5000/BMI");
        WebElement heightField = driver.findElement(By.name("height"));
        heightField.sendKeys(height);
        WebElement weightField = driver.findElement(By.name("weight"));
        weightField.sendKeys(weight);
        WebElement calcBtn = driver.findElement(By.id("Calculate"));
        calcBtn.click();
        WebElement bmiField = driver.findElement(By.name("BMI"));
        assertEquals(bmi, bmiField.getAttribute("value"));
        WebElement bmiCategoryNum = driver.findElement(By.name("BMICategory"));
        assertEquals(bmiCategory, bmiCategoryNum.getText());
        WebElement upFile = driver.findElement(By.id("upFile"));
        upFile.sendKeys("e:\\Backup\\gitrepo\\learngit\\readme.txt");
        System.out.println(upFile.getTagName());
        System.out.println(upFile.getText());
        Thread.sleep(10000);
        upFile.click();
        Thread.sleep(20000);
    }


    @After
    public void tearDown(){
        driver.close();
    }

}
