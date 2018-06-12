package multi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get(_APP_URL);
        return this;
    }

    @FindBy(css = "#box-most-popular ul[class='listing-wrapper products'] li")
    private List<WebElement> firstElement;

    @FindBy(name = "add_cart_product")
    private WebElement addToCardButton;

    @FindBy(css = "#cart a.content span.quantity")
    private WebElement cartQuantityText;

    @FindBy(css = "#cart .link")
    private WebElement cart;

    public String getCartText(){
        return cartQuantityText.getText();
    }

    public void addProductToCart(String num) {
        addToCardButton.click();
        wait.until((WebDriver driver) -> getCartText().equals(num));
    }

    public void addRandomProductToCart(Integer num) {
        driver.findElements(By.cssSelector("#box-most-popular ul[class='listing-wrapper products'] li")).get(0).click();
        addProductToCart(Integer.toString(num));
    }

    public void openCart() {
        cart.click();
    }

    public void checkProductsRemoved(){
        wait.until((WebDriver driver) -> getCartText().equals("0"));
    }
}
