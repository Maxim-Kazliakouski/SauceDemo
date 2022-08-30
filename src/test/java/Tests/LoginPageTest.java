package Tests;

import Pages.BasePage;
import Tests.Base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPageTest extends BaseTest {

    @Test
    public void successfulLogin() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened!");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
    }

    @Test
    public void wrongPassword() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened!");
        loginPage.login(BasePage.USERNAME, "wrong_password");
        assertEquals(loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Wrong error message shown");
    }

    @Test
    public void emptyUserName() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened!");
        loginPage.login("", BasePage.PASSWORD);
        assertEquals(loginPage.getError(),
                "Epic sadface: Username is required",
                "Wrong error message shown");
    }
}
