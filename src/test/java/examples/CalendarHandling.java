package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CalendarHandling {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.path2usa.com/travel-companion/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void gettingTheRightDateOnTheCalendar(){

        String year;
        String month;
        String day;

        driver.findElement(By.id("form-field-travel_comp_date")).click();

        List<WebElement> days = driver.findElements(By.xpath("//span[@class='flatpickr-day ']"));
        WebElement nextMonth = driver.findElement(By.cssSelector(".flatpickr-next-month"));
        WebElement nextYear = driver.findElement(By.cssSelector("arrowUp"));

        WebElement currentMonth = driver.findElement(By.cssSelector("cur-month"));
        WebElement currentYear = driver.findElement(By.xpath("//input[@class='numInput cur-year']"));

    }

}
