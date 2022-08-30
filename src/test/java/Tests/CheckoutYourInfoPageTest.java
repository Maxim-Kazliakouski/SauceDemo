package Tests;

import Pages.BasePage;
import Tests.Base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckoutYourInfoPageTest extends BaseTest {

    @Test
    public void redirectionToOverviewPage() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        yourInformationPage.open();
        assertTrue(yourInformationPage.isOpened(), "User hasn't been redirected to the checkout confirmation page");
        yourInformationPage.fillCheckoutFields("Ivan", "Ivanov", "200100");
        yourInformationPage.clickContinue();
        assertTrue(overviewPage.isOpened(), "Checkout: Overview page wasn't opened!");
    }

    @Test
    public void errorMessageAfterMissingRequiredFieldFirstName() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        yourInformationPage.open();
        assertTrue(yourInformationPage.isOpened(), "User hasn't been redirected to the checkout confirmation page");
        yourInformationPage.fillCheckoutFields("", "Ivanov", "200100");
        yourInformationPage.clickContinue();
        assertTrue(yourInformationPage.errorIsAppeared(), "There is no any error message after " +
                "missing required field 'First Name'");
        assertTrue(yourInformationPage.correctErrorMessageText("First Name"), "Error message text has been changed");
    }

    @Test
    public void errorMessageAfterMissingRequiredFieldLastName() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        yourInformationPage.open();
        assertTrue(yourInformationPage.isOpened(), "User hasn't been redirected to the checkout confirmation page");
        yourInformationPage.fillCheckoutFields("Ivan", "", "200100");
        yourInformationPage.clickContinue();
        assertTrue(yourInformationPage.errorIsAppeared(), "There is no any error message after " +
                "missing required field 'Last Name'");
        assertTrue(yourInformationPage.correctErrorMessageText("Last Name"), "Error message text has been changed!");
    }

    @Test
    public void errorMessageAfterMissingRequiredFieldZip() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        yourInformationPage.open();
        assertTrue(yourInformationPage.isOpened(), "User hasn't been redirected to the checkout confirmation page");
        yourInformationPage.fillCheckoutFields("Ivan", "Ivanov", "");
        yourInformationPage.clickContinue();
        assertTrue(yourInformationPage.errorIsAppeared(), "There is no any error message after " +
                "missing required field 'Postal Code'");
        assertTrue(yourInformationPage.correctErrorMessageText("Postal Code"), "Error message text has been changed!");
    }

    @Test
    public void closeErrorMessage() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        yourInformationPage.open();
        assertTrue(yourInformationPage.isOpened(), "User hasn't been redirected to the checkout confirmation page");
        yourInformationPage.fillCheckoutFields("Ivan", "Ivanov", "");
        yourInformationPage.clickContinue();
        yourInformationPage.closeErrorMessage();
        yourInformationPage.changeExplicitlyWait(1);
        assertFalse(yourInformationPage.isElementVisible(), "The error message still visible!");
        yourInformationPage.changeExplicitlyWait(10);
    }
}
