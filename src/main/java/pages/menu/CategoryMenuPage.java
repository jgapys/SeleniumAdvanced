package pages.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CategoryMenuPage extends BasePage {
    public CategoryMenuPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(CategoryMenuPage.class);

    @FindBy(css = "a[data-depth='0']")
    private List<WebElement> allMenuItems;

    @FindBy(className = "logo")
    private WebElement logo;

    public List<WebElement> getAllMenuItems() {
        return allMenuItems;
    }

    public List<String> getAllMenuItemsNames() {
        List<String> menuItemsNames = new ArrayList<>();
        for (WebElement menuItem : getAllMenuItems()) {
            menuItemsNames.add(getMenuItemName(menuItem));
        }
        return menuItemsNames;
    }

    public void clickInChosenCategory(String categoryName) {
        for (WebElement menuItem : getAllMenuItems()) {
            if (getElementText(menuItem).equals(categoryName)) {
                click(menuItem);
                break;
            }
        }
    }

    public String getMenuItemName(WebElement menuItem) {
        return getElementText(menuItem);
    }

    public void returnToHomePage() {
        logger.info("Clicking on: my store logo");
        logo.click();
    }
}