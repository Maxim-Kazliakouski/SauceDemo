package Tests;

import Pages.BasePage;
import Tests.Base.BaseTest;
import org.testng.annotations.Test;

import static java.lang.String.format;

import static org.testng.Assert.*;

public class CartPageTest extends BaseTest {
    String testProductName1 = "Sauce Labs Onesie";
    String testProductPrice1 = "$7.99";

    @Test
    public void productNameAndPriceInCartShouldBeCorrect() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        productPage.open();
        assertTrue(productPage.isOpened(), "Product page wasn't opened");
        productPage.addToCart(testProductName1);
        productPage.redirectionToCart();
        assertTrue(cartPage.isOpened(), "Cart page wasn't opened!");
        String title = cartPage.getProductName(testProductName1);
        String price = cartPage.getPrice(testProductName1);
        assertEquals(title, testProductName1, "In cart there is no title of product that user has added");
        assertEquals(price, testProductPrice1, "The price of added product doesn't match with correct price");
    }

    @Test
    public void userShouldBeRedirectToCheckoutPage() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        productPage.open();
        assertTrue(productPage.isOpened(), "Product page wasn't opened");
        productPage.addToCart(testProductName1);
        productPage.redirectionToCart();
        assertTrue(cartPage.isOpened(), "Cart page wasn't opened!");
        cartPage.clickCheckout();
        assertTrue(yourInformationPage.isOpened(), "User hasn't been redirected to the checkout confirmation page");
    }

    @Test
    public void removeProductFromCart() {
        String testProductName2 = "Sauce Labs Fleece Jacket";
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        productPage.open();
        assertTrue(productPage.isOpened(), "Product page wasn't opened");
        productPage.addToCart(testProductName1);
        productPage.addToCart(testProductName2);
        productPage.redirectionToCart();
        cartPage.removeProduct(testProductName1);
        assertFalse(cartPage.isProductVisible(testProductName1), format("The product '%s' hasn't been deleted from cart", testProductName1));
    }
}
