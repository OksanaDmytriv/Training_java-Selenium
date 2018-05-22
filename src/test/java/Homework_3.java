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

    public WebElement findByCSSSelector(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }

    @BeforeClass
    public static void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void thirdTest() {
        driver.get("http://localhost/litecart/");

        WebElement discountPriceElementMainPage = findByXpathMainPage("//strong[@class='campaign-price']");
        WebElement regularPriceElementMainPage = findByXpathMainPage("//s[@class='regular-price']");

        //main page
        String mainPageText = findByCSSSelector("#box-campaigns .name").getText();

        String discountPriceMainPage = discountPriceElementMainPage.getText();
        String textStyleDiscountPriceMainPage = discountPriceElementMainPage.getTagName();
        String colorDiscountPriceMainPage = discountPriceElementMainPage.getCssValue("color");

        String regularPriceMainPage = regularPriceElementMainPage.getText();
        String textStyleRegularPriceMainPage = regularPriceElementMainPage.getTagName();
        String colorRegularPriceMainPage = regularPriceElementMainPage.getCssValue("color");


        //product page
        findByXpathMainPage("").click();

        WebElement discountPriceElementProductPage = findByClassName("campaign-price");
        WebElement regularPriceElementProductPage = findByClassName("regular-price");

        String productPageText = findByCSSSelector("#box-product .title").getText();

        String discountPriceProductPage = discountPriceElementProductPage.getText();
        String textStyleDiscountPriceProductPage = discountPriceElementProductPage.getTagName();
        String colorDiscountPriceProductPage = discountPriceElementProductPage.getCssValue("color");

        String regularPriceProductPage = regularPriceElementProductPage.getText();
        String textStyleRegularPriceProductPage = regularPriceElementProductPage.getTagName();
        String colorRegularPriceProductPage = regularPriceElementProductPage.getCssValue("color");

        //asserts
        Assert.assertEquals(mainPageText, productPageText);

        Assert.assertEquals(discountPriceMainPage, discountPriceProductPage);
        Assert.assertEquals(regularPriceMainPage, regularPriceProductPage);

        Assert.assertEquals(textStyleDiscountPriceMainPage, "strong");
        Assert.assertEquals(textStyleDiscountPriceProductPage, "strong");
        Assert.assertEquals(textStyleRegularPriceMainPage, "s");
        Assert.assertEquals(textStyleRegularPriceProductPage, "s");

        Assert.assertEquals(colorDiscountPriceMainPage, "rgb(204, 0, 0)");
        Assert.assertEquals(colorDiscountPriceProductPage, "rgb(204, 0, 0)");
        Assert.assertEquals(colorRegularPriceMainPage, "rgb(119, 119, 119)");
        Assert.assertEquals(colorRegularPriceProductPage, "rgb(102, 102, 102)");
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }
}
