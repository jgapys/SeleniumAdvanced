package pages.basket;

import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

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
        List<Product> productsInCar = new ArrayList<>();
        for (int i = 0; i < getCartItems().size(); i++) {
            String productName = getCartItemName(i);
            int quantity = getCartItemQuantity(i);
            double quantityPrice = getCartItemQuantityPrice(i);
            double totalPrice = getCartItemTotalPrice(i);

            Product product = Product.productBuilder()
                    .name(productName)
                    .quantity(quantity)
                    .quantityPrice(quantityPrice)
                    .totalPrice(totalPrice)
                    .build();

            productsInCar.add(product);

        }

        return productsInCar;
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