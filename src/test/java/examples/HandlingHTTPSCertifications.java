package examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingHTTPSCertifications {

    private WebDriver driver;
    private ChromeOptions options;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;
    private SafariOptions safariOptions;

    @BeforeMethod
    public void setUp() {
        options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.get("https://expired.badssl.com/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void handlingHttpCertificate() {
        System.out.println(driver.getTitle());
    }

}
