package pages.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.Objects;

public class MenuPage extends BasePage {
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-depth='0']")
    private List<WebElement> allMenuItems;

    public List<WebElement> getAllMenuItems() {
        return allMenuItems;
    }

    public void clickInChosenCategory(String categoryName) {
        for (WebElement menuItem : getAllMenuItems()) {
            if (Objects.equals(menuItem.getText(), categoryName)) {
                click(menuItem);
            }
        }
    }

    public void clickInMenuItem(WebElement menuItem) {
        click(menuItem);
    }

    public String getMenuItemName(WebElement menuItem) {
        return getElementText(menuItem);
    }
}