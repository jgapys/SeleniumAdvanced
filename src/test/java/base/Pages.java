package base;

import org.junit.jupiter.api.BeforeEach;
import pages.basket.BasketPage;
import pages.basket.CheckoutPage;
import pages.login.LoginPage;
import pages.menu.CategoryMenuPage;
import pages.menu.LoginAndCartMenuPage;
import pages.order.OrderConfirmationPage;
import pages.popup.AddedToCartPage;
import pages.productAndCategories.*;
import pages.search.SearchPage;

public class Pages extends TestBase {
    public CategoriesPage categoriesPage;
    public FiltersPage filtersPage;
    public CategoryMenuPage categoryMenuPage;
    public AllProductsPage allProductsPage;
    public ProductsTilePage productsTilePage;
    public SearchPage searchPage;
    public ProductPage productPage;
    public AddedToCartPage addedToCartPage;
    public LoginAndCartMenuPage loginAndCartMenuPage;
    public LoginPage loginPage;
    public BasketPage basketPage;
    public CheckoutPage checkoutPage;
    public OrderConfirmationPage orderConfirmationPage;

    @BeforeEach
    public void pagesSetup() {
        categoriesPage = new CategoriesPage(driver);
        filtersPage = new FiltersPage(driver);
        categoryMenuPage = new CategoryMenuPage(driver);
        allProductsPage = new AllProductsPage(driver);
        productsTilePage = new ProductsTilePage(driver);
        searchPage = new SearchPage(driver);
        addedToCartPage = new AddedToCartPage(driver);
        productPage = new ProductPage(driver);
        loginAndCartMenuPage = new LoginAndCartMenuPage(driver);
        loginPage = new LoginPage(driver);
        basketPage = new BasketPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
    }
}
