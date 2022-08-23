import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver browser;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        browser = new ChromeDriver(options);
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        browser.quit();
    }

    public void login(String username, String password) {
        browser.findElement(By.id("user-name")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
        browser.findElement(By.id("login-button")).click();
    }

    public static String priceOfGood(Map<String, String> map, String goodName) {
        return map.get(goodName);
    }

}

