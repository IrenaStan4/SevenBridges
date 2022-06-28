import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task2 {

    //2. Use Selenium to access to https://www.amazon.com/, search for Guitar, and wait until all
    //items are loaded.

    WebDriver driver;
    JavascriptExecutor js;
    List<WebElement> allElements;
    List<String> allElementsGetText = new ArrayList<>();

    @Before
    public void setupMethod() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com");
        js = (JavascriptExecutor) driver;

    }

    @After
    public void tearDownMethod() {
        driver.close();
    }
}
