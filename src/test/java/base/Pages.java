package base;

import org.junit.jupiter.api.BeforeEach;
import pages.account.AccountPage;
import pages.basket.BasketPage;
import pages.basket.CheckoutPage;
import pages.login.LoginPage;
import pages.menu.CategoryMenuPage;
import pages.menu.LoginAndCartMenuPage;
import pages.order.OrderConfirmationPage;
import pages.order.OrderDetails;
import pages.order.OrderHistoryPage;
import pages.popup.AddedToBasketPopupPage;
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
    public AddedToBasketPopupPage addedToBasketPopupPage;
    public LoginAndCartMenuPage loginAndCartMenuPage;
    public LoginPage loginPage;
    public BasketPage basketPage;
    public CheckoutPage checkoutPage;
    public OrderConfirmationPage orderConfirmationPage;
    public AccountPage accountPage;
    public OrderHistoryPage orderHistoryPage;
    public OrderDetails orderDetails;

    @BeforeEach
    public void pagesSetup() {
        categoriesPage = new CategoriesPage(driver);
        filtersPage = new FiltersPage(driver);
        categoryMenuPage = new CategoryMenuPage(driver);
        allProductsPage = new AllProductsPage(driver);
        productsTilePage = new ProductsTilePage(driver);
        searchPage = new SearchPage(driver);
        addedToBasketPopupPage = new AddedToBasketPopupPage(driver);
        productPage = new ProductPage(driver);
        loginAndCartMenuPage = new LoginAndCartMenuPage(driver);
        loginPage = new LoginPage(driver);
        basketPage = new BasketPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        accountPage = new AccountPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
        orderDetails = new OrderDetails(driver);
    }
}
