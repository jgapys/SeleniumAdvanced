package basket;

import base.Pages;
import models.Product;
import org.decimal4j.util.DoubleRounder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketTest extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(BasketTest.class);

    @Test
    @DisplayName("Generic test - checking the correctness of added products to basket and removing them")
    @Tag("basket")
    public void checkingAddedProductsCorrectnessAndRemovingThem() {
        int productsNumber = 5;
        List<Product> randomProducts = generateRandomProductsInCart(productsNumber);
        listsAllProducts(randomProducts);

        loginAndCartMenuPage.clickCartBtn();

        List<Product> cartProducts = basketPage.getListOfProductsInCart();
        listsAllProducts(cartProducts);

        assertThat(cartProducts).usingRecursiveComparison().isEqualTo(randomProducts);
        checkTotalOrderValue(randomProducts);

        removeAllElementsFromBasket(cartProducts, randomProducts);

        assertTrue(basketPage.emptyCartLabelIsDisplayed());
        logger.info("Empty cart label is displayed");
    }


    private List<Product> generateRandomProductsInCart(int productsNumber) {
        List<Product> randomProducts = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < productsNumber; i++) {
            allProductsPage.clickInRandomProduct();

            String productName = productPage.getProductName();
            logger.info("Product: {}", productName);

            int quantity = random.nextInt(1, 6);
            productPage.setProductQuantity(quantity);
            logger.info("Quantity: {}", quantity);

            productPage.addProductToCart();
            addedToBasketPopupPage.waitingForModalDialog();

            double quantityPrice = addedToBasketPopupPage.getProductPrice();
            logger.info("Quantity price: {}", quantityPrice);

            double totalPrice = quantity * quantityPrice;
            logger.info("Total price: {}", totalPrice);

            for (Product randomProduct : randomProducts) {
                if (randomProduct.getName().equals(productName)) {
                    quantity += randomProduct.getQuantity();
                    totalPrice += randomProduct.getTotalPrice();
                }
            }

            Product product = Product.productBuilder()
                    .name(productName)
                    .quantity(quantity)
                    .quantityPrice(quantityPrice)
                    .totalPrice(roundToTwoDecimalPlaces(totalPrice))
                    .build();

            boolean isInCart = false;
            for (Product randomProduct : randomProducts) {
                if (randomProduct.getName().equals(product.getName())) {
                    isInCart = true;
                    randomProducts.set(randomProducts.indexOf(randomProduct), product);
                    break;
                }
            }
            if (!isInCart) {
                randomProducts.add(product);
            }

            addedToBasketPopupPage.clickContinueShoppingBtn();
            categoryMenuPage.returnToHomePage();
        }

        return randomProducts;
    }

    private void listsAllProducts(List<Product> products) {
        for (Product product : products) {
            logger.info(product.toString());
        }
    }

    private void checkTotalOrderValue(List<Product> products) {
        double totalOrderValue = getTotalOrderValue(products);
        double cartTotalValue = basketPage.getTotalOrderPrice();

        logger.info("Value of all added random products: " + roundToTwoDecimalPlaces(totalOrderValue) + " | total value on cart page: " + cartTotalValue);
        assertThat(roundToTwoDecimalPlaces(totalOrderValue)).isEqualTo(cartTotalValue);
    }

    private double getTotalOrderValue(List<Product> products) {
        double totalOrderValue = 0;
        for (Product product : products) {
            totalOrderValue += product.getTotalPrice();
        }
        return totalOrderValue;
    }

    private double roundToTwoDecimalPlaces(double value) {
        return DoubleRounder.round(value, 2);
    }

    private void removeAllElementsFromBasket(List<Product> cartProducts, List<Product> randomProducts) {
        int cartSize = cartProducts.size();
        for (int i = 0; i < cartSize; i++) {
            logger.info("Removing " + basketPage.getCartItemName(0) + " with total product value " + basketPage.getCartItemTotalPrice(0));
            basketPage.removeFirstProductFromCart()
                    .waitingForRemove();
            randomProducts.remove(0);
            cartProducts.remove(0);
            assertThat(cartProducts).usingRecursiveComparison().isEqualTo(randomProducts);
            checkTotalOrderValue(randomProducts);
        }
    }
}