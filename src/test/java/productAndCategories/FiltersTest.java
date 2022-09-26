package productAndCategories;

import base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.menu.MenuPage;
import pages.productAndCategories.FiltersPage;
import pages.productAndCategories.ProductsPage;
import pages.productAndCategories.ProductsTilePage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FiltersTest extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(FiltersTest.class);

    @Test
    @DisplayName("Checking the correct operation of filters for category")
    @Tag("filters")
    @Tag("prodAndCat")
    public void checkingFilterForCategory() {
        MenuPage menuPage = new MenuPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductsTilePage productsTilePage = new ProductsTilePage(driver);
        FiltersPage filtersPage = new FiltersPage(driver);

        menuPage.clickInChosenCategory("ART");
        int productsAmount = productsPage.getProductsQuantity();
        int minPrice = 8;
        int maxPrice = 10;
        filtersPage.setPriceFilter(minPrice, maxPrice);
        List<Integer> filteredProdPrice = productsPage.getAllProductsPrices(productsTilePage.getProductPrice());
        for (int price : filteredProdPrice) {
            logger.info("Price " + price + " matches filter between " + minPrice + " and " + maxPrice);
            assertThat(price).isBetween(minPrice, maxPrice);
        }
        filtersPage.clearFilter();
        int productsAmountAfterClear = productsPage.getProductsQuantity();

        logger.info("Products after clear: " + productsAmountAfterClear + ", initial number of products: " + productsAmount);
        assertThat(productsAmountAfterClear).isEqualTo(productsAmount);
    }
}
