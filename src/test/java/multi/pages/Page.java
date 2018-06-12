package multi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String _APP_URL;

    public Page(WebDriver driver) {

        _APP_URL = "http://localhost/litecart/en/";
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }
}
