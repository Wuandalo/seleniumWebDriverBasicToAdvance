package org.qaProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddingItensToCard {

    private WebDriver driver;
    private WebDriverWait wait;

    private static List<String> getStrings() {
        List<String> shopList = new ArrayList<>();
        shopList.add("Brocolli");
        shopList.add("Cucumber");
        shopList.add("Tomato");
        shopList.add("Brinjal");
        shopList.add("Capsicum");
        shopList.add("Potato");
        shopList.add("Corn");
        shopList.add("Apple");
        shopList.add("Grapes");
        shopList.add("Musk Melon");
        shopList.add("Pears");
        shopList.add("Raspberry");
        shopList.add("Almonds");
        shopList.add("Nuts Mixture");
        shopList.add("Cashews");
        return shopList;
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void tryingToPlaceOrderWithoutItens() {
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
        assertEquals("You cart is empty!", driver.findElement(By.xpath("//h2[text()='You cart is empty!']")).getText());
    }

    @Test
    public void addingProductsOnCart() {

        WebElement item;
        String price;
        int totalPrice = 0;
        double discountPrice = 0.0;
        List<String> shopList = getStrings();
        for (String itemList : shopList) {
            item = driver.findElement(By.xpath("//h4[@class='product-name' and contains(text(),'" + itemList + "')]/following-sibling::div/button"));
            price = driver.findElement(By.xpath("//h4[@class='product-name' and contains(text(),'" + itemList + "')]/following-sibling::p")).getText();
            totalPrice = totalPrice + Integer.parseInt(price);
            if (item.isDisplayed()) {
                item.click();
            }
        }
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
        assertEquals(totalPrice, Integer.parseInt(driver.findElement(By.xpath("//span[@class='totAmt']")).getText()));
        driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.className("promoBtn")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("promoInfo"))));
        assertEquals("10%", driver.findElement(By.xpath("//span[@class='discountPerc']")).getText());
        discountPrice = totalPrice * (1 - (10 / 100.0));
        assertEquals(discountPrice, Double.parseDouble(driver.findElement(By.xpath("//span[@class='discountAmt']")).getText()), 0.01);
    }

}