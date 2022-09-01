package tests.base;

import pages.CartPage;
import pages.checkout.CompletePage;
import pages.checkout.OverviewPage;
import pages.checkout.YourInformationPage;
import pages.LoginPage;
import pages.ProductPage;
import tests.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    public WebDriver browser;
    public LoginPage loginPage;
    public ProductPage productPage;
    public CartPage cartPage;
    public YourInformationPage yourInformationPage;
    public OverviewPage overviewPage;
    public CompletePage completePage;

    @Parameters({"browserType", "headlessMode"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browserType, @Optional("true") String headlessMode) {
        if (browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(headlessMode.equals("true"));
            browser = new ChromeDriver(options);
        } else if (browserType.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(headlessMode.equals("true"));
            browser = new FirefoxDriver(options);
        }
        loginPage = new LoginPage(browser);
        productPage = new ProductPage(browser);
        cartPage = new CartPage(browser);
        yourInformationPage = new YourInformationPage(browser);
        overviewPage = new OverviewPage(browser);
        completePage = new CompletePage(browser);
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (browser != null) {
            browser.quit();
        }
    }

    public void login(String USERNAME, String password) {
        browser.findElement(By.id("user-name")).sendKeys(USERNAME);
        browser.findElement(By.id("password")).sendKeys(password);
        browser.findElement(By.id("login-button")).click();
    }
}

