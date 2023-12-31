package assigments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AssignmentTwo_UiValidations {

    private WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void setUp() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void fillingProtractorTutorial() {
        driver.findElement(By.name("name")).sendKeys("Gustavo");
        driver.findElement(By.name("email")).sendKeys("email@email.com");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("password");
        driver.findElement(By.id("exampleCheck1")).click();
        Select gender = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
        gender.selectByVisibleText("Male");
        driver.findElement(By.cssSelector("input[id='inlineRadio1']")).click();
        driver.findElement(By.name("bday")).sendKeys("10102000");
        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        System.out.println(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText());
    }

}
