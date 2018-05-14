import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Homework_1 {

    @Test
    public void firstTest()
    {
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");

        driver.quit();
    }
}
