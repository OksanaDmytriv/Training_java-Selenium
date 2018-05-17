import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Homework_1 {

    @Test
    public void firstTest() {
        ChromeDriverManager.getInstance().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");

        driver.quit();
    }
}
