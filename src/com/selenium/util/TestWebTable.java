package com.selenium.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * Created by yujun on 2015/10/15.
 */
public class TestWebTable {
    WebDriver driver;
    @Before
    public void SetUp(){
        String fireFoxPath = "e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
        File file = new File(fireFoxPath);
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
    }

    @Test
    public void testTableElement(){
        driver.get("http://www.w3school.com.cn/tags/tag_table.asp");
        WebTable webTable = new WebTable(driver.findElement(By.cssSelector("table[class=dataintable]")));
        assertEquals(10, webTable.getRowCount());
        assertEquals(3, webTable.getColCount(1));
        assertEquals("pixels",webTable.getCellText(3,1));
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
