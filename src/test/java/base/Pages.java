package base;

import org.junit.jupiter.api.BeforeEach;
import pages.menu.MenuPage;
import pages.productAndCategories.CategoriesPage;
import pages.productAndCategories.FiltersPage;
import pages.productAndCategories.ProductsPage;
import pages.productAndCategories.ProductsTilePage;
import pages.search.SearchPage;

public class Pages extends TestBase {
    public CategoriesPage categoriesPage;
    public FiltersPage filtersPage;
    public MenuPage menuPage;
    public ProductsPage productsPage;
    public ProductsTilePage productsTilePage;
    public SearchPage searchPage;

    @BeforeEach
    public void pagesSetup() {
        categoriesPage = new CategoriesPage(driver);
        filtersPage = new FiltersPage(driver);
        menuPage = new MenuPage(driver);
        productsPage = new ProductsPage(driver);
        productsTilePage = new ProductsTilePage(driver);
        searchPage = new SearchPage(driver);
    }
}
