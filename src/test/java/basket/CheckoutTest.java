package basket;

import base.Pages;
import models.User;
import models.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutTest extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutTest.class);

    @Test
    @DisplayName("Checking the correctness of the order placed")
    @Tag("basket")
    @Tag("checkout")
    public void checkingOrderCorrectness() {
        User user = new UserFactory().getAlreadyRegisteredUser();
        loginAndCartMenuPage.clickSignInBtn();
        loginPage.logInUserToAccount(user.getEmail(), user.getPassword());

        categoryMenuPage.returnToHomePage();
        String productName = "THE BEST IS YET POSTER";
        allProductsPage.clickInChosenProduct(productName);
        productPage.addProductToCart();

        addedToCartPage.waitingForModalDialog();
        double productPrice = addedToCartPage.getProductPrice();
        int quantity = addedToCartPage.getProductQuantity();
        addedToCartPage.clickProceedToCheckoutBtn();

        basketPage.clickProceedToCheckoutBtn();

        String shippingOption = "My carrier";
        checkoutPage.clickAddNewAddressBtn()
                .fillFormWithNewAddress("Kwiatowa 5", "33-333", "Sopot")
                .chooseShippingMethod(shippingOption)
                .choosePaymentMethod("Pay by Check")
                .acceptTermsOfService()
                .clickPlaceOrderBtn();

        checkOrderConfirmationInfo(productName, productPrice, quantity, shippingOption);


    }

    private void checkOrderConfirmationInfo(String productName, double productPrice, int quantity, String shippingOption) {
        checkProductNameInOrderConfirm(productName);
        checkQuantityPriceInOrderConfirm(productPrice);
        checkTotalPriceInOrderConfirm(productPrice, quantity);
        checkShippingAndHandlingInOrderConfirm(shippingOption);
    }

    private void checkProductNameInOrderConfirm(String productName) {
        String confirmProductName = orderConfirmationPage.getConfirmProductName();
        logger.info("Product name on order confirmation page: " + confirmProductName + " | chosen product name: " + productName);
        assertThat(confirmProductName).contains(productName);
    }

    private void checkQuantityPriceInOrderConfirm(double productPrice) {
        double confirmProductPrice = orderConfirmationPage.getConfirmProductPrice();
        logger.info("Quantity price on order confirmation page: " + confirmProductPrice + " | chosen product price: " + productPrice);
        assertThat(confirmProductPrice).isEqualTo(productPrice);
    }

    private void checkTotalPriceInOrderConfirm(double productPrice, int quantity) {
        double confirmTotalPrice = orderConfirmationPage.getConfirmTotalPrice();
        double totalPrice = productPrice * quantity;
        logger.info("Total price on order confirmation page: " + confirmTotalPrice + " | product price * quantity: " + totalPrice);
        assertThat(confirmTotalPrice).isEqualTo(totalPrice);
    }

    private void checkShippingAndHandlingInOrderConfirm(String shippingOption) {
        double confirmShippingPrice = orderConfirmationPage.getConfirmShippingPrice();
        double shippingPrice = shippingOption.equals("My carrier") ? 7.00 : 0;
        logger.info("Shipping and handling price on order confirmation page: " + confirmShippingPrice + " | chosen shipping method price: " + shippingPrice);
        assertThat(confirmShippingPrice).isEqualTo(shippingPrice);
    }
}
