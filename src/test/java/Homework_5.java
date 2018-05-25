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

    public void fillGeneralTab(String productName, String imagePath) {
        findElementsByCSSSelector("input[name='status']").get(0).click();
        findElementByCSSSelector("input[name='name[en]']").sendKeys(productName);
        findElementByCSSSelector("input[name='code']").sendKeys("1111");
        findElementsByCSSSelector("input[name='categories[]']").get(0).click();
        findElementsByCSSSelector("input[name='categories[]']").get(1).click();
        findElementsByCSSSelector("input[name='product_groups[]']").get(0).click();
        findElementsByCSSSelector("input[name='product_groups[]']").get(1).click();
        findElementByCSSSelector("input[name='new_images[]']").sendKeys(imagePath);
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
        driver.findElement(By.id("box-apps-menu")).findElements(By.tagName("li")).get(1).click();
        findElementsByCSSSelector("a.button").get(1).click();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("Costa_Rica.jpg").getFile());
        String productName = "Test product " + UUID.randomUUID().toString();

        fillGeneralTab(productName, file.getAbsolutePath());

        //fill Information tab
        navigateBetweenTabs(1);
        WebElement manufacturer = findElementByCSSSelector("select[name='manufacturer_id']");
        Select manufacturerDropDown = new Select(manufacturer);
        manufacturerDropDown.selectByIndex(1);
        findElementByCSSSelector("input[name='keywords']").sendKeys("Costa Rica nature");
        findElementByCSSSelector(".trumbowyg-editor").sendKeys("This is test product");

        //fill Price tab
        navigateBetweenTabs(3);
        findElementByCSSSelector("input[name='prices[USD]']").sendKeys("123456");

        findElementByCSSSelector("button[name='save']").click();

        findElementByCSSSelector("input[name='query']").sendKeys(productName + Keys.ENTER);
        Integer productSize = findElementByCSSSelector(".dataTable").findElements(By.tagName("tr")).size();

        Assert.assertEquals("3", Integer.toString(productSize));
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }

}
