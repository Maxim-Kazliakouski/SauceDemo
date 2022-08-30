package Tests.Base;

import Pages.CartPage;
import Pages.Checkout.CompletePage;
import Pages.Checkout.OverviewPage;
import Pages.Checkout.YourInformationPage;
import Pages.LoginPage;
import Pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver browser;
    public LoginPage loginPage;
    public ProductPage productPage;
    public CartPage cartPage;
    public YourInformationPage yourInformationPage;
    public OverviewPage overviewPage;
    public CompletePage completePage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        browser = new ChromeDriver(options);
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
        browser.quit();
    }

    public void login(String USERNAME, String password) {
        browser.findElement(By.id("user-name")).sendKeys(USERNAME);
        browser.findElement(By.id("password")).sendKeys(password);
        browser.findElement(By.id("login-button")).click();
    }
}

