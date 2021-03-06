import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by yujun on 2015/10/12.
 */
@RunWith(value = Parameterized.class)
public class CVSData {
    private String height;
    private String weight;
    private String bmi;
    private String bmiCategory;
    WebDriver driver;

    public CVSData(String height, String weight, String bmi, String bmiCategory){
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmiCategory = bmiCategory;
    }

    @Parameterized.Parameters
    public static Collection<String[]> testData(){
        return getTestData("e:\\a.csv");
    }

    public static Collection<String[]> getTestData(String dataPath){
        List<String[]> records = new ArrayList<>();
        String row;
        try{
            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            while ((row = br.readLine()) != null){
                String fields[] = row.split(",");
                records.add(fields);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return records;
    }


    @Before
    public void setUp(){
        File file = new File("e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
    }

    @Test
    public void testBMICalcultaor(){
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

    @After
    public void tearDown(){
        driver.close();
    }
}
