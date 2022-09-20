package search;

import base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.search.SearchPage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchTest extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(SearchTest.class);

    @Test
    @DisplayName("Checking in the search result if there is the name of searched random product")
    @Tag("search")
    public void checkingSearchResultForRandomSearchedProduct() {
        SearchPage searchPage = new SearchPage(driver);
        WebElement randomProduct = searchPage.getRandomProduct();
        String productName = searchPage.getProductName(randomProduct);
        searchPage.searchProduct(productName).clickSearchInput();
        String productAfterSearch = searchPage.getSearchedProductTitle();
        logger.info("Product on website: " + productAfterSearch + ", product to search: " + productName);
        assertThat(productAfterSearch).isEqualTo(productName);
    }

    @Test
    @DisplayName("Checking results in dropdown that appears for text entered in input")
    @Tag("search")
    public void checkingDropdownForTextInInput() {
        SearchPage searchPage = new SearchPage(driver);
        String search = "HUMMINGBIRD";
        searchPage.searchProduct(search);
        List<String> dropdownItemsNames = searchPage.getDropdownItemsNames();
        boolean isContained = true;
        for (String dropdownItemsName : dropdownItemsNames) {
            if (!dropdownItemsName.contains(search)) {
                isContained = false;
                break;
            }
        }
        assertThat(isContained).isEqualTo(true);
    }
}
