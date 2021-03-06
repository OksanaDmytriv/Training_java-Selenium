import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class Homework_8 {
    public int sideMenuSize;
    static EventFiringWebDriver driver;

    public void login() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public void clickOnLogo() {
        driver.findElement(By.className("logotype")).click();
    }

    public List<WebElement> getFullSideMenu() {
        return driver.findElement(By.id("box-apps-menu")).findElements(By.tagName("li"));
    }


    public WebElement getSideMenuElementByNumber(int count) {
        return getFullSideMenu().get(count);
    }

    public boolean checkIfDropDownExist(int count) {
        try {
            getDropDownMenu(count);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public List<WebElement> getDropDownMenu(int count) {
        return getSideMenuElementByNumber(count).findElement(By.className("docs")).findElements(By.tagName("li"));
    }

    public Integer dropDownMenuSize(int count) {
        return getDropDownMenu(count).size();
    }

    @BeforeClass
    public static void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new EventsListener());
    }

    @Test
    public void secondTest() {
        login();
        sideMenuSize = getFullSideMenu().size();
        for (int i = 0; i < sideMenuSize; i++) {

            clickOnLogo();
            WebElement sideMenuElement = getSideMenuElementByNumber(i);
            sideMenuElement.click();
            if (checkIfDropDownExist(i) != false) {
                Integer dropDownElementsSize = dropDownMenuSize(i);
                for (int k = 0; k < dropDownElementsSize; k++) {
                    getDropDownMenu(i).get(k).click();
                    driver.findElement(By.tagName("h11"));
                }
            } else {
                driver.findElement(By.tagName("h1"));
            }
        }
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }

}
