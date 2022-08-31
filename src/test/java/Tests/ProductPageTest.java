package Tests;

import Pages.BasePage;
import Tests.Base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductPageTest extends BaseTest {

    @Test(description = "Checking that button 'Add to cart' changes on 'Remove' after clicking on it")
    public void changeAddToCartButtonOnRemove() {
        String testProductName1 = "Sauce Labs Onesie";
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        productPage.addToCart(testProductName1);
        assertTrue(productPage.isButtonRemoveVisible(testProductName1), "Button 'ADD TO CART' hasn't " +
                "been changed on 'REMOVE' after clicking on it!");
    }

    @Test(description = "Product sorting from A to Z at the Product page")
    public void sortingAToZ() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        List<String> sortByJava = productPage.sortedProductListAToZByJava();
        productPage.selectSortingType("az");
        assertEquals(productPage.getProductsList(), productPage.sortedProductListAToZByJava(), "Sorted product list A-Z at website is differ from test data sorting!\n");
    }

    @Test(description = "Product sorting from Z to A at the Product page")
    public void sortingZToA() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        List<String> sortByJava = productPage.sortedProductListZToAByJava();
        productPage.selectSortingType("za");
        assertEquals(productPage.getProductsList(), sortByJava, "Sorted product list Z-A at website is differ from test data sorting!\n");
    }

    @Test(description = "Product price sorting from Low to High at the Product page")
    public void sortingPriceLoHi() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        List<Double> sortPriceLoHiByJava = productPage.sortedProductPriceLoHi();
        productPage.selectSortingType("lohi");
        assertEquals(productPage.getProductPrices(), sortPriceLoHiByJava, "Sorted product list by price Low-High at website is differ from test data sorting!\n");
    }

    @Test(description = "Product price sorting from High to Low at the Product page")
    public void sortingPriceHiLo() {
        loginPage.open();
        assertTrue(loginPage.isOpened(), "Login page wasn't opened");
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productPage.isOpened(), "Product page wasn't opened!");
        List<Double> sortPriceHiLoByJava = productPage.sortedProductPriceHiLo();
        productPage.selectSortingType("hilo");
        assertEquals(productPage.getProductPrices(), sortPriceHiLoByJava, "Sorted product list by price" +
                " High-Low at website is differ from test data sorting!\n");
    }
}
