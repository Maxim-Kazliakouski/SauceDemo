package Tests;

import Pages.BasePage;
import Tests.Base.BaseTest;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutOverviewPageTest extends BaseTest {

    @Test(description = "User should be redirect to the Complete page from Your information page")
    public void successfulRedirectionToCompletePage() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        yourInformationPage.open();
        assertTrue(yourInformationPage.isOpened(), "User hasn't been redirected to the checkout confirmation page");
        yourInformationPage.fillCheckoutFields("Ivan", "Ivanov", "200100");
        yourInformationPage.clickContinue();
        assertTrue(overviewPage.isOpened(), "Checkout: Overview page wasn't opened!");
        overviewPage.clickFinish();
        assertTrue(completePage.isOpened(), "The complete page wasn't opened!");
    }

    @Test(description = "The product price at Overview page should be $0, if user hasn't added anything")
    public void checkProductPriceWhenCartIsEmpty() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        overviewPage.open();
        assertTrue(overviewPage.isOpened(), "Checkout: Overview page wasn't opened!");
        assertEquals("$" + overviewPage.getItemPrice(), "$0", "Item price with empty cart is --> " + overviewPage.getItemPrice());
    }

    @Test(description = "The tax at Overview page should be $0.00, if user hasn't added anything")
    public void checkTaxWhenCartIsEmpty() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        overviewPage.open();
        assertTrue(overviewPage.isOpened(), "Checkout: Overview page wasn't opened!");
        assertEquals("$" + overviewPage.getTax(), "$0.00", "Tax with empty cart is --> " + overviewPage.getTax());
    }

    @Test(description = "The total price at Overview page should be $0.00, if user hasn't added anything")
    public void checkTotalPriceWhenCartIsEmpty() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        overviewPage.open();
        assertTrue(overviewPage.isOpened(), "Checkout: Overview page wasn't opened!");
        assertEquals("$" + overviewPage.getTotalPrice(), "$0.00", "Total price with empty cart is --> " + overviewPage.getTotalPrice());
    }

    @Test(description = "Product price at Overview page should be change, if user add some product into the cart")
    public void checkProductPrice() {
        String testItemName = "Sauce Labs Bike Light";
        String testItemPrice = "$9.99";
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        productPage.addToCart(testItemName);
        overviewPage.open();
        assertTrue(overviewPage.isOpened(), "Checkout: Overview page hasn't been opened!");
        assertEquals("$" + overviewPage.getItemPrice(), testItemPrice, format("The price of '%s' is differ --> ", testItemName) + overviewPage.getItemPrice());
    }

    @Test(description = "Tax at Overview page should be change, if user add some product into the cart")
    public void checkTax() {
        String testItemName = "Sauce Labs Bolt T-Shirt";
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        productPage.addToCart(testItemName);
        overviewPage.open();
        assertTrue(overviewPage.isOpened(), "Checkout: Overview page hasn't been opened!");
        assertEquals("$" + overviewPage.getTax(), "$" + overviewPage.calculateTax(), format("The tax of '%s' is differ --> ", testItemName) + "$" + overviewPage.getTax());
    }

    @Test(description = "Total price at Overview page should be change, if user add some product into the cart")
    public void checkTotalPrice() {
        String testItemName = "Sauce Labs Onesie";
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        productPage.addToCart(testItemName);
        overviewPage.open();
        assertTrue(overviewPage.isOpened(), "Checkout: Overview page hasn't been opened!");
        assertEquals("$" + overviewPage.getTotalPrice(), "$" + overviewPage.calculateTotalSum(), format("The total sum of '%s' is differ --> ", testItemName) + "$" + overviewPage.getTotalPrice());
        System.out.println(overviewPage.calculateTotalSum());
    }
}