package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PerformingScrollOnTable {

    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void scrollingThePageAndTheTable() throws InterruptedException {
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(1000);
        js.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");

        int iTotalAmount = 0;
        List<WebElement> eAmounts = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        for (WebElement eAmount : eAmounts) {
            iTotalAmount = iTotalAmount + Integer.parseInt(eAmount.getText());
        }
        int iAmountTable = Integer.parseInt(driver.findElement(By.className("totalAmount")).getText().split(":")[1].trim());
        assertEquals(iAmountTable, iTotalAmount);
    }


}
