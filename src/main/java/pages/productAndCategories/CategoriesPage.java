package pages.productAndCategories;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriesPage extends BasePage {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-depth='0']")
    private List<WebElement> allCategories;

    @FindBy(css = ".category-sub-menu a")
    private List<WebElement> allSubcategories;

    @FindBy(className = "h1")
    private WebElement openedCategory;

    @FindBy(id = "search_filters")
    private WebElement filtersSideMenu;

    @FindBy(className = "total-products")
    private WebElement totalProducts;

    @FindBy(xpath = "(//a[@href='#'])[1]")
    private WebElement leftSlider;

    @FindBy(xpath = "(//a[@href='#'])[2]")
    private WebElement rightSlider;

    @FindBy(css = ".overlay__content")
    private WebElement overlay;

    @FindBy(className = "product")
    private List<WebElement> products;

    @FindBy(className = "price")
    private List<WebElement> productsPrices;

    @FindBy(className = "js-search-filters-clear-all")
    private WebElement clearFilerBtn;

    private static final Logger logger = LoggerFactory.getLogger(CategoriesPage.class);

    public List<WebElement> getAllCategories() {
        return allCategories;
    }

    public List<WebElement> getAllSubategories() {
        return allSubcategories;
    }

    public void clickInCategory(WebElement category) {
        click(category);
    }

    public String getCategoryName(WebElement category) {
        return getElementText(category).toUpperCase();
    }

    public String getOpenedCategoryTitle() {
        return getElementText(openedCategory);
    }

    public boolean isFiltersMenuDisplayed() {
        logger.info("Filters side menu is displayed");
        return filtersSideMenu.isDisplayed();
    }

    public int getTotalProductsNumber() {
        return Integer.parseInt(totalProducts.getText().replaceAll("\\D+", ""));
    }

    public boolean isSubcategory() {
        logger.info("Subcategory exists");
        return allSubcategories.size() > 0;
    }

    public void clickInChosenCategory(String categoryName) {
        for (WebElement allCategory : getAllCategories()) {
            if (Objects.equals(allCategory.getText(), categoryName)) {
                click(allCategory);
            }
        }
    }

    public void moveLeftSlider(int minValue) {
        int valueToShift = minValue - 9;
        if (valueToShift > 0) {
            for (int i = 0; i < valueToShift; i++) {
                leftSlider.sendKeys(Keys.ARROW_RIGHT);
                waitToBeInvisible(overlay);
            }
            logger.info("Moving left slider to: {}", minValue);
        }
    }

    public void moveRightSlider(int maxValue) {
        int valueToShift = 29 - maxValue;
        if (valueToShift > 0) {
            for (int i = 0; i < valueToShift; i++) {
                rightSlider.sendKeys(Keys.ARROW_LEFT);
                waitToBeInvisible(overlay);
            }
            logger.info("Moving right slider to: {}", maxValue);
        }
    }

    public void setPriceFilter(int minValue, int maxValue) {
        moveLeftSlider(minValue);
        moveRightSlider(maxValue);
    }

    public List<Integer> getFilteredProductsPrice() {
        List<Integer> filteredPrices = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            int productPrice = Integer.parseInt(productsPrices.get(i).getText().replaceAll("\\$|\\.00", ""));
            filteredPrices.add(productPrice);
        }
        return filteredPrices;
    }

    public void clearFilter() {
        click(clearFilerBtn);
        waitToBeInvisible(overlay);
    }

    public List<WebElement> getAllProducts() {
        return products;
    }

    public int getProductsQuantity() {
        return getAllProducts().size();
    }

}
