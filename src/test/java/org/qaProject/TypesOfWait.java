package org.qaProject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class TypesOfWait {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void clickingWithImplicitWait(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        driver.findElement(By.xpath("//div[@id='finish']/h4")).click();
        System.out.println(driver.findElement(By.id("finish")).getText());
    }

    @Test
    public void clickingWithExplicitWait(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
        System.out.println(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText());
    }

    @Test
    public void clickingWithFluentWait(){

        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);

        WebElement helloWorld = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.xpath("//div[@id='finish']/h4"));
                if (element.isDisplayed()){
                    return element;
                }else{
                    return null;
                }
            }
        });
        System.out.println(helloWorld.getText());

    }

}
