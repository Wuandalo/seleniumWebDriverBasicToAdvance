package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class HandlingBrokeLinks {

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
    public void checkingBrokeLinks() throws IOException {

        List<WebElement> links = driver.findElements(By.xpath("//li[@class='gf-li']/a "));
        String url;
        int response;
        HttpURLConnection connection;
        SoftAssert a = new SoftAssert();

        for (WebElement link : links) {
            url = link.getAttribute("href");
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            response = connection.getResponseCode();
            a.assertTrue(response < 400, "Link " + link.getText() + " is returning " + response);
            System.out.println(response);
        }

        a.assertAll();


    }

}
