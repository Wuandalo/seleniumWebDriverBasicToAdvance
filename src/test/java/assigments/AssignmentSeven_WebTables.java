package assigments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AssignmentSeven_WebTables {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void printingNumberOfRows() {
        List<WebElement> rows = driver.findElements(By.cssSelector("table[name='courses'] tr"));
        System.out.println("The number of rows is: " + rows.size());
    }

    @Test
    public void printingNumberOfColumn() {
        List<WebElement> columns = driver.findElements(By.cssSelector("table[name='courses'] th"));
        System.out.println("The number of columns is: " + columns.size());
    }

    @Test
    public void printingTheSecondRow() {
        WebElement eInstructor = driver.findElement(By.cssSelector("table[name='courses'] tr:nth-child(3) td:nth-child(1)"));
        WebElement eCourse = driver.findElement(By.cssSelector("table[name='courses'] tr:nth-child(3) td:nth-child(2)"));
        WebElement ePrice = driver.findElement(By.cssSelector("table[name='courses'] tr:nth-child(3) td:nth-child(3)"));

        System.out.println("Instructor: " + eInstructor.getText());
        System.out.println("Course: " + eCourse.getText());
        System.out.println("Price: R$" + ePrice.getText());
    }

}
