import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class AddProductIntoCartTest extends BaseTest {
    final static String url = "https://www.saucedemo.com";
    final static String username = "standard_user";
    final static String password = "secret_sauce";
    static String testProductName;
    static String testPrice;
    final static Map<String, String> testProductAndPrice = new HashMap<>();

    {
        testProductAndPrice.put("Sauce Labs Backpack", "$29.99");
        testProductAndPrice.put("Sauce Labs Bike Light", "$29.99");
        testProductAndPrice.put("Sauce Labs Bolt T-Shirt", "$15.99");
        testProductAndPrice.put("Sauce Labs Fleece Jacket", "$49.99");
        testProductAndPrice.put("Sauce Labs Onesie", "$7.99");
        testProductAndPrice.put("Test.allTheThings() T-Shirt (Red)", "$15.99");
    }

    @Test
    public void addProductIntoCart() {
        testProductName = "Sauce Labs Onesie";
        testPrice = priceOfGood(testProductAndPrice, testProductName);
        browser.get(url);
        login(username, password);
        browser.findElement(By.xpath(format("//div[contains(text(), '%s')]//ancestor::div[@class='inventory_item']//button", testProductName))).click();
        browser.findElement(By.className("shopping_cart_link")).click();
        WebElement titleOfGoodInCart = browser.findElement(By.xpath(format("//div[contains(text(), '%s')]", testProductName)));
        WebElement getPrice = browser.findElement(By.xpath(format("//div[contains(text(), '%s')]//following::div[@class='inventory_item_price']", testProductName)));
        String productName = titleOfGoodInCart.getText();
        String price = getPrice.getText();
        assertEquals(productName, testProductName, "Another product name --> " + productName);
        assertEquals(price, testPrice, "Another price --> " + price);
    }
}
