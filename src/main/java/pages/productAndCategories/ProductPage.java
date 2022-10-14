package pages.productAndCategories;

import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(className = "add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(css = "span[itemprop='price']")
    private WebElement currentPrice;

    @FindBy(css = "h1")
    private WebElement productName;

    public int getQuantityInputValue() {
        int productQuantity = Integer.parseInt(quantityInput.getAttribute("value"));
        logger.info("Actual product quantity: {}", productQuantity);
        return productQuantity;
    }

    public void setProductQuantity(int quantity) {
        getQuantityInputValue();
        clearInput(quantityInput);
        sendKeys(quantityInput, String.valueOf(quantity));
        logger.info("Set quantity to: {}", quantity);
    }

    public void addProductToCart() {
        click(addToCartBtn);
    }

    public double getCurrentPrice() {
        return getPriceAndLog(currentPrice);
    }

    public String getProductName() {
        return getElementText(productName);
    }

    public Product toProductDetails() {
        return Product.productBuilder()
                .name(getProductName())
                .quantity(getQuantityInputValue())
                .quantityPrice(getCurrentPrice())
                .totalPrice(getCurrentPrice() * getQuantityInputValue())
                .build();
    }
}