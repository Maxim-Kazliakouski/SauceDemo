package pages.checkout;

import pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletePage extends BasePage {
    private final By TITLE_COMPLETE = By.xpath("//span[contains(text(),'Checkout: Complete!')]");
    public final By COMPLETE_HEADER = By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]");
    private final By BACK_HOME_BUTTON = By.id("back-to-products");

    public CompletePage(WebDriver browser) {
        super(browser);
    }

    public void open() {
        String COMPLETE_PAGE_URL = BASE_URL + "checkout-complete.html";
        browser.get(COMPLETE_PAGE_URL);
    }

    public boolean isOpened() {
        return waitForVisibility(TITLE_COMPLETE);
    }

    public boolean isHeaderTextVisible() {
        return waitForVisibility(COMPLETE_HEADER);
    }

    public void clickBackToHome() {
        browser.findElement(BACK_HOME_BUTTON).click();
    }
}
