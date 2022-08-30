package Tests;

import Pages.BasePage;
import Tests.Base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CheckoutCompletePageTest extends BaseTest {

    @Test
    public void confirmationMessage() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        completePage.open();
        assertTrue(completePage.isOpened(), "Checkout: Complete page wasn't opened!");
        assertTrue(completePage.isHeaderTextVisible(), "There is no complete header!");
    }

    @Test
    public void redirectionToProductPageFromCompletePage() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        assertTrue(loginPage.isOpened(), "Login page wasn't opened!");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        completePage.open();
        assertTrue(completePage.isOpened(), "Checkout: Complete page wasn't opened!");
        completePage.clickBackToHome();
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
    }
}
