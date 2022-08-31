package Tests;

import Pages.BasePage;
import Tests.Base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPageTest extends BaseTest {
    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"performance_glitch_user", "asdsads", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"}
        };
    }

    @Test
    public void successfulLogin() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened!");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
    }

    @Test(dataProvider = "loginData")
    public void negativeLogin(String userName, String password, String error) {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened!");
        loginPage.login(userName, password);
        assertEquals(loginPage.getError(),
                error,
                "Wrong error message shown");
    }
}
