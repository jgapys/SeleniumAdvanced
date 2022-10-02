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

    public void setPriceFilter(double minValue, double maxValue) {
        moveLeftSlider(minValue);
        moveRightSlider(maxValue);
    }

    private String getFiltersPriceRange() {
        return removeDollarSignFromPrice(getElementText(filtersPriceLabel));
    }

    private double getMinLabelPrice() {
        return Double.parseDouble(getFiltersPriceRange().substring(0, 4));
    }

    private double getMaxLabelPrice() {
        return Double.parseDouble(getFiltersPriceRange().substring(7));
    }

    public void moveLeftSlider(double minValue) {
        double valueToShift = minValue - getMinLabelPrice();
        moveSliderToDirection(valueToShift, minValue, leftSlider, Keys.ARROW_RIGHT);
    }

    public void moveRightSlider(double maxValue) {
        double valueToShift = getMaxLabelPrice() - maxValue;
        moveSliderToDirection(valueToShift, maxValue, rightSlider, Keys.ARROW_LEFT);
    }

    private void moveSliderToDirection(double valueToShift, double value, WebElement slider, Keys arrow) {
        if (valueToShift > 0) {
            for (int i = 0; i < valueToShift; i++) {
                slider.sendKeys(arrow);
                waitToBeInvisible(overlay);
            }
            logger.info("Moving slider to: " + value);
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