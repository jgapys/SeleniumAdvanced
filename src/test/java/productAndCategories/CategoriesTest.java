package productAndCategories;

import base.Pages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CategoriesTest extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(CategoriesTest.class);

    @Test
    @DisplayName("Checking name, filters, count and label for each category")
    @Tag("categories")
    @Tag("prodAndCat")
    public void checkingParametersForEachCategory() {
        List<String> allCategoriesNames = menuPage.getAllMenuItemsNames();
        for (String categoryName : allCategoriesNames) {
            menuPage.clickInChosenCategory(categoryName);
            checkCategoryParameters(categoryName);
        }

    }

    @Test
    @DisplayName("Checking name, filters, count and label for each subcategory")
    @Tag("categories")
    @Tag("prodAndCat")
    public void checkingParametersForEachSubcategory() {
        checkSubcategoryParameters("CLOTHES");
        checkSubcategoryParameters("ACCESSORIES");
    }

    private void checkCategoryParameters(String categoryName) {
        String openedCategoryTitle = categoriesPage.getOpenedCategoryTitle();

        logger.info("Opened category title: " + openedCategoryTitle + ", clicked category name: " + categoryName);
        assertThat(openedCategoryTitle).isEqualTo(categoryName.toUpperCase());

        assertThat(filtersPage.isFiltersMenuDisplayed()).isEqualTo(true);

        int productsAmount = productsPage.getProductsQuantity();
        logger.info("There are " + categoriesPage.getTotalProductsNumber() + " products. | products in category: " + productsAmount);
        assertThat(categoriesPage.getTotalProductsNumber()).isEqualTo(productsAmount);
    }

    private void checkSubcategoryParameters(String categoryName) {
        menuPage.clickInChosenCategory(categoryName);
        List<String> allSubcategoriesNames = categoriesPage.getAllSubcategoriesNames();
        for (String subcategoryName : allSubcategoriesNames) {
            categoriesPage.clickInChosenSubcategory(subcategoryName);
            checkCategoryParameters(subcategoryName);
            categoriesPage.returnToCategory();
        }
    }
}