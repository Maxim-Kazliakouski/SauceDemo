package Pages.Checkout;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;

public class YourInformationPage extends BasePage {

    private final By TITLE_YOUR_INFO = By.xpath("//span[contains(text(),'Checkout: Your Information')]");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By X_BUTTON = By.className("error-button");
    private final By ERROR_CONTAINER = By.xpath("//div[@class='error-message-container error']");
    private final By ERROR_MESSAGE = By.xpath("//h3");


    public YourInformationPage(WebDriver browser) {
        super(browser);
    }

    private final By FIRST_NAME = By.id("first-name");
    private final By LAST_NAME = By.id("last-name");
    private final By ZIP = By.id("postal-code");

    public void open() {
        browser.get(BASE_URL + "checkout-step-one.html");
    }

    public boolean isOpened() {
        return waitForVisibility(TITLE_YOUR_INFO);
    }

    public boolean isElementVisible() {
        return waitForVisibility(ERROR_CONTAINER);
    }

    public void changeExplicitlyWait(int timeOut) {
        wait = new WebDriverWait(browser, Duration.ofSeconds(timeOut));

    }

    public void fillCheckoutFields(String firstName, String lastName, String zip) {
        browser.findElement(FIRST_NAME).sendKeys(firstName);
        browser.findElement(LAST_NAME).sendKeys(lastName);
        browser.findElement(ZIP).sendKeys(zip);
    }

    public void clickContinue() {
        browser.findElement(CONTINUE_BUTTON).click();
    }

    public boolean errorIsAppeared() {
        return waitForVisibility(ERROR_MESSAGE);
    }

    public boolean correctErrorMessageText(String missingField) {
        String errorText = browser.findElement(ERROR_MESSAGE).getText();
        System.out.println(errorText);
        return errorText.equals(format("Error: %s is required", missingField));
    }

    public void closeErrorMessage() {
        browser.findElement(X_BUTTON).click();
    }
}
