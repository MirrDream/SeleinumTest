import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by yujun on 2015/9/2.
 */

public class TestBaidu {
    WebDriver driver;

    public static void setAttribute(WebElement element, String attributeName, String value){
        WrapsDriver wrapsDriver = (WrapsDriver)element;
        JavascriptExecutor driver =  (JavascriptExecutor)wrapsDriver.getWrappedDriver();
        driver.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attributeName, value );
    }


    public static void highlightElement(WebElement element){
        WrapsDriver wrapsDriver = (WrapsDriver)element;
        JavascriptExecutor driver = (JavascriptExecutor)wrapsDriver.getWrappedDriver();
        driver.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "color:green; border: 2px solid yellow;");

        try{
            Thread.sleep(800);
            driver.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "");
            Thread.sleep(800);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static File captureElement(WebElement element) throws Exception{
        WrapsDriver wrapsDriver = (WrapsDriver)element;
        File screen = ((TakesScreenshot)wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
        BufferedImage image = ImageIO.read(screen);
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
        Rectangle rect = new Rectangle(width, height);
        Point p = element.getLocation();
        BufferedImage dest = image.getSubimage(p.getX(), p.getY(), rect.width, rect.height);

        ImageIO.write(dest, "png", screen);
        return screen;

    }


    //@Test
    public void testSetAttribute(){
        String fireFoxPath = "e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
        File file = new File(fireFoxPath);
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(binary, profile);

        driver.get("http://www.baidu.com");
        WebElement wb = driver.findElement(By.id("kw"));
        highlightElement(wb);
        //setAttribute(wb, "value", "test");
    }

    @Before
    public void setUp(){
        String fireFoxPath = "e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
        File file = new File(fireFoxPath);
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
    }

    @Test
    public void testWebelement(){
        driver.get("http://www.baidu.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class=mnav]")));
        WebElement wb = driver.findElement(By.cssSelector("a[class=mnav]"));
        try{
            FileUtils.copyFile(captureElement(wb), new File("E:\\a.png"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
