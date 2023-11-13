package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CalendarHandling {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.path2usa.com/travel-companion/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void gettingTheRightDateOnTheCalendar() throws InterruptedException {

        String sYear = "2030";
        String sMonth = "October";
        String sDay = "28";

        Util.scrowDownTo(1200, driver);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("form-field-travel_comp_date"))));

        driver.findElement(By.id("form-field-travel_comp_date")).click();
        WebElement eCurrentMonth = driver.findElement(By.cssSelector(".flatpickr-month"));
        WebElement eNextMonth = driver.findElement(By.cssSelector(".flatpickr-next-month"));
        Util.selectMonth(eCurrentMonth, eNextMonth, sMonth);

        driver.findElement(By.id("form-field-travel_comp_date")).click();
        WebElement eNextYear = driver.findElement(By.cssSelector(".arrowUp"));
        Util.selectYear(driver, sYear, eNextYear);

        driver.findElement(By.id("form-field-travel_comp_date")).click();
        List<WebElement> days = driver.findElements(By.xpath("//span[@class='flatpickr-day ']"));
        Util.selectDay(sDay, days);

    }

}
