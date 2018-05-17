import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Homework_2 {
    static WebDriver driver;
    public int menuSize;

    public List<WebElement> menu() {
        return driver.findElement(By.id("box-apps-menu")).findElements(By.tagName("li"));
    }

    public WebElement menuElementFinder(int count) {
        return menu().get(count);
    }

    public List<WebElement> menuElements(int count) {
        return menuElementFinder(count).findElement(By.className("docs")).findElements(By.tagName("li"));
    }

    public Integer menuElementsSize(int count) {
        return menuElements(count).size();
    }

    public void clickOnLogo() {
        driver.findElement(By.className("logotype")).click();
    }

    public void login() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @BeforeClass
    public static void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void secondTest() {
        login();
        menuSize = menu().size();
        for (int i = 0; i < menuSize; i++) {
            clickOnLogo();

            WebElement menuElement = menuElementFinder(i);
            menuElement.click();
            Integer menuElSize = menuElementsSize(i);

            if (menuElSize > 1) {
                for (int k = 0; k < menuElSize; k++) {
                    menuElements(i).get(k).click();
                    //Assert.assertTrue(driver.findElements(By.cssSelector("hi")).size() > 0);
                    //Assert.assertTrue(IsElementPresent(By.cssSelector("h1")));
                }
            }
            if (menuElSize == 1) {
                {
                    menuElement.click();
                }

            }
        }
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }

}
