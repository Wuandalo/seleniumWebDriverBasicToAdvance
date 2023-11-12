package examples;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

    public static void scrowDownTo(WebElement element, WebDriver driver) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
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

    public static void selectMonth(WebElement eCurrentMonth, WebElement eNextMonth,String sMonth) throws InterruptedException {
        boolean bMonthFound = false;
        for(int i=0; i<=11; i++) {
            if(!eCurrentMonth.getText().contains(sMonth)){
                eNextMonth.click();
                Thread.sleep(500);
            }else{
                bMonthFound = true;
                break;
            }
        }

        if(!bMonthFound){
            Assert.fail("Month "+ sMonth +" wasn't found");
        }
    }

    public static void selectYear(WebDriver driver, String sYear, WebElement eNextYear) {

        boolean bYearFound = false;
        for (int i=0;i<20;i++){
            String sCurrentYear = driver.findElement(By.xpath("//span[@class='flatpickr-day ']")).getAttribute("aria-label");
            if(!sCurrentYear.contains(sYear)){
                eNextYear.click();
            }else{
                bYearFound = true;
                break;
            }
        }

        if(!bYearFound){
            Assert.fail("Year "+ sYear +" wasn't found");
        }
    }


}
