package assigments;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AssignmentFour_WindowHandling {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Accessing different tabs to grab information")
    public void windowHandling() {
        driver.findElement(By.xpath("//a[text()='Multiple Windows']")).click();
        driver.findElement(By.xpath("//a[text()='Click Here']")).click();
        List<String> tabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(1));
        System.out.println(driver.findElement(By.className("example")).getText());
        driver.switchTo().window(tabList.get(0));
        System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());
    }

}
