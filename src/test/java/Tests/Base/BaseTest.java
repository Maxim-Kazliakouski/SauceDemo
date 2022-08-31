package Tests.Base;

import Pages.CartPage;
import Pages.Checkout.CompletePage;
import Pages.Checkout.OverviewPage;
import Pages.Checkout.YourInformationPage;
import Pages.LoginPage;
import Pages.ProductPage;
import Tests.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import java.util.Map;
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

    @Parameters({"browserType"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browserType) {
        if (browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            browser = new ChromeDriver(options);
        } else if (browserType.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
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

