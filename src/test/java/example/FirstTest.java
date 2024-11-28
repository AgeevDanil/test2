package example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;


public class FirstTest {
    public static FoodPage foodPage;
    public static WebDriver driver;

    @BeforeAll
    static void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/ageev/Downloads/first-web-test/src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/food");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().getScriptTimeout();
        foodPage = new FoodPage(driver);

    }

    @Test
    void test1(){
        foodPage.ClickAdd();
        foodPage.inputName("Кабачок");
        foodPage.Type("VEGETABLE");
        foodPage.SaveItem();
        foodPage.checkNewItem("Кабачок", "Овощ", "false");
    }

    @Test
    void test2(){
        foodPage.ClickAdd();
        foodPage.inputName("Папая");
        foodPage.Type("FRUIT");
        foodPage.setExot();
        foodPage.SaveItem();
        foodPage.checkNewItem("Папая", "Фрукт", "true");
    }
    @AfterEach
    @Test
    void cleaning(){
        foodPage.ClearAll();
    }
    @AfterAll
    static void tearDown(){
        driver.quit();
    }
}
