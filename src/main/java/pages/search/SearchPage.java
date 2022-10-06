package pages.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='s']")
    private WebElement searchInput;

    @FindBy(css = "button .search")
    private WebElement searchButton;

    @FindBy(css = ".ui-menu-item .product")
    private List<WebElement> dropdownItems;

    @FindBy(className = "ui-autocomplete")
    private WebElement dropdownList;

    public SearchPage searchProduct(String productName) {
        sendKeys(searchInput, productName);
        return this;
    }

    public void clickSearchInputBtn() {
        click(searchButton);
    }

    public List<WebElement> getDropdownItems() {
        return dropdownItems;
    }

    public List<String> getDropdownItemsNames() {
        waitToBeVisible(dropdownList);
        List<String> dropdownItemsNames = new ArrayList<>();
        for (WebElement dropdownItem : getDropdownItems()) {
            dropdownItemsNames.add(getElementText(dropdownItem));
        }
        return dropdownItemsNames;
    }
}
