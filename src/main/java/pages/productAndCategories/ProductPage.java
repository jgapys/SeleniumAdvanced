package pages.productAndCategories;

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

    @FindBy(className = "touchspin-up")
    private WebElement quantityArrowUp;

    @FindBy(className = "touchspin-down")
    private WebElement quantityArrowDown;

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
        int actualInputValue = getQuantityInputValue();
        int clicksNumber = Math.abs(actualInputValue - quantity);
        if (actualInputValue < quantity) {
            changeProductQuantity(quantityArrowUp, clicksNumber);
        } else {
            changeProductQuantity(quantityArrowDown, clicksNumber);
        }
        getQuantityInputValue();
    }

    private void changeProductQuantity(WebElement quantityArrow, int clicksNumber) {
        for (int i = 0; i < clicksNumber; i++) {
            logger.info("Number of arrow clicks: {}", i + 1);
            quantityArrow.click();
        }
    }

    public void addProductToCart() {
        click(addToCartBtn);
    }

    public double getCurrentPrice() {
        double currentPrice = getPrice(this.currentPrice);
        logger.info("Current product price: {}", currentPrice);
        return currentPrice;
    }

    public String getProductName(){
        return getElementText(productName);
    }
}
