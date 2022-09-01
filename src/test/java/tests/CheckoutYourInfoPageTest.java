package tests;

import pages.BasePage;
import tests.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckoutYourInfoPageTest extends BaseTest {

    @Test(description = "User should be redirect to the Overview page from Your Info page after clicking on 'Continue' button")
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
    @DataProvider
    public Object[][] errorMessage(){
        return new Object[][]{
                {"", "Ivanov", "200100", "Error: First Name is required"},
                {"Ivan", "", "200100", "Error: Last Name is required"},
                {"Ivan", "Ivanov", "", "Error: Postal Code is required"}
        };
    }
    @Test(dataProvider = "errorMessage", description = "Error message should be appear, if user doesn't fill required field")
    public void errorMessageAfterMissingRequiredField(String firstName, String lastName, String zip, String errorMessage) {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        yourInformationPage.open();
        assertTrue(yourInformationPage.isOpened(), "User hasn't been redirected to the checkout confirmation page");
        yourInformationPage.fillCheckoutFields(firstName, lastName, zip);
        yourInformationPage.clickContinue();
        assertTrue(yourInformationPage.isErrorAppeared(), "There is no any error message after " +
                "missing required field!");
        assertEquals(yourInformationPage.getErrorMessage(), errorMessage, "Error message text has been changed");
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
        assertFalse(yourInformationPage.isErrorVisible(), "The error message still visible!");
        yourInformationPage.changeExplicitlyWait(10);
    }
}
