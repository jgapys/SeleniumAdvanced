package basket;

import base.Pages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddToBasketTest extends Pages {

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

        addedToBasketPopupPage.waitingForModalDialog();
        assertThat(addedToBasketPopupPage.getProductName()).isEqualTo(productName);
        assertThat(addedToBasketPopupPage.getProductPrice()).isEqualTo(productPrice);
        assertThat(addedToBasketPopupPage.getProductQuantity()).isEqualTo(quantity);
        assertThat(addedToBasketPopupPage.getItemsInCartInfo()).isEqualTo("There are " + itemsInCart + " items in your cart.");
        assertThat(addedToBasketPopupPage.getTotalProductsPrice()).isEqualTo(productPrice * quantity);

        addedToBasketPopupPage.clickContinueShoppingBtn();

        int cartQuantity = loginAndCartMenuPage.getCartBtnQuantity();

        assertThat(cartQuantity).isEqualTo(itemsInCart);
    }
}
