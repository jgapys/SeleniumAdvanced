package productAndCategories;

import base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.productAndCategories.CategoriesPage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CategoriesTest extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(CategoriesTest.class);

    @Test
    @DisplayName("Checking name, filters, count and label for each category")
    @Tag("categories")
    public void checkingParametersForEachCategory() {
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        List<WebElement> allCategories = categoriesPage.getAllCategories();
        for (int i = 0; i < allCategories.size(); i++) {
            WebElement category = allCategories.get(i);
            String categoryName = categoriesPage.getCategoryName(category);
            categoriesPage.clickInCategory(category);
            String openedCategoryTitle = categoriesPage.getOpenedCategoryTitle();
            logger.info("Opened category title: " + openedCategoryTitle + ", clicked category name: " + categoryName);
            assertThat(openedCategoryTitle).isEqualTo(categoryName);
            assertThat(categoriesPage.isFiltersMenuDisplayed()).isEqualTo(true);
            int productsAmount = categoriesPage.getProductsQuantity();
            logger.info("There are " + categoriesPage.getTotalProductsNumber() + " products. | products in category: " + productsAmount);
            assertThat(categoriesPage.getTotalProductsNumber()).isEqualTo(productsAmount);
        }
    }

    @Test
    @DisplayName("Checking name, filters, count and label for each subcategory")
    @Tag("categories")
    public void checkingParametersForEachSubcategory() {
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        List<WebElement> allCategories = categoriesPage.getAllCategories();
        for (int i = 0; i < allCategories.size(); i++) {
            WebElement category = allCategories.get(i);
            categoriesPage.clickInCategory(category);
            if (categoriesPage.isSubcategory()) {
                List<WebElement> allSubcategories = categoriesPage.getAllSubategories();
                for (int j = 0; j < allSubcategories.size(); j++) {
                    WebElement subcategory = allSubcategories.get(j);
                    String subcategoryName = categoriesPage.getCategoryName(subcategory);
                    categoriesPage.clickInCategory(subcategory);
                    String openedSubcategoryTitle = categoriesPage.getOpenedCategoryTitle();
                    logger.info("Opened subcategory title: " + openedSubcategoryTitle + ", clicked subcategory name: " + subcategoryName);
                    assertThat(openedSubcategoryTitle).isEqualTo(subcategoryName);
                    assertThat(categoriesPage.isFiltersMenuDisplayed()).isEqualTo(true);
                    int productsAmount = categoriesPage.getProductsQuantity();
                    logger.info("There are " + categoriesPage.getTotalProductsNumber() + " products. | products in subcategory: " + productsAmount);
                    assertThat(categoriesPage.getTotalProductsNumber()).isEqualTo(productsAmount);
                }
            }
        }
    }

    @Test
    @DisplayName("Checking the correct operation of filters for category")
    @Tag("categories")
    public void checkingFilterForCategory() {
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        categoriesPage.clickInChosenCategory("ART");
        int productsAmount = categoriesPage.getProductsQuantity();
        categoriesPage.setPriceFilter(8, 10);
        List<Integer> filteredProdPrice = categoriesPage.getFilteredProductsPrice();
        for (int price : filteredProdPrice) {
            logger.info("Price " + price + " matches filter between " + 8 + " and " + 10);
            assertThat(price).isBetween(8, 10);
        }
        categoriesPage.clearFilter();
        int productsAmountAfterClear = categoriesPage.getProductsQuantity();
        logger.info("Products after clear: " + productsAmountAfterClear + ", initial number of products: " + productsAmount);
        assertThat(productsAmountAfterClear).isEqualTo(productsAmount);
    }
}