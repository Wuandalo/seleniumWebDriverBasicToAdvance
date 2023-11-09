package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActionsDemo {

    private WebDriver driver;
    private Actions action;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void moveToElement() {
        action.moveToElement(driver.findElement(By.name("field-keywords")))
                .click()
                .keyDown(Keys.SHIFT)
                .sendKeys("Test")
                .doubleClick()
                .build()
                .perform();
        action.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']")))
                .contextClick()
                .build()
                .perform();
    }


}
