package pages.basket;

import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BasketPage extends BasePage {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".checkout .btn-primary")
    private WebElement proceedToCheckoutBtn;

    @FindBy(className = "cart-item")
    private List<WebElement> cartItems;

    @FindBy(css = ".product-line-info a")
    private List<WebElement> cartItemsNames;

    @FindBy(css = ".input-group input")
    private List<WebElement> cartItemsQuantityInputs;

    @FindBy(css = ".current-price span")
    private List<WebElement> cartItemsCurrentPrices;

    @FindBy(css = ".product-price strong")
    private List<WebElement> cartItemsTotalPrices;

    @FindBy(css = "#cart-subtotal-products .value")
    private WebElement totalOrderValue;

    @FindBy(className = "remove-from-cart")
    private List<WebElement> removeIcons;

    @FindBy(className = "no-items")
    private WebElement emptyCartLabel;

    public void clickProceedToCheckoutBtn() {
        click(proceedToCheckoutBtn);
    }

    public String getCartItemName(int index) {
        return getElementText(cartItemsNames.get(index));
    }

    public int getCartItemQuantity(int index) {
        return Integer.parseInt(cartItemsQuantityInputs.get(index).getAttribute("value"));
    }

    public double getCartItemQuantityPrice(int index) {
        return getPrice(cartItemsCurrentPrices.get(index));
    }

    public double getCartItemTotalPrice(int index) {
        return getPrice(cartItemsTotalPrices.get(index));
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public List<Product> getListOfProductsInCart() {
        return IntStream.range(0, getCartItems().size()).mapToObj(i -> Product.productBuilder()
                .name(getCartItemName(i))
                .quantity(getCartItemQuantity(i))
                .quantityPrice(getCartItemQuantityPrice(i))
                .totalPrice(getCartItemTotalPrice(i))
                .build()).collect(Collectors.toList());
    }

    public WebElement getTotalOrderValue() {
        return totalOrderValue;
    }

    public double getTotalOrderPrice() {
        return getPrice(getTotalOrderValue());
    }

    public BasketPage removeFirstProductFromCart() {
        click(removeIcons.get(0));
        return this;
    }

    public BasketPage waitingForRemove() {
        waitToBeInvisible(removeIcons.get(removeIcons.size() - 1));
        return this;
    }

    public boolean emptyCartLabelIsDisplayed() {
        return emptyCartLabel.isDisplayed();
    }
}