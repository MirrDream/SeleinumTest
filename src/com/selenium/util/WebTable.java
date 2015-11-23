package com.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by yujun on 2015/10/14.
 */
public class WebTable {
    private WebElement webTable;
    public WebTable(WebElement webElement){
        this.webTable = webElement;
    }

    public int getRowCount(){
        List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
        return rowCounts.size();
    }

    public int getColCount(int rowIdx){
        try{
            List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
            WebElement rowNum = rowCounts.get(rowIdx);
            List<WebElement> colCounts = rowNum.findElements(By.tagName("td"));
            return colCounts.size();
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Failed to get the cell");
        }
    }

    public String getCellText(int rowIdx, int colIdx){
        try{
            List<WebElement> rowCounts = webTable.findElements(By.tagName("tr"));
            WebElement rowNum = rowCounts.get(rowIdx);
            List<WebElement> colCounts = rowNum.findElements(By.tagName("td"));
            WebElement cell = colCounts.get(colIdx);
            return cell.getText();
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Fail to get the cell");
        }
    }
}
