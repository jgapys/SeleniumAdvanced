package pages.productAndCategories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class ProductsTilePage extends BasePage {

    public ProductsTilePage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductsTilePage.class);

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

    public int getProductPrice() {
        int productPrice = Integer.parseInt(getElementText(this.productPrice).replaceAll("\\$|\\.00", ""));
        logger.info("Product price: {}", productPrice);
        return productPrice;
    }
}