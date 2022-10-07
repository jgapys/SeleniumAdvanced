package basket;

import base.Pages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddToBasketTest extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(AddToBasketTest.class);

    @Test
    @DisplayName("Checking if the cart icon will update after adding the product")
    @Tag("basket")
    public void checkingCartIconAfterSuccessfullyAddedProduct() {
        String productName = "THE BEST IS YET POSTER";
        int quantity = 3;
        int itemsInCart = 3;

        categoryMenuPage.clickInChosenCategory("ART");
        allProductsPage.clickInChosenProduct(productName);

        double productPrice = productPage.getCurrentPrice();

        productPage.setProductQuantity(quantity);
        productPage.addProductToCart();

        checkAddedToCartPopupParameters(productName, productPrice, quantity, itemsInCart);

        addedToBasketPopupPage.clickContinueShoppingBtn();

        int cartQuantity = loginAndCartMenuPage.getCartBtnQuantity();

        logger.info("Quantity of products on cart button: " + cartQuantity + ", items in car: " + itemsInCart);
        assertThat(cartQuantity).isEqualTo(itemsInCart);

    }

    private void checkAddedToCartPopupParameters(String productName, double productPrice, int productQuantity, int itemsInCart) {
        addedToBasketPopupPage.waitingForModalDialog();

        checkPopupProductName(productName);
        checkPopupProductPrice(productPrice);
        checkPopupProductQuantity(productQuantity);
        checkPopupItemsInCartInfo(itemsInCart);
        checkPopupTotalProductsPrice(productPrice, productQuantity);

    }

    private void checkPopupProductName(String productName) {
        String name = addedToBasketPopupPage.getProductName();
        logger.info("Product name in popup: " + name + ", chosen product name: " + productName);
        assertThat(name).isEqualTo(productName);
    }

    private void checkPopupProductPrice(double productPrice) {
        double price = addedToBasketPopupPage.getProductPrice();
        logger.info("Product price in popup: " + price + ", chosen product price: " + productPrice);
        assertThat(price).isEqualTo(productPrice);
    }

    private void checkPopupProductQuantity(int productQuantity) {
        int quantity = addedToBasketPopupPage.getProductQuantity();
        logger.info("Product quantity in popup: " + quantity + ", chosen product quantity: " + productQuantity);
        assertThat(quantity).isEqualTo(productQuantity);
    }

    private void checkPopupItemsInCartInfo(int itemsInCart) {
        String itemsInCartInfo = addedToBasketPopupPage.getItemsInCartInfo();
        String itemsInfo = "There are " + itemsInCart + " items in your cart.";
        logger.info("Item in cart info in popup: " + itemsInCartInfo + ", info that should be: " + itemsInfo);
        assertThat(itemsInCartInfo).isEqualTo(itemsInfo);
    }

    private void checkPopupTotalProductsPrice(double productPrice, int productQuantity) {
        double totalProductsPrice = addedToBasketPopupPage.getTotalProductsPrice();
        double productsPrice = productPrice * productQuantity;
        logger.info("Total products price in popup: " + totalProductsPrice + ", chosen total products price: " + productsPrice);
        assertThat(totalProductsPrice).isEqualTo(productsPrice);
    }
}
