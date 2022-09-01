package pages.checkout;

import pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourInformationPage extends BasePage {

    private final By TITLE_YOUR_INFO = By.xpath("//span[contains(text(),'Checkout: Your Information')]");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By X_BUTTON = By.className("error-button");
    private final By ERROR_CONTAINER = By.xpath("//div[@class='error-message-container error']");
    private final By ERROR_MESSAGE = By.xpath("//h3");
    private final By FIRST_NAME = By.id("first-name");
    private final By LAST_NAME = By.id("last-name");
    private final By ZIP = By.id("postal-code");

    public YourInformationPage(WebDriver browser) {
        super(browser);
    }

    public void open() {
        browser.get(BASE_URL + "checkout-step-one.html");
    }

    public boolean isOpened() {
        return waitForVisibility(TITLE_YOUR_INFO);
    }

    public boolean isErrorVisible() {
        return waitForVisibility(ERROR_CONTAINER);
    }

    public void fillCheckoutFields(String firstName, String lastName, String zip) {
        browser.findElement(FIRST_NAME).sendKeys(firstName);
        browser.findElement(LAST_NAME).sendKeys(lastName);
        browser.findElement(ZIP).sendKeys(zip);
    }

    public void clickContinue() {
        browser.findElement(CONTINUE_BUTTON).click();
    }

    public boolean isErrorAppeared() {
        return waitForVisibility(ERROR_MESSAGE);
    }

    public String getErrorMessage() {
        return browser.findElement(ERROR_MESSAGE).getText();
    }

    public void closeErrorMessage() {
        browser.findElement(X_BUTTON).click();
    }
}
