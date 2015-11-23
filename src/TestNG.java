import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;


/**
 * Created by Franky on 2015/10/11.
 */
public class TestNG {
    WebDriver driver;

    @DataProvider
    public Object[][] testData(){
        return new Object[][]{
                {"160", "45", "17.6", "Underweight"},
                {"168", "70", "24.8", "Normal"},
                {"181", "89", "27.2", "Overweight"},
                {"178", "100", "31.6", "Obesity"},
        };
    }

    @BeforeTest
    public void setup(){
        File file = new File("e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
    }

    @Test(dataProvider = "testData")
    public void testBMICalculator(String height, String weight, String bmi, String bmiCategory){
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
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}

