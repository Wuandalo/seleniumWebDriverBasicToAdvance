package org.qaProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddingItensToCard {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void tryingToPlaceOrderWithoutItens(){
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
        assertEquals("You cart is empty!",driver.findElement(By.xpath("//h2[text()='You cart is empty!']")).getText());
    }

    @Test
    public void addingProductsOnCart() throws InterruptedException {

        WebElement item;
        String price;
        int totalPrice=0;
        List<String> shopList = getStrings();
        for (String itemList:shopList){
            item = driver.findElement(By.xpath("//h4[@class='product-name' and contains(text(),'"+itemList+"')]/following-sibling::div/button"));
            price = driver.findElement(By.xpath("//h4[@class='product-name' and contains(text(),'"+itemList+"')]/following-sibling::p")).getText();
            totalPrice=totalPrice+Integer.parseInt(price);
            if (item.isDisplayed()){
                item.click();
            }
        }
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
        Thread.sleep(2000);
        assertEquals(totalPrice,Integer.parseInt(driver.findElement(By.xpath("//span[@class='discountAmt']")).getText()));
    }
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

}
