package com.expample.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

/**
 * Created by yujun on 2015/10/13.
 */
public class BmiCalculatePage {
    /*
    @FindBy(css = "input[name=height]")
    @CacheLookup
    public WebElement heightCMS;
    @FindBy(css = "input[name=weight]")
    public WebElement weightKg;

    public WebElement Calculate;
    public WebElement bmi;
    public WebElement bmi_category;
    */

    private String fireFoxPath = "e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
    @FindBy(css = "input[name=height]")
    @CacheLookup
    private WebElement heightCMS;
    @FindBy(css = "input[name=weight]")
    @CacheLookup
    private WebElement weightKg;
    @FindBy(css = "#Calculate")
    @CacheLookup
    private WebElement Calculate;
    @FindBy(css = "input[name=BMI]")
    @CacheLookup
    private WebElement bmi;
    @FindBy(css = "span[name=BMICategory]")
    @CacheLookup
    private WebElement bmi_category;
    private String url = "http://127.0.0.1:5000/BMI";
    private WebDriver driver;


    public BmiCalculatePage(){
        File file = new File(fireFoxPath);
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
        PageFactory.initElements(driver, this);
    }

    public void load(){
        this.driver.get(url);
    }

    public void close(){
        this.driver.close();
    }

    public void calculateBmi(String height, String weight){
        heightCMS.sendKeys(height);
        weightKg.sendKeys(weight);
        Calculate.click();
    }

    public String getBmi(){
        return bmi.getAttribute("value");
    }

    public String getBmiCategory(){
        return bmi_category.getText();
    }
}
