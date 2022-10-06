package basket;

import base.Pages;
import models.User;
import models.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        checkOrderConfirmationInfo(productName, productPrice, quantity, shippingOption, paymentOption, checkPayee, checkAddress);

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

        checkOrderDetailsInfo(todayDate, totalPrice, "Awaiting check payment", userOrderAddress);

    }

    private void checkOrderConfirmationInfo(String productName, double productPrice, int quantity, String shippingOption, String paymentOption, String checkPayee, String checkAddress) {
        checkProductNameInOrderConfirm(productName);
        checkQuantityPriceInOrderConfirm(productPrice);
        checkTotalPriceInOrderConfirm(productPrice, quantity);
        checkShippingAndHandlingInOrderConfirm(shippingOption);
        checkPaymentMethodInOrderConfirm(paymentOption);
        checkShippingMethodInOrderConfirm(shippingOption);
        checkCheckPaymentsDetails(checkPayee, checkAddress);
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

    private void checkPaymentMethodInOrderConfirm(String paymentOption) {
        String confirmPaymentOption = orderConfirmationPage.getConfirmPaymentOption();
        logger.info("Payment option on order confirmation page: " + confirmPaymentOption + " | chosen payment option: " + paymentOption);
        assertThat(confirmPaymentOption.toUpperCase()).contains(paymentOption.toUpperCase());
    }

    private void checkShippingMethodInOrderConfirm(String shippingOption) {
        String confirmShippingOption = orderConfirmationPage.getConfirmShippingOption();
        logger.info("Shipping option on order confirmation page: " + confirmShippingOption + " | chosen shipping option: " + shippingOption);
        assertThat(confirmShippingOption).contains(shippingOption);
    }

    private void checkCheckPaymentsDetails(String checkPayee, String checkAddress) {
        String confirmCheckPayee = orderConfirmationPage.getConfirmCheckPayee();
        String confirmCheckAddress = orderConfirmationPage.getConfirmCheckAddress();
        logger.info("Check payee on order confirmation page: " + confirmCheckPayee + " | expected check payee: " + checkPayee);
        assertThat(confirmCheckPayee).contains(checkPayee);
        logger.info("Check address on order confirmation page: " + confirmCheckAddress + " | expected check address: " + checkAddress);
        assertThat(confirmCheckAddress).contains(checkAddress);
    }

    private void checkOrderDetailsInfo(String date, double totalPrice, String orderStatus, String orderAddress) {
        checkOrderDetailsDate(date);
        checkOrderDetailsTotalPrice(totalPrice);
        checkOrderDetailsPayment(orderStatus);
        checkOrderDetailsDeliveryAddress(orderAddress);
        checkOrderDetailsInvoiceAddress(orderAddress);
    }

    private void checkOrderDetailsDate(String date) {
        String orderDetailsDate = orderDetails.getOrderDate();
        logger.info("Date on order details page: " + orderDetailsDate + " | expected order date: " + date);
        assertThat(orderDetailsDate).isEqualTo(date);
    }

    private void checkOrderDetailsTotalPrice(double totalPrice) {
        double orderDetailsTotalPrice = orderDetails.getOrderTotalPrice();
        logger.info("Total price on order details page: " + orderDetailsTotalPrice + " | expected total price: " + totalPrice);
        assertThat(orderDetailsTotalPrice).isEqualTo(totalPrice);
    }

    private void checkOrderDetailsPayment(String orderStatus) {
        String orderDetailsPayment = orderDetails.getOrderStatus();
        logger.info("Order status on order details page: " + orderDetailsPayment + " | expected order status: " + orderStatus);
        assertThat(orderDetailsPayment).isEqualTo(orderStatus);
    }

    private void checkOrderDetailsDeliveryAddress(String deliveryAddress) {
        String orderDeliveryAddress = orderDetails.getOrderDeliveryAddress();
        logger.info("Delivery address on order details page: \n" + orderDeliveryAddress + "\nExpected delivery address: \n" + deliveryAddress);
        assertThat(orderDeliveryAddress).isEqualTo(deliveryAddress);
    }

    private void checkOrderDetailsInvoiceAddress(String invoiceAddress) {
        String orderInvoiceAddress = orderDetails.getOrderInvoiceAddress();
        logger.info("Invoice address on order details page: \n" + orderInvoiceAddress + "\nExpected invoice address: \n" + invoiceAddress);
        assertThat(orderInvoiceAddress).isEqualTo(invoiceAddress);
    }
}