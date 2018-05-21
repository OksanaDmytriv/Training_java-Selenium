import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Homework_3 {
    static WebDriver driver;

    public WebElement findByXpathMainPage(String locator) {
        return driver.findElement(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li[1]" + locator));
    }

    public WebElement findByClassName(String locator) {
        return driver.findElement(By.className(locator));
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
        String discountPriceMainPage = findByXpathMainPage("//strong[@class='campaign-price']").getText();

        String textStyleDiscountPriceMainPage = findByXpathMainPage("//strong[@class='campaign-price']")
                .getTagName();
        String colorDiscountPriceMainPage = findByXpathMainPage("//strong[@class='campaign-price']")
                .getCssValue("color");

        String regularPriceMainPage = findByXpathMainPage("//s[@class='regular-price']").getText();

        String textStyleRegularPriceMainPage = findByXpathMainPage("//s[@class='regular-price']")
                .getTagName();
        String colorRegularPriceMainPage = findByXpathMainPage("//s[@class='regular-price']")
                .getCssValue("color");


        //product page
        findByXpathMainPage("").click();

        String productPageText = driver.findElement(By.cssSelector("#box-product .title")).getText();
        String discountPriceProductPage = findByClassName("campaign-price").getText();

        String textStyleDiscountPriceProductPage = findByClassName("campaign-price").getTagName();
        String colorDiscountPriceProductPage = findByClassName("campaign-price")
                .getCssValue("color");

        String regularPriceProductPage = driver.findElement(By.className("regular-price")).getText();

        String textStyleRegularPriceProductPage = findByClassName("regular-price").getTagName();
        String colorRegularPriceProductPage = findByClassName("regular-price")
                .getCssValue("color");


        //asserts
        Assert.assertEquals(mainPageText, productPageText);

        Assert.assertEquals(discountPriceMainPage, discountPriceProductPage);
        Assert.assertEquals(regularPriceMainPage, regularPriceProductPage);

        Assert.assertEquals(textStyleDiscountPriceMainPage, textStyleDiscountPriceProductPage);
        Assert.assertEquals(colorDiscountPriceMainPage,colorDiscountPriceProductPage);

        Assert.assertEquals(textStyleRegularPriceMainPage, textStyleRegularPriceProductPage);
        Assert.assertEquals(colorRegularPriceMainPage, colorRegularPriceProductPage);
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }
}
