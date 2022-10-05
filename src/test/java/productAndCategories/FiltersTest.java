package productAndCategories;

import base.Pages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FiltersTest extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(FiltersTest.class);

    @Test
    @DisplayName("Checking the correct operation of filters for category")
    @Tag("filters")
    @Tag("prodAndCat")
    public void checkingFilterForCategory() {
        categoryMenuPage.clickInChosenCategory("ART");
        int productsAmount = allProductsPage.getProductsQuantity();
        double minPrice = 8.00;
        double maxPrice = 10.00;
        filtersPage.setPriceFilter(minPrice, maxPrice);
        List<Double> filteredProdPrice = allProductsPage.getAllProductsPrices(productsTilePage.getProductPrice());
        for (double price : filteredProdPrice) {
            logger.info("Price " + price + " matches filter between " + minPrice + " and " + maxPrice);
            assertThat(price).isBetween(minPrice, maxPrice);
        }
        filtersPage.clearFilter();
        int productsAmountAfterClear = allProductsPage.getProductsQuantity();

        logger.info("Products after clear: " + productsAmountAfterClear + ", initial number of products: " + productsAmount);
        assertThat(productsAmountAfterClear).isEqualTo(productsAmount);
    }
}
