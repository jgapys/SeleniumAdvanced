package pages.productAndCategories;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class FiltersPage extends BasePage {
    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(FiltersPage.class);

    @FindBy(xpath = "(//a[@href='#'])[1]")
    private WebElement leftSlider;

    @FindBy(xpath = "(//a[@href='#'])[2]")
    private WebElement rightSlider;

    @FindBy(css = ".overlay__content")
    private WebElement overlay;

    @FindBy(className = "js-search-filters-clear-all")
    private WebElement clearFilerBtn;

    @FindBy(css = "li p")
    private WebElement filtersPriceLabel;

    @FindBy(id = "search_filters")
    private WebElement filtersSideMenu;

    public void setPriceFilter(int minValue, int maxValue) {
        moveLeftSlider(minValue);
        moveRightSlider(maxValue);
    }

    private String getFiltersPriceRange() {
        return getElementText(filtersPriceLabel).replaceAll("\\$|\\.00", "");
    }

    private int getMinLabelPrice() {
        return Integer.parseInt(getFiltersPriceRange().substring(0, 1));
    }

    private int getMaxLabelPrice() {
        return Integer.parseInt(getFiltersPriceRange().substring(4));
    }

    public void moveLeftSlider(int minValue) {
        int valueToShift = minValue - getMinLabelPrice();
        if (valueToShift > 0) {
            for (int i = 0; i < valueToShift; i++) {
                leftSlider.sendKeys(Keys.ARROW_RIGHT);
                waitToBeInvisible(overlay);
            }
            logger.info("Moving left slider to: {}", minValue);
        }
    }

    public void moveRightSlider(int maxValue) {
        int valueToShift = getMaxLabelPrice() - maxValue;
        if (valueToShift > 0) {
            for (int i = 0; i < valueToShift; i++) {
                rightSlider.sendKeys(Keys.ARROW_LEFT);
                waitToBeInvisible(overlay);
            }
            logger.info("Moving right slider to: {}", maxValue);
        }
    }

    public void clearFilter() {
        click(clearFilerBtn);
        waitToBeInvisible(overlay);
    }

    public boolean isFiltersMenuDisplayed() {
        logger.info("Filters side menu is displayed");
        return filtersSideMenu.isDisplayed();
    }
}