import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Homework_3 {
    static WebDriver driver;

    public WebElement findElementByXpathOnMainPage(String locator) {
        return driver.findElement(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li[1]" + locator));
    }


    @BeforeClass
    public static void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void thirdTest() {
        driver.get("http://localhost/litecart/");

        //main page
        String mainPageText = driver.findElement(By.cssSelector("#box-campaigns .name")).getText();
        String discountPriceMainPageStr = findElementByXpathOnMainPage("//strong[@class='campaign-price']").getText();
        String regularPriceMainPageStr = findElementByXpathOnMainPage("//s[@class='regular-price']").getText();


        //product page
        findElementByXpathOnMainPage("").click();

        String productPageText = driver.findElement(By.cssSelector("#box-product .title")).getText();
        String discountPriceProductPageStr = driver.findElement(By.className("campaign-price")).getText();
        String regularPriceProductPageStr = driver.findElement(By.className("regular-price")).getText();


        //asserts
        Assert.assertEquals(mainPageText, productPageText);
        Assert.assertEquals(discountPriceMainPageStr, discountPriceProductPageStr);
        Assert.assertEquals(regularPriceMainPageStr, regularPriceProductPageStr);
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }
}
