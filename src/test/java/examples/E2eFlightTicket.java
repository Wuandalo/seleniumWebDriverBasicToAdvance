package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class E2eFlightTicket {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void serchingFlight() {
        String option = "India";
        String origin = "AIP";
        String destination = "BHO";
        int adultQntity = 4;
        driver.findElement(By.cssSelector("input[value='RoundTrip']")).click();
        driver.findElement(By.cssSelector("#autosuggest")).sendKeys("ind");
        List<WebElement> contries = driver.findElements(By.cssSelector("#ui-id-1 li[class='ui-menu-item'] a"));
        for (WebElement country : contries) {
            if (country.getText().equalsIgnoreCase(option)) {
                country.click();
                break;
            }
        }
        driver.findElement(By.xpath("//input[contains(@id,'originStation1_CTXT')]")).click();
        driver.findElement(By.xpath("//div[contains(@id,'originStation1_CTNR')]/descendant::a[@value='" + origin + "']")).click();
        driver.findElement(By.xpath("//div[contains(@id,'destinationStation1_CTNR')]/descendant::a[@value='" + destination + "']")).click();
        assertEquals(origin, driver.findElement(By.xpath("//input[contains(@id,'originStation1_CTXT')]")).getAttribute("selectedvalue"));
        assertEquals(destination, driver.findElement(By.xpath("//input[contains(@id,'destinationStation1_CTXT')]")).getAttribute("selectedvalue"));
        List<WebElement> dataIda = driver.findElements(By.xpath("//div[contains(@class,'datepicker-group-first')]/descendant::td[@data-handler='selectDay']/a"));
        Collections.shuffle(dataIda);
        dataIda.get(0).click();
        driver.findElement(By.cssSelector("#ctl00_mainContent_view_date2")).click();
        List<WebElement> dataVolta = driver.findElements(By.xpath("//div[contains(@class,'datepicker-group-last')]/descendant::td[@data-handler='selectDay']/a"));
        Collections.shuffle(dataVolta);
        dataVolta.get(0).click();
        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
        driver.findElement(By.id("divpaxinfo")).click();
        Util.clickFor(driver.findElement(By.id("hrefIncAdt")), adultQntity);
        driver.findElement((By.id("btnclosepaxoption"))).click();
        assertTrue(driver.findElement(By.id("divpaxinfo")).getText().contains(String.valueOf(adultQntity)));
        Select dropdown = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
        dropdown.selectByValue("AED");
        assertEquals(dropdown.getFirstSelectedOption().getText(), "AED");
        dropdown.selectByVisibleText("USD");
        assertEquals(dropdown.getFirstSelectedOption().getText(), "USD");
        driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
    }

}
