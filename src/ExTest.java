/**
 * Created by yujun on 2015/9/1.
 */

import java.io.File;
import java.util.*;

import com.sun.jna.platform.FileUtils;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
;

public class ExTest {
    WebDriver driver = null;
    JavascriptExecutor js = null;

    private void injectjQueryIfNeeded() {
        if (!jQueryLoad())
            injectjQuery();
    }

    public Boolean jQueryLoad() {
        Boolean loaded;
        try {
            loaded = (Boolean) js.executeScript("return jQuery!=null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    public void injectjQuery() {
        js.executeScript(" var headID = document.getElementsByTagName(\"head\")[0];"
                + "var newScript = document.createElement('script');"
                + "newScript.type = 'text/javascript';"
                + "newScript.src = 'http://www.w3school.com.cn/jquery/jquery-1.11.1.min.js';"
                + "headID.appendChild(newScript);");
        System.out.println("jQuery Loaded!");
    }

    private boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Before
    public void setUp() {
        File file = new File("e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
        js = (JavascriptExecutor) driver;
    }

    //@Test
    public void test() {

        driver.get("http://www.baidu.com");
        List<WebElement> links = driver.findElements(By.cssSelector(".mnav"));
        WebElement news = driver.findElement(By.partialLinkText("123"));

        System.out.println(news.getText());
        System.out.println("Hi");
        //assertEquals(10, links.size());
        for (WebElement link : links) {
            System.out.println(link.getAttribute("href"));
        }
    }

    //@Test
    public void test2() {
        driver.get("http://passport.111.com.cn/sso/login.action");
        WebElement login = driver.findElement(By.tagName("button"));
        login.click();
        System.out.println("a");
    }

    //@Test
    public void test3() {
        driver.get("http://www.baidu.com");
        WebElement news = driver.findElement(By.cssSelector("[name^=tj_trnews]"));
        news.click();
    }

    //@Test
    public void testjQuery() {
        driver.get("http://www.baidu.com");
        injectjQueryIfNeeded();
        js.executeScript("$('.mnav').css('background', 'red')");
        js.executeScript("jQuery.find('.mnav')");

    }

    //@Test
    public void testTable(){
        driver.get("http://www.w3school.com.cn/html/html_tables.asp");
        List<WebElement> rows = driver.findElements(By.cssSelector(".dataintable tr"));

        for (WebElement row : rows){
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols){
                System.out.println(col.getText());
            }
            System.out.println();
        }

    }

    //@Test
    public void checklinks(){
        driver.get("http://www.baidu.com");
        //injectjQueryIfNeeded();
        System.out.println(js.executeScript("return window.document.title"));
        long linkNum = (long)js.executeScript("var a = $.find('a');" +
        "return a.length");
        js.executeScript("$('.mnav').css('background', 'blue')");
        System.out.println(linkNum);
        assertEquals(31, linkNum);
        driver.manage().window().maximize();
        try{
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            org.apache.commons.io.FileUtils.copyFile(file, new File("E:\\a.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //@Test
    public void testYYW(){
        Boolean page;
        driver.get("http://www.111.com.cn");

        try{
            driver.findElement(By.className("close_a"));
            page = true;
        }catch (Exception e){
            page = false;
        }

        if(page){
            js.executeScript("void(0)");
        }
        //WebElement publicity_page = driver.findElement(By.className("close_a"));
        WebElement oldAddress = driver.findElement(By.cssSelector("#usersProvince > strong"));
        System.out.println(oldAddress.getText());
        js.executeScript("setAddressCity(7)");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usersProvince")));
        System.out.println(driver.findElement(By.cssSelector("#usersProvince")).getText());
    }

    //@Test
    public void testSelect(){
        driver.get("file:///E:/Backup/ext-4.0.7-gpl/examples/form/combos.html");
        driver.manage().window().maximize();
        Select select = new Select(driver.findElement(By.cssSelector("#stateSelectOrig")));
        try{
            select.selectByValue("IL");
        }catch (Exception e){
            e.printStackTrace();
        }

        }

    //@Test
    public void testElementPresent(){
        driver.get("http://www.baidu.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"kw\"]")));
        driver.findElement(By.xpath("//*[@id=\"kw\"]")).sendKeys("selenium");
        if(isElementPresent(By.cssSelector(".su"))){
            driver.findElement(By.cssSelector(".su")).click();
        }else{
            fail("Not Found");
        }

    }

    //@Test
    public void testWindowsPop(){
        driver.get("http://127.0.0.1:5000/login");
        String parentWindoswId = driver.getWindowHandle();
        //System.out.println(parentWindoswId);
        WebElement Button = driver.findElement(By.cssSelector("#test"));
        Button.click();
        Set<String> handles = driver.getWindowHandles();
        //System.out.println(handles);
        Iterator<String> iterator = handles.iterator();
        String handle1 = iterator.next();
        String handle2 = iterator.next();

        try{
            driver.switchTo().window(handle2);
        }catch (NoSuchWindowException e){
            e.printStackTrace();
            return;
        }
        WebElement close = driver.findElement(By.id("close"));
        close.click();
        driver.switchTo().window(parentWindoswId);

    }

    @Test
    public void testAlert(){
        driver.get("http://127.0.0.1:5000/login");
        String parent = driver.getWindowHandle();
        driver.findElement(By.id("alert")).click();
        Alert confirmBox = driver.switchTo().alert();
        confirmBox.accept();
        Alert alert = driver.switchTo().alert();
        assertTrue(confirmBox.getText().equals("Sure"));
        confirmBox.accept();
    }

    @Test
    public void testPromAlert(){
        driver.get("http://127.0.0.1:5000/login");
        WebElement prop = driver.findElement(By.id("promp"));
        prop.click();
        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys("input something");
        prompt.accept();
        String span = driver.findElement(By.cssSelector("span")).getText();
        assertTrue(span.equals("input something"));
    }

    @Test
    public void testFrame(){
        driver.get("http://127.0.0.1:5000/frame");
        driver.switchTo().frame("left");
        String leftMessage = driver.findElement(By.cssSelector("p")).getText();
        assertEquals("Left", leftMessage);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("right");
        String rightMsg = driver.findElement(By.cssSelector("p")).getText();
        assertEquals("right", rightMsg);
        driver.switchTo().parentFrame();
        driver.switchTo().frame(1);
        String middleMsg = driver.findElement(By.cssSelector("p")).getText();
        assertEquals("middle", middleMsg);
    }

    @Test
    public void findFrame(){
        driver.get("http://127.0.0.1:5000/frame");
        List<WebElement> frames = driver.findElements(By.tagName("frame"));
        for(int i = 0; i < frames.size(); i++ ){
            driver.switchTo().frame(i);
            //页面寻找
            if(driver.getPageSource().contains("middle")){
                break;
            }else {
                driver.switchTo().parentFrame();
            }
        }
        String actString = driver.findElement(By.cssSelector("p")).getText();
        assertEquals("middle", actString);
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }
}
