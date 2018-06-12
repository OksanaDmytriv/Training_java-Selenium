package multi.app;

import multi.pages.Cart;
import multi.pages.MainPage;
import multi.utils.ConfigReader;
import multi.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import ru.qatools.properties.PropertyLoader;

public class Application {
    private WebDriver driver;

    private MainPage mainPage;
    private Cart cartPage;

    private static ConfigReader config = PropertyLoader.newInstance()
            .populate(ConfigReader.class);

    public Application() {

        driver = DriverProvider.getDriver();

        mainPage = new MainPage(driver);
        cartPage = new Cart(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductToCard(Integer num) {
        for (int i = 0; i < num; i++) {
            mainPage.open();
            mainPage.addRandomProductToCart(i + 1);
        }
    }

    public void removeProductFromCart(Integer num) {
        mainPage.openCart();
        for (int i = 0; i < num; i++) {
            cartPage.removeProductFromCart();
        }
    }

    public void checkCartEmpty() {
        mainPage.open();
        mainPage.checkProductsRemoved();
    }
}
