package examples;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WindowHandle {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Fazendo como foi ensinado na aula")
    public void handlingChildWindow() {
        driver.findElement(By.cssSelector("a[class='blinkingText']")).click();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String firstBrowser = it.next();
        String secondBrowser = it.next();
        driver.switchTo().window(secondBrowser);
        String login = driver.findElement(By.xpath("//p[@class='im-para red']/descendant::a")).getText();
        driver.switchTo().window(firstBrowser);
        driver.findElement(By.id("username")).sendKeys(login.trim());
    }

    @Test
    @DisplayName("Fazendo por conta própria")
    public void handlingChildWindow2() {
        driver.findElement(By.cssSelector("a[class='blinkingText']")).click();
        Set<String> windows = driver.getWindowHandles();
        List<String> stringList = new ArrayList<>(windows);
        driver.switchTo().window(stringList.get(1));
        String login = driver.findElement(By.xpath("//p[@class='im-para red']/descendant::a")).getText();
        driver.switchTo().window(stringList.get(0));
        driver.findElement(By.id("username")).sendKeys(login.trim());
    }
}
