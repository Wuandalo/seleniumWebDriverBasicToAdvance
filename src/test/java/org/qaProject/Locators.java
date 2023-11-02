package org.qaProject;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class Locators {

    WebDriver driver;
    WebDriverWait wait;
    //WebDriver driver = new EdgeDriver();
    //WebDriver driver = new FirefoxDriver();

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Criando um login e testando na aplicação")
    public void creatingUserTest() throws InterruptedException {

        String username = "Gustavo";
        String email = "email@email.com";
        String phoneNumber = "123123123";
        String password;

        Util.inputText(driver.findElement(By.id("inputUsername")), username);
        Util.inputText(driver.findElement(By.name("inputPassword")), "123");
        driver.findElement(By.className("submit")).click();
        driver.findElement(By.linkText("Forgot your password?")).click();

        Util.inputText(driver.findElement(By.xpath("//input[@placeholder='Name']")), username);
        Util.inputText(driver.findElement(By.cssSelector("input[placeholder='Email']")), email);
        Util.inputText(driver.findElement(By.xpath("//input[@type='text'][3]")), phoneNumber);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("reset-pwd-btn"))));
        driver.findElement(By.className("reset-pwd-btn")).click();
        password = this.getPassword(driver.findElement(By.className("infoMsg")).getText());
        driver.findElement(By.className("go-to-login-btn")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'overlay-left')]/h1")));

        Util.inputText( driver.findElement(By.id("inputUsername")),username);
        Util.inputText(driver.findElement(By.name("inputPassword")), password);

        driver.findElement(By.xpath("//input[@value='agreeTerms']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Test
    @DisplayName("Validando Login e Logout")
    public void loginLogoutTest() throws InterruptedException {
        String username = "Gustavo";
        Util.inputText(driver.findElement(By.id("inputUsername")), username);
        Util.inputText(driver.findElement(By.name("inputPassword")), "rahulshettyacademy");
        driver.findElement(By.xpath("//input[@value='agreeTerms']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertEquals(driver.findElement(By.xpath("//div[@class='login-container']/h2")).getText(), "Hello " + username + ",");
        assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");

        driver.findElement(By.xpath("//button[text()='Log Out']")).click();
        assertEquals(driver.findElement(By.xpath("//form/h1")).getText(), "Sign in");

    }

    public String getPassword(String message) {
        String[] messageList = message.split("'");
        return messageList[1];
    }

}
