import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by yujun on 2015/11/5.
 */

@RunWith(value = Parameterized.class)
public class TestPhantomjs {
    private String height;
    private String weight;
    private String bmi;
    private String bmiCategory;
    WebDriver driver;
    String Url = "http://127.0.0.1:5000/BMI";

    @Parameterized.Parameters
    public static Collection testData(){
        return Arrays.asList(new Object[][]{
                {"160", "45", "17.6", "Underweight"},
                {"168", "70", "24.8", "Normal"},
                {"181", "89", "27.2", "Overweight"},
                {"178", "100", "31.8", "Obesity"},
        });
    }

    public TestPhantomjs(String height, String weight, String bmi, String bmiCategory){
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmiCategory = bmiCategory;
    }

    @Before
    public void setup() throws IOException{
        String path = "e:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path);
        this.driver = new PhantomJSDriver(caps);

    }

    @Test
    public void testPhantojs(){
        driver.get(Url);
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

    }

    @After
    public void tearDown(){
        driver.close();
    }


}
