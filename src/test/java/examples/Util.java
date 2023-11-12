package examples;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Util {

    public static void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static void clickFor(WebElement element, int quantity) {
        for (int i = 1; i < quantity; i++) {
            element.click();
        }
    }

    public static void scrowDownTo(WebElement element, WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    static void selectDay(String sDay, List<WebElement> oDays) {
        boolean bDayFound = false;

        for(WebElement day:oDays){
            if (day.getText().equals(sDay)){
                day.click();
                bDayFound = true;
                break;
            }
        }

        if(!bDayFound){
            Assert.fail("Day "+ sDay +" wasn't found");
        }
    }

    public static void selectMonth(WebElement eCurrentMonth, WebElement eNextMonth,String sMonth) {
        boolean bMonthFound = false;
        for(int i=0; i<11; i++) {
            if(!eCurrentMonth.getText().contains(sMonth)){
                eNextMonth.click();
            }else{
                bMonthFound = true;
                break;
            }
        }

        if(!bMonthFound){
            Assert.fail("Month "+ bMonthFound +" wasn't found");
        }
    }

    public static void selectYear(WebElement eCurrentYear, String sYear, WebElement eNextYear) {
        boolean bYearFound = false;

        for (int i=0;i<20;i++){
            if(!eCurrentYear.getText().equals(sYear)){
                eNextYear.click();
            }else{
                bYearFound = true;
                break;
            }
        }

        if(!bYearFound){
            Assert.fail("Year "+ bYearFound +" wasn't found");
        }
    }


}
