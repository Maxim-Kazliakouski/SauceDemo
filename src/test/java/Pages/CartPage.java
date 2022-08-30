package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

public class CartPage extends BasePage {

    private final By TITLE_CART = By.xpath("//span[contains(text(),'Your Cart')]");

    public CartPage(WebDriver browser) {
        super(browser);
    }

    public String getProductName(String productName) {
        return browser.findElement(By.xpath(format("//div[@class='inventory_item_name'][text()='%s']", productName))).getText();
    }

    public void removeProduct(String productName) {
        browser.findElement(By.xpath(format("//div[@class='inventory_item_name'][contains(text(), '%s')]//..//..//button", productName))).click();
    }

    public boolean isOpened() {
        return waitForVisibility(TITLE_CART);
    }

    public boolean isProductVisible(String productName) {
        return waitForVisibility(By.xpath(format("//div[@class='inventory_item_name'][text()='%s']", productName)));
    }

    public String getPrice(String product) {
        return browser.findElement(By.xpath(format("//div[contains(text(), '%s')]//following::div[@class='inventory_item_price']", product))).getText();
    }

    public void clickCheckout() {
        browser.findElement(By.id("checkout")).click();
    }
}
