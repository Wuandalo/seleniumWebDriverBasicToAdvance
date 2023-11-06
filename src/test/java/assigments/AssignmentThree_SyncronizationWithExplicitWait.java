package assigments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AssignmentThree_SyncronizationWithExplicitWait {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addingItensToCard() {
        String username = "rahulshettyacademy";
        String password = "learning";

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='user']/following-sibling::span")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
        driver.findElement(By.id("okayBtn")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("okayBtn")));
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
        dropdown.selectByVisibleText("Consultant");
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.id("signInBtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Shop Name']")));
        List<WebElement> itens = driver.findElements(By.xpath("//button[@class='btn btn-info']"));
        for (WebElement item : itens) {
            item.click();
        }
        driver.findElement(By.xpath("//a[contains(@class,'btn-primary')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-success")));
    }


}
