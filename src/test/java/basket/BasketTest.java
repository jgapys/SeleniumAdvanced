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
        int quantity = 5;
        List<Product> expectedProducts = addRandomProductsToCart(quantity);
        listsAllProducts(expectedProducts);

        loginAndCartMenuPage.clickCartBtn();

        List<Product> actualProducts = basketPage.getListOfProductsInCart();
        listsAllProducts(actualProducts);

        assertThat(actualProducts).usingRecursiveComparison().isEqualTo(expectedProducts);
        assertThat(roundToTwoDecimalPlaces(getTotalOrderValue(expectedProducts))).isEqualTo(basketPage.getTotalOrderPrice());

        removeAllElementsFromBasket(actualProducts, expectedProducts);

        assertTrue(basketPage.emptyCartLabelIsDisplayed());
        logger.info("Empty cart label is displayed");
    }


    private List<Product> addRandomProductsToCart(int itemsQuantity) {
        categoryMenuPage.returnToHomePage();
        List<Product> allProducts = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < itemsQuantity; i++) {
            allProductsPage.clickInRandomProduct();
            int productQuantity = random.nextInt(1, 6);
            productPage.setProductQuantity(productQuantity);
            Product productToAdd = productPage.toProductDetails();
            productPage.addProductToCart();
            addedToBasketPopupPage.waitingForModalDialog();
            addedToBasketPopupPage.clickContinueShoppingBtn();

            boolean isInCart = false;
            for (Product product : allProducts) {
                if (product.getName().equals(productToAdd.getName())) {
                    allProducts.get(allProducts.indexOf(product)).addQuantity(productToAdd.getQuantity());
                    isInCart = true;
                    break;
                }
            }
            if (!isInCart) {
                allProducts.add(productToAdd);
            }
        }
        return allProducts;
    }

    private void listsAllProducts(List<Product> products) {
        for (Product product : products) {
            logger.info(product.toString());
        }
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
            assertThat(roundToTwoDecimalPlaces(getTotalOrderValue(randomProducts))).isEqualTo(basketPage.getTotalOrderPrice());
        }
    }
}