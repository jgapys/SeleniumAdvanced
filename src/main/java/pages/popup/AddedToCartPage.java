package pages.popup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class AddedToCartPage extends BasePage {

    public AddedToCartPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(AddedToCartPage.class);

    @FindBy(className = "modal-title")
    private WebElement modalTitle;

    @FindBy(className = "product-name")
    private WebElement productName;

    @FindBy(css = ".modal-body .product-price")
    private WebElement productPrice;

    @FindBy(css = ".product-quantity strong")
    private WebElement productQuantity;

    @FindBy(css = ".modal-body .cart-products-count")
    private WebElement itemInCartInfo;

    @FindBy(className = "subtotal")
    private WebElement subtotalPrice;

    @FindBy(className = "btn-secondary")
    private WebElement continueBTn;

    public void waitingForModalDialog() {
        waitToBeVisible(modalTitle);
    }

    public String getProductName() {
        String productName = getElementText(this.productName);
        logger.info(productName);
        return productName;
    }

    public double getProductPrice() {
        double productPrice = getPrice(this.productPrice);
        logger.info(String.valueOf(productPrice));
        return productPrice;
    }

    public int getProductQuantity() {
        String productQuantity = getElementText(this.productQuantity);
        logger.info(productQuantity);
        return Integer.parseInt(productQuantity);
    }

    public String getItemsInCartInfo() {
        String itemsInCarInfo = getElementText(this.itemInCartInfo);
        logger.info(itemsInCarInfo);
        return itemsInCarInfo;
    }

    public double getTotalProductsPrice() {
        double totalProductsPrice = getPrice(subtotalPrice);
        logger.info(String.valueOf(totalProductsPrice));
        return totalProductsPrice;
    }

    public void clickContinueShoppingBtn() {
        click(continueBTn);
    }
}
