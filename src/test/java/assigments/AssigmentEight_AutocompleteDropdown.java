package assigments;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AssigmentEight_AutocompleteDropdown {

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
    public void selectingTheCountry() {
        String country = "United States Minor Outlying Islands";
        driver.findElement(By.id("autocomplete")).sendKeys("United");
        driver.findElement(By.xpath("//div[text()='" + country + "']")).click();
        Assert.assertEquals(driver.findElement(By.id("autocomplete")).getAttribute("value"), country);
    }

    @Test
    public void anotherAssignment() {
        driver.findElement(By.id("autocomplete")).sendKeys("GER");
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return document.getElementById(\"autocomplete\").value;";
        String text = (String) js.executeScript(script);
        System.out.println(text);

        int i = 0;

        while (!text.equalsIgnoreCase("Germany")) {
            i++;
            driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
            text = (String) js.executeScript(script);
            System.out.println(text);
            if (i > 10) {
                break;
            }
        }

        if (i > 10) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element  found");
        }
    }

}
