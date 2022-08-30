package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    public WebDriver browser;
    public WebDriverWait wait;
    public static final String BASE_URL = "https://www.saucedemo.com/";
    static final By CART = By.id("shopping_cart_container");
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";


    public BasePage(WebDriver browser) {
        this.browser = browser;
        wait = new WebDriverWait(browser, Duration.ofSeconds(5));
    }

    public boolean waitForVisibility(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    public void clickJS(By locator) {
        ((JavascriptExecutor) browser).executeScript("arguments[0].click();", browser.findElement(locator));
    }

    public void redirectionToCart() {
        browser.findElement(CART).click();
    }
}
