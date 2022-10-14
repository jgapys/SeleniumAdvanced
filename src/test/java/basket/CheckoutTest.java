package basket;

import base.Pages;
import models.User;
import models.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutTest extends Pages {

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

        addedToBasketPopupPage.waitingForModalDialog();
        double productPrice = addedToBasketPopupPage.getProductPrice();
        int quantity = addedToBasketPopupPage.getProductQuantity();
        addedToBasketPopupPage.clickProceedToCheckoutBtn();

        basketPage.clickProceedToCheckoutBtn();

        String shippingOption = "My carrier";
        String paymentOption = "by Check";
        String userFirstName = user.getFirstName();
        String userLastName = user.getLastName();
        String userAddress = "Kwiatowa 5";
        String userZipOrPostalCode = "33-333";
        String userCity = "Sopot";
        String userCountry = "Poland";

        checkoutPage.clickAddNewAddressBtn()
                .fillFormWithNewAddress(userAddress, userZipOrPostalCode, userCity)
                .chooseShippingMethod(shippingOption)
                .choosePaymentMethod(paymentOption)
                .acceptTermsOfService();
        String checkPayee = checkoutPage.getCheckPaymentPayee();
        String checkAddress = checkoutPage.getCheckPaymentAddress();
        checkoutPage.clickPlaceOrderBtn();

        assertThat(orderConfirmationPage.getConfirmProductName()).contains(productName);
        assertThat(orderConfirmationPage.getConfirmProductPrice()).isEqualTo(productPrice);
        assertThat(orderConfirmationPage.getConfirmTotalPrice()).isEqualTo(productPrice * quantity);
        assertThat(orderConfirmationPage.getConfirmShippingPrice()).isEqualTo(shippingOption.equals("My carrier") ? 7.00 : 0);
        assertThat(orderConfirmationPage.getConfirmPaymentOption().toUpperCase()).contains(paymentOption.toUpperCase());
        assertThat(orderConfirmationPage.getConfirmShippingOption()).contains(shippingOption);
        assertThat(orderConfirmationPage.getConfirmCheckPayee()).contains(checkPayee);
        assertThat(orderConfirmationPage.getConfirmCheckAddress()).contains(checkAddress);

        String orderReferenceNumber = orderConfirmationPage.getOrderReferenceNumber();
        double orderShippingPrice = orderConfirmationPage.getConfirmShippingPrice();

        loginAndCartMenuPage.clickAccountBtn();

        accountPage.clickOrderHistoryBtn();

        orderHistoryPage.getOrderByReferenceNumber(orderReferenceNumber);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String todayDate = formatter.format(date);
        double totalPrice = productPrice * quantity + orderShippingPrice;
        String userOrderAddress = userFirstName + " " + userLastName + "\n" + userAddress + "\n" + userZipOrPostalCode + " " + userCity + "\n" + userCountry;

        assertThat(orderDetails.getOrderDate()).isEqualTo(todayDate);
        assertThat(orderDetails.getOrderTotalPrice()).isEqualTo(totalPrice);
        assertThat(orderDetails.getOrderStatus()).isEqualTo("Awaiting check payment");
        assertThat(orderDetails.getOrderDeliveryAddress()).isEqualTo(userOrderAddress);
        assertThat(orderDetails.getOrderInvoiceAddress()).isEqualTo(userOrderAddress);
    }
}