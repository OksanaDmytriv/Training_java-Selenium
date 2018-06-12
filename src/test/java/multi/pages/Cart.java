package multi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Cart extends Page {
    public Cart(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='remove_cart_item']")
    private WebElement removeButton;

    public void removeProductFromCart() {
        WebElement table = driver.findElement(By.cssSelector("table[class='dataTable rounded-corners']"));
        wait.until((WebDriver driver) -> removeButton.isEnabled());
        removeButton.click();
        wait.until(ExpectedConditions.stalenessOf(table));
    }
}
