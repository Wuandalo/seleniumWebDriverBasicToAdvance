package assigments;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class AssignmentSix_Practice {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Test assignment number six")
    public void manipulatingValues() {
        driver.findElement(By.xpath("//label[@for='honda']/input")).click();
        String option = driver.findElement(By.xpath("//label[@for='honda']")).getText();
        Select dropdow = new Select(driver.findElement(By.id("dropdown-class-example")));
        dropdow.selectByVisibleText(option);
        driver.findElement(By.id("name")).sendKeys(option);
        driver.findElement(By.id("alertbtn")).click();
        assertTrue(driver.switchTo().alert().getText().contains(option));
        System.out.println(option);
    }
}