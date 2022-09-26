package pages.productAndCategories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class CategoriesPage extends BasePage {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "li[data-depth='0']")
    private List<WebElement> allSubcategories;

    @FindBy(className = "h1")
    private WebElement openedCategory;

    @FindBy(className = "total-products")
    private WebElement totalProducts;

    public String getOpenedCategoryTitle() {
        return getElementText(openedCategory);
    }

    public int getTotalProductsNumber() {
        return Integer.parseInt(totalProducts.getText().replaceAll("\\D+", ""));
    }

    public List<WebElement> getAllSubategories() {
        return allSubcategories;
    }

    public boolean isSubcategory() {
        return getAllSubategories().size() > 0;
    }

    public String getSubcategoryName(WebElement subcategory) {
        return getElementText(subcategory).toUpperCase();
    }

    public void clickInSubcategory(WebElement subcategory) {
        click(subcategory);
    }

    public void returnToCategory() {
        returnToPreviousPage();
    }
}