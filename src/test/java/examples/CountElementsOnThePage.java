package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CountElementsOnThePage {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void countingTagElements() {
        System.out.println(driver.findElements(By.tagName("a")).size());
    }

    @Test
    public void countingTagElementsOnFootPage() {
        WebElement columDriverFooter = driver.findElement(By.xpath("//table[@class='gf-t']/descendant::ul[1]"));
        System.out.println(columDriverFooter.findElements(By.tagName("a")).size());

        for (WebElement link : columDriverFooter.findElements(By.tagName("a"))) {
            link.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
        }

        Set<String> windows = driver.getWindowHandles();
        List<String> stringList = new ArrayList<>(windows);
        for (String tab : stringList) {
            driver.switchTo().window(tab);
            System.out.println(driver.getTitle());
            System.out.println(driver.getCurrentUrl());
        }
    }

}