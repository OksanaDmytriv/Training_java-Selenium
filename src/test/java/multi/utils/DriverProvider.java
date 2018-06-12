package multi.utils;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.qatools.properties.PropertyLoader;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverProvider {

    private static ConfigReader config = PropertyLoader.newInstance()
            .populate(ConfigReader.class);

    public static WebDriver getDriver() {
        return getDriver(config.getBrowserName(), config.isRemote());
    }

    public static WebDriver getDriver(String browser) {
        return getDriver(browser, false);
    }

    public static WebDriver getDriver(String browser, boolean remote) {

        if (remote) {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("browserName", browser);

            try {
                return new RemoteWebDriver(new URL(config.getRemoteBrowserUrl()),
                        cap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            if (browser.equals("chrome")) {
                ChromeDriverManager.getInstance().setup();
                return new ChromeDriver();
            }
            if (browser.equals("firefox")) {
                FirefoxDriverManager.getInstance().setup();
                return new FirefoxDriver();
            }
            if (browser.equals("edge")) {
                EdgeDriverManager.getInstance().setup();
                return new EdgeDriver();
            }

            if (browser.equals("ie")) {
                InternetExplorerDriverManager.getInstance().arch32().setup();
                return new InternetExplorerDriver();
            }
        }

        System.out.println("Unsupported browser ["+ browser +"]");
        return null;
    }
}