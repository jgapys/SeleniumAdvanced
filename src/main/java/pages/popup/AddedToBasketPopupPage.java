package pages.popup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class AddedToBasketPopupPage extends BasePage {

    public AddedToBasketPopupPage(WebDriver driver) {
        super(driver);
    }

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

    @FindBy(css = ".cart-content-btn .btn-primary")
    private WebElement proceedToCheckoutBTn;

    public void waitingForModalDialog() {
        waitToBeVisible(modalTitle);
    }

    public String getProductName() {
        return getElementText(productName);
    }

    public double getProductPrice() {
        return getPriceAndLog(productPrice);
    }

    public int getProductQuantity() {
        return Integer.parseInt(getElementText(productQuantity));
    }

    public String getItemsInCartInfo() {
        return getElementText(itemInCartInfo);
    }

    public double getTotalProductsPrice() {
        return getPriceAndLog(subtotalPrice);
    }

    public void clickContinueShoppingBtn() {
        click(continueBTn);
    }

    public void clickProceedToCheckoutBtn() {
        click(proceedToCheckoutBTn);
    }
}
