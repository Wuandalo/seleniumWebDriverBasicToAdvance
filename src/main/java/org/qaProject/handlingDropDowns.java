package org.qaProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class handlingDropDowns {

    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setup(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void selectStaticDropDown(){
        Select dropdown = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
        dropdown.selectByValue("AED");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(),"AED");
        dropdown.selectByVisibleText("USD");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(),"USD");
    }

    @Test
    public void selectUiDropdown() throws InterruptedException {
        int adultQntity = 4;
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(500);
        Util.clickFor(driver.findElement(By.id("hrefIncAdt")),adultQntity);
        driver.findElement((By.id("btnclosepaxoption"))).click();
        Assert.assertTrue(driver.findElement(By.id("divpaxinfo")).getText().contains(String.valueOf(adultQntity)));
    }

}
