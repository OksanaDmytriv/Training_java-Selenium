import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class Homework_5 {
    static WebDriver driver;

    public void login() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public List<WebElement> findElementsByCSSSelector(String locator) {
        return driver.findElements(By.cssSelector(locator));
    }

    public WebElement findElementByCSSSelector(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }

    public WebElement findElementByName(String locator) {
        return driver.findElement(By.name(locator));
    }

    public List<WebElement> findElementsByName(String locator) {
        return driver.findElements(By.name(locator));
    }

    public void fillGeneralTab(String productName, String imagePath) {
        findElementsByName("status").get(0).click();
        findElementByName("name[en]").sendKeys(productName);
        findElementByName("code").sendKeys("1111");
        findElementsByName("categories[]").get(0).click();
        findElementsByName("categories[]").get(1).click();
        findElementsByName("product_groups[]").get(0).click();
        findElementsByName("product_groups[]").get(1).click();
        findElementByName("new_images[]").sendKeys(imagePath);
    }

    public void navigateBetweenTabs(Integer index) {
        findElementByCSSSelector("ul[class='index']").findElements(By.tagName("li")).get(index).click();
    }

    @BeforeClass
    public static void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void fifthTest() {
        login();
        findElementByCSSSelector("#box-apps-menu li:nth-child(2)").click();
        findElementByCSSSelector("a.button:nth-child(2)").click();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("Costa_Rica.jpg").getFile());
        String productName = "Test product " + UUID.randomUUID().toString();

        fillGeneralTab(productName, file.getAbsolutePath());

        //fill Information tab
        navigateBetweenTabs(1);
        WebElement manufacturer = findElementByName("manufacturer_id");
        Select manufacturerDropDown = new Select(manufacturer);
        manufacturerDropDown.selectByIndex(1);
        findElementByName("keywords").sendKeys("Costa Rica nature");
        findElementByCSSSelector(".trumbowyg-editor").sendKeys("This is test product");

        //fill Price tab
        navigateBetweenTabs(3);
        findElementByName("prices[USD]").sendKeys("123456");

        findElementByName("save").click();

        findElementByName("query").sendKeys(productName + Keys.ENTER);
        String nameToCheck = findElementByCSSSelector(".dataTable td:nth-child(3)").getText();

        Assert.assertEquals(productName, nameToCheck);
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }

}
