package org.qaProject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class handlingdropdowns {

    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setUp(){
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
        assertEquals(dropdown.getFirstSelectedOption().getText(),"AED");
        dropdown.selectByVisibleText("USD");
        assertEquals(dropdown.getFirstSelectedOption().getText(),"USD");
    }

    @Test
    public void selectUiDropdowns() throws InterruptedException {
        int adultQntity = 4;
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(500);
        Util.clickFor(driver.findElement(By.id("hrefIncAdt")),adultQntity);
        driver.findElement((By.id("btnclosepaxoption"))).click();
        assertTrue(driver.findElement(By.id("divpaxinfo")).getText().contains(String.valueOf(adultQntity)));
    }

    @Test
    public void dynamicDropdowns() throws InterruptedException {
        String origin = "AIP";
        String destination = "BHO";
        driver.findElement(By.xpath("//input[contains(@id,'originStation1_CTXT')]")).click();
        driver.findElement(By.xpath("//div[contains(@id,'originStation1_CTNR')]/descendant::a[@value='"+origin+"']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@id,'destinationStation1_CTNR')]/descendant::a[@value='"+destination+"']")).click();
        assertEquals(origin,driver.findElement(By.xpath("//input[contains(@id,'originStation1_CTXT')]")).getAttribute("selectedvalue"));
        assertEquals(destination,driver.findElement(By.xpath("//input[contains(@id,'destinationStation1_CTXT')]")).getAttribute("selectedvalue"));
    }

    @Test
    public void autoSuggestiveDropDowns() throws InterruptedException {
        String option = "India";
        driver.findElement(By.cssSelector("#autosuggest")).sendKeys("ind");
        Thread.sleep(3000);
        List<WebElement> contries = driver.findElements(By.cssSelector("#ui-id-1 li[class='ui-menu-item'] a"));
        for (WebElement country:contries){
            if(country.getText().equalsIgnoreCase(option)){
                country.click();
                break;
            }
        }
    }

    @Test
    public void checkBoxItens(){
        List<WebElement> checkBoxDiscounts = driver.findElements(By.xpath("//div[@id='discount-checkbox']/descendant::input"));
        for (WebElement checkbox:checkBoxDiscounts){
            assertFalse(checkbox.isSelected());
            checkbox.click();
            assertTrue(checkbox.isSelected());
        }
    }

}
