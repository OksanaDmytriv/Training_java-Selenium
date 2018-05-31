import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Homework_7 {
    static WebDriver driver;
    WebDriverWait wait;

    public void login() {
        wait = new WebDriverWait(driver, 3);
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public List<WebElement> findElementsByCSSSelector(String locator) {
        return driver.findElements(By.cssSelector(locator));
    }

    public ExpectedCondition<String> anyNewWindowOtherThan(Set<String> windows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @BeforeClass
    public static void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void seventhTest() {
        login();

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        findElementsByCSSSelector(".dataTable tr i[class='fa fa-pencil']").get(100).click();
        List<WebElement> urls = findElementsByCSSSelector(".fa.fa-external-link");

        String startWindow = driver.getWindowHandle();

        for (WebElement url : urls) {
            Set<String> windows = driver.getWindowHandles();
            url.click();
            String newWindow = wait.until(anyNewWindowOtherThan(windows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(startWindow);
        }
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }

}

