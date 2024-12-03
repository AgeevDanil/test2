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
    public static TestSql testSql;

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
        testSql = new TestSql();

    }

    @Test
    void test1(){
        String nameItem = "Кабачок";
        foodPage.ClickAdd();
        foodPage.inputName(nameItem);
        foodPage.Type("VEGETABLE");
        foodPage.SaveItem();
        foodPage.checkNewItem (nameItem, "Овощ", "false");
        testSql.searchItemByName(nameItem, "VEGETABLE", "false");

    }

    @Test
    void test2(){
        String nameItem = "Папая";
        foodPage.ClickAdd();
        foodPage.inputName("Папая");
        foodPage.Type("FRUIT");
        foodPage.setExot();
        foodPage.SaveItem();
        foodPage.checkNewItem("Папая", "Фрукт", "true");
        testSql.searchItemByName(nameItem, "FRUIT", "true");

    }
    @Test
    void test3(){
        foodPage.ClickAdd();
        foodPage.inputName("Капуста");
        foodPage.Type("VEGETABLE");
        foodPage.SaveItem();
        foodPage.checkNewSameItem("Капуста");
        testSql.getCountOfFood("Капуста","VEGETABLE");

    }

    @AfterEach
    @Test
    void cleaning(){
        testSql.deleteUnwantedFoods();
        foodPage.ClearAll();
    }
    @AfterAll
    static void tearDown(){
        driver.quit();
    }
}
