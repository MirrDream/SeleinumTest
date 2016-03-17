import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.fail;

/**
 * Created by yujun on 2015/12/11.
 */
public class TestERP {
    private WebDriver driver;
    private String baseUrl = null;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js = null;
    @Before
    public void setUp() throws Exception {
        File file = new File("e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
        js = (JavascriptExecutor) driver;
        baseUrl = "http://manage.111.com.cn/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void login(){
        System.out.println("aaa");
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.findElement(By.id("username")).sendKeys("yanghongyan");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.xpath("/html/body/div/form/div[2]/a")).click();
        driver.switchTo().frame(1);
        //driver.findElement(By.xpath("/html/body/div/form[1]/div/div[4]/a")).click();
        js.executeScript("appForward('http://erp.111.com.cn/as/admin/login.action');");
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        iterator.next();
        String handle2 = iterator.next();
        driver.switchTo().window(handle2);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div[2]/a[13]")).click();
        //js.executeScript("f_addTab('41b01a50d3894e2ab8d18a6136cb7869','��̨�µ�','/sd/backendOrder/backEndOrder.action');");
        driver.switchTo().frame("41b01a50d3894e2ab8d18a6136cb7869");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //driver.findElement(By.id("orderFourSourceId")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form[2]/div[4]/table/tbody/tr[1]/td[2]/div/div[1]/div/div[3]/div")).click();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector("td[value='30']")).click();
        //driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr[2]/td")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form[2]/div[4]/table/tbody/tr[1]/td[2]/div/div[2]/div/div[3]/div")).click();
        driver.findElement(By.cssSelector("td[value='377']")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form[2]/div[4]/table/tbody/tr[1]/td[2]/div/div[3]/div/div[3]/div")).click();
        driver.findElement(By.cssSelector("td[value='3546")).click();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"productInfo1\"]/div[2]/div/div[1]/span")).click();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.switchTo().parentFrame();
        driver.switchTo().frame("41b01a50d3894e2ab8d18a6136cb7869");
        //driver.findElement(By.cssSelector("div.l-dialog-btn:nth-child(1) > div:nth-child(3)")).click();
        driver.findElement(By.cssSelector("div.l-dialog-btn:nth-child(2) > div:nth-child(3)")).click();
        //js.executeScript("$('body > div.l-dialog.l-dialog-win.l-dialog-fixed > table > tbody > tr:nth-child(2) > td.l-dialog-cc > div > div.l-dialog-buttons > div > div:nth-child(1) > div.l-dialog-btn-inner').click();");
        //driver.findElement(By.xpath("/html/body/div[7]/table/tbody/tr[2]/td[2]/div/div[2]/div/div[1]")).click();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //Select select = new Select(driver.findElement(By.id("provinceId")));
        //select.selectByValue("337");

        //driver.findElement(By.cssSelector("body > div.l-dialog.l-dialog-win.l-dialog-fixed > table > tbody > tr:nth-child(2) > td.l-dialog-cc > div > div.l-dialog-buttons > div > div:nth-child(1) > div.l-dialog-btn-inner")).click();
        //driver.findElement(By.xpath("/html/body/div[1]/div[2]/form[2]/div[4]/table/tbody/tr[1]/td[2]/div/div[1]/div"));
        //js.executeScript("checklogin();");
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
