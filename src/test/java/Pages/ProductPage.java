package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

public class ProductPage extends BasePage {

    private final By PAGE_TITLE = By.xpath("//span[contains(text(),'Products')]");

    public ProductPage(WebDriver browser) {
        super(browser);
    }

    public void open() {
        browser.get(BASE_URL + "inventory.html");
    }

    public boolean isOpened() {
        return waitForVisibility(PAGE_TITLE);
    }

    public void addToCart(String productName) {
        browser.findElement(By.xpath(format("//div[contains(text(), '%s')]//ancestor::div[@class='inventory_item']//button", productName))).click();
    }

    public boolean isButtonRemoveVisible(String productName) {
        return ((browser.findElement(By.xpath(format("//div[contains(text(), '%s')]//ancestor::div[@class='inventory_item']//button", productName))).getText().equals("REMOVE")));
    }

    public void selectSortingType(String value) {
        Select dropdownSorting = new Select(browser.findElement(By.className("product_sort_container")));
        dropdownSorting.selectByValue(value);
    }

    public final List<String> getProductsList() {
        List<WebElement> list = browser.findElements(By.xpath("//div[@class='inventory_item_name']"));
        List<String> productList = new ArrayList<>();
        for (WebElement eachProduct : list) {
            productList.add(eachProduct.getText());
        }
        return productList;
    }

    public List<String> sortedProductListAToZByJava() {
        List<String> sortedProductList = getProductsList();
        Collections.sort(sortedProductList);
        return sortedProductList;
    }

    public List<String> sortedProductListZToAByJava() {
        List<String> sortedProductList = getProductsList();
        Collections.reverse(sortedProductList);
        return sortedProductList;
    }

    public final List<Double> getProductPrices() {
        List<WebElement> list = browser.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> productPriceList = new ArrayList<>();
        for (WebElement eachProductPrice : list) {
            productPriceList.add(Double.parseDouble(eachProductPrice.getText().substring(1)));
        }
        return productPriceList;
    }

    public List<Double> sortedProductPriceLoHi() {
        List<Double> sortedProductPriceList = getProductPrices();
        Collections.sort(sortedProductPriceList);
        return sortedProductPriceList;
    }

    public List<Double> sortedProductPriceHiLo() {
        List<Double> sortedProductPriceList = getProductPrices();
        Collections.sort(sortedProductPriceList);
        Collections.reverse(sortedProductPriceList);
        return sortedProductPriceList;
    }
}
