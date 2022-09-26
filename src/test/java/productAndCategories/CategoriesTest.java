package productAndCategories;

import base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.menu.MenuPage;
import pages.productAndCategories.CategoriesPage;
import pages.productAndCategories.FiltersPage;
import pages.productAndCategories.ProductsPage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CategoriesTest extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(CategoriesTest.class);

    @Test
    @DisplayName("Checking name, filters, count and label for each category")
    @Tag("categories")
    @Tag("prodAndCat")
    public void checkingParametersForEachCategory() {
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        FiltersPage filtersPage = new FiltersPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        List<WebElement> allCategories = menuPage.getAllMenuItems();
        for (int i = 0; i < allCategories.size(); i++) {
            WebElement category = allCategories.get(i);
            String categoryName = menuPage.getMenuItemName(category);
            menuPage.clickInMenuItem(category);
            String openedCategoryTitle = categoriesPage.getOpenedCategoryTitle();

            logger.info("Opened category title: " + openedCategoryTitle + ", clicked category name: " + categoryName);
            assertThat(openedCategoryTitle).isEqualTo(categoryName);

            assertThat(filtersPage.isFiltersMenuDisplayed()).isEqualTo(true);

            int productsAmount = productsPage.getProductsQuantity();
            logger.info("There are " + categoriesPage.getTotalProductsNumber() + " products. | products in category: " + productsAmount);
            assertThat(categoriesPage.getTotalProductsNumber()).isEqualTo(productsAmount);
        }
    }

    @Test
    @DisplayName("Checking name, filters, count and label for each subcategory")
    @Tag("categories")
    @Tag("prodAndCat")
    public void checkingParametersForEachSubcategory() {
        CategoriesPage categoriesPage = new CategoriesPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        FiltersPage filtersPage = new FiltersPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        List<WebElement> allCategories = menuPage.getAllMenuItems();
        for (int i = 0; i < allCategories.size(); i++) {
            WebElement category = allCategories.get(i);
            menuPage.clickInMenuItem(category);

            if (categoriesPage.isSubcategory()) {
                logger.info("Subcategory exists");
                List<WebElement> allSubcategories = categoriesPage.getAllSubategories();
                for (int j = 0; j < allSubcategories.size(); j++) {
                    WebElement subcategory = allSubcategories.get(j);
                    String subcategoryName = categoriesPage.getSubcategoryName(subcategory);
                    categoriesPage.clickInSubcategory(subcategory);
                    String openedSubcategoryTitle = categoriesPage.getOpenedCategoryTitle();

                    logger.info("Opened subcategory title: " + openedSubcategoryTitle + ", clicked subcategory name: " + subcategoryName);
                    assertThat(openedSubcategoryTitle).isEqualTo(subcategoryName);

                    assertThat(filtersPage.isFiltersMenuDisplayed()).isEqualTo(true);

                    int productsAmount = productsPage.getProductsQuantity();
                    logger.info("There are " + categoriesPage.getTotalProductsNumber() + " products. | products in subcategory: " + productsAmount);
                    assertThat(categoriesPage.getTotalProductsNumber()).isEqualTo(productsAmount);

                    categoriesPage.returnToCategory();
                }
            }
        }
    }
}