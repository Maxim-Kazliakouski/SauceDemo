package Pages.Checkout;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage extends BasePage {

    private final By FINISH_BUTTON = By.id("finish");
    private final By TITLE_OVERVIEW = By.xpath("//span[contains(text(),'Checkout: Overview')]");
    private final By ITEM_PRICE = By.className("summary_subtotal_label");
    private final By TAX = By.className("summary_tax_label");
    private final By TOTAL_PRICE = By.className("summary_total_label");

    public OverviewPage(WebDriver browser) {
        super(browser);
    }

    public void open() {
        String OVERVIEW_PAGE_URL = BASE_URL + "checkout-step-two.html";
        browser.get(OVERVIEW_PAGE_URL);
    }

    public boolean isOpened() {
        return waitForVisibility(TITLE_OVERVIEW);
    }

    public String getItemPrice() {
        return browser.findElement(ITEM_PRICE).getText().substring(13);
    }

    public String getTax() {
        return browser.findElement(TAX).getText().substring(6);
    }

    public String calculateTax() {
        String price = getItemPrice();
        double priceDouble = Double.parseDouble(price) * 0.08;
        return String.format("%.2f", priceDouble).replace(",", ".");
    }

    public String getTotalPrice() {
        return browser.findElement(TOTAL_PRICE).getText().substring(8);
    }

    public String calculateTotalSum() {
        return String.valueOf(Double.parseDouble(calculateTax()) + Double.parseDouble(getItemPrice()));
    }

    public void clickFinish() {
        browser.findElement(FINISH_BUTTON).click();
    }
}
