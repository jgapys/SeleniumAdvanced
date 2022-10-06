package search;

import base.Pages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchTest extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(SearchTest.class);

    @Test
    @DisplayName("Checking in the search result if there is the name of searched random product")
    @Tag("search")
    public void checkingSearchResultForRandomSearchedProduct() {
        String productTitle = productsTilePage.getProductTitle(allProductsPage.getRandomProduct());
        searchPage.searchProduct(productTitle).clickSearchInputBtn();
        String productTitleAfterSearch = productsTilePage.getSearchedProductTitle();

        logger.info("Product on website: " + productTitleAfterSearch + ", product to search: " + productTitle);
        assertThat(productTitleAfterSearch).isEqualTo(productTitle);
    }

    @Test
    @DisplayName("Checking results in dropdown that appears for text entered in input")
    @Tag("search")
    public void checkingDropdownForTextInInput() {
        String search = "HUMMINGBIRD";
        searchPage.searchProduct(search);

        List<String> dropdownItemsNames = searchPage.getDropdownItemsNames();
        for (String dropdownItemsName : dropdownItemsNames) {
            logger.info(dropdownItemsName + " contains " + search);
            assertThat(dropdownItemsName).contains(search);
        }

    }
}