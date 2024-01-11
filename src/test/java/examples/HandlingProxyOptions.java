package examples;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HandlingProxyOptions {

    private WebDriver driver;
    private ChromeOptions options;
    private Proxy proxy;

    @BeforeMethod
    public void setUp() {
        options = new ChromeOptions();
        proxy = new Proxy();

        //Configurando proxy no navegador
        proxy.setHttpProxy("ipadress:4444");
        options.setCapability("proxy", proxy);

        //Desabilitando pop-ups de localização e entre outros ao abrir o navegador
        options.setExperimentalOption("excluideSwitches", List.of("disable-popup-blocking"));

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
