package pages.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(SearchPage.class);

    @FindBy(css = "input[name='s']")
    private WebElement searchInput;

    @FindBy(css = "button .search")
    private WebElement searchButton;

    @FindBy(css = "li .product")
    private List<WebElement> dropdownItems;

    public SearchPage searchProduct(String productName) {
        sendKeys(searchInput, productName);
        return this;
    }

    public void clickSearchInputBtn() {
        click(searchButton);
    }

    public List<WebElement> getDropdownItems(){
        return dropdownItems;
    }

    public List<String> getDropdownItemsNames() {
        List<String> dropdownItemsNames = new ArrayList<>();
        for (WebElement dropdownItem : getDropdownItems()) {
            logger.info("--------" + dropdownItem.getText());
            //dropdownItemsNames.add(getElementText(dropdownItem));
        }
        return dropdownItemsNames;
    }
}
