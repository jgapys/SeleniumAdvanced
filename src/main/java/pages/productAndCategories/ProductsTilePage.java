package pages.productAndCategories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class ProductsTilePage extends BasePage {

    public ProductsTilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-title a")
    private WebElement productTitle;

    @FindBy(className = "price")
    private WebElement productPrice;

    public String getProductTitle(WebElement product) {
        return getElementText(product.findElement(By.cssSelector(".product-title a")));
    }

    public String getSearchedProductTitle() {
        return getElementText(productTitle);
    }

    public double getProductPrice() {
        return getPriceAndLog(productPrice);
    }
}