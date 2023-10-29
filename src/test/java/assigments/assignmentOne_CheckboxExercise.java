package assigments;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class assignmentOne_CheckboxExercise {

    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Selecionando o Checkbox, verificando se está selecionado, removendo e validando o oposto e depois contando a quantidade de checkboxs na página")
    public void checkBoxSubmissiion() {

        WebElement checkbox1 = driver.findElement(By.cssSelector("#checkBoxOption1"));
        checkbox1.click();
        assertTrue(checkbox1.isSelected());
        checkbox1.click();
        assertFalse(checkbox1.isSelected());
        System.out.println(driver.findElements(By.xpath("//*[@type='checkbox']")).size());

    }

}
