package assigments;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class AssignmentOne_CheckboxExercise {

    WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Selecionando o Checkbox, verificando se está selecionado, removendo e validando o oposto e depois contando a quantidade de checkboxs na página")
    public void checkBoxSubmissiion() {

        driver.findElement(By.cssSelector("#checkBoxOption1")).click();
        assertTrue(driver.findElement(By.cssSelector("#checkBoxOption1")).isSelected());
        driver.findElement(By.cssSelector("#checkBoxOption1")).click();
        assertFalse(driver.findElement(By.cssSelector("#checkBoxOption1")).isSelected());
        System.out.println(driver.findElements(By.xpath("//*[@type='checkbox']")).size());

    }

}
