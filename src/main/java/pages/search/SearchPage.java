package pages.search;

import org.openqa.selenium.By;
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

    @FindBy(className = "product")
    private List<WebElement> products;

    @FindBy(css = "input[name='s']")
    private WebElement searchInput;

    @FindBy(css = "button .search")
    private WebElement searchButton;

    @FindBy(css = ".product-title a")
    private WebElement productTitle;

    @FindBy(css = "ui-menu-item .product")
    private List<WebElement> dropdownItems;

    public WebElement getRandomProduct() {
        return getRandomElement(products);
    }

    public String getProductName(WebElement product) {
        return getElementText(product.findElement(By.cssSelector(".product-title a")));
    }

    public SearchPage searchProduct(String productName) {
        sendKeys(searchInput, productName);
        return this;
    }

    public void clickSearchInput() {
        click(searchButton);
    }

    public String getSearchedProductTitle() {
        return getElementText(productTitle);
    }

    public List<String> getDropdownItemsNames() {
        List<String> dropdownItemsNames = new ArrayList<>();
        for (WebElement dropdownItem : dropdownItems) {
            dropdownItemsNames.add(getElementText(dropdownItem));
        }
        return dropdownItemsNames;
    }
}
