import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Homework_6 {
    static WebDriver driver;
    WebDriverWait wait;

    public WebElement findElementByCSSSelector(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }

    public List<WebElement> findElementsByCSSSelector(String locator) {
        return driver.findElements(By.cssSelector(locator));
    }

    public void addProductToCart(String text) {
        findElementByCSSSelector("#box-most-popular ul[class='listing-wrapper products']").findElements(By.tagName("li")).get(0).click();
        driver.findElement(By.name("add_cart_product")).click();

        wait = new WebDriverWait(driver, 5);
        wait.until((WebDriver driver) -> findElementByCSSSelector("#cart a.content span.quantity").getText().equals(text));
    }

    public void removeProductFromCard(Integer num) {
        wait.until((WebDriver driver) -> findElementByCSSSelector("[name='remove_cart_item']").isEnabled());
        findElementByCSSSelector("[name='remove_cart_item']").click();
        wait.until((WebDriver driver) -> {
            Integer size = findElementsByCSSSelector("table[class='dataTable rounded-corners'] tbody tr").size();
            return size.equals(num);
        });
    }

    @BeforeClass
    public static void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void sixthTest() {
        driver.get("http://localhost/litecart/en/");

        addProductToCart("1");
        driver.get("http://localhost/litecart/en/");

        addProductToCart("2");
        driver.get("http://localhost/litecart/en/");

        addProductToCart("3");
        driver.get("http://localhost/litecart/en/");

        findElementByCSSSelector("#cart .link").click();

        removeProductFromCard(7);
        removeProductFromCard(6);
        removeProductFromCard(0);

        driver.get("http://localhost/litecart/en/");
        wait.until((WebDriver driver) -> findElementByCSSSelector("#cart a.content span.quantity").getText().equals("0"));
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }
}
