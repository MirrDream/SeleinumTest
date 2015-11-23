import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by yujun on 2015/10/12.
 */
@RunWith(value = Parameterized.class)
public class ExcelData {
    private String height;
    private String weight;
    private String bmi;
    private String bmiCategory;
    WebDriver driver;

    @Parameterized.Parameters
    public static Collection<String[]> testData(){
        List<String[]> records = new ArrayList<>();
        try{
            InputStream is = new FileInputStream("e://a.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();

            for(int i = 0; i <= rowNum; i++){
                XSSFRow row = sheet.getRow(i);
                int colNum = row.getLastCellNum();
                String[] data = new String[colNum];

                for(int j = 0; j < colNum; j++){
                    data[j] = row.getCell(j).getStringCellValue();
                    System.out.println(data[j]);
                }
                records.add(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return records;
    }

    public ExcelData(String height, String weight, String bmi, String bmiCategory){
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmiCategory = bmiCategory;
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
