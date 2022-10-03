package pages.productAndCategories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPage extends BasePage {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "li[data-depth='0'] a")
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

    public List<WebElement> getAllSubcategories() {
        return allSubcategories;
    }

    public List<String> getAllSubcategoriesNames() {
        List<String> subcategoriesNames = new ArrayList<>();
        for (WebElement subcategory : getAllSubcategories()) {
            subcategoriesNames.add(getSubcategoryName(subcategory));
        }
        return subcategoriesNames;
    }

    public void clickInChosenSubcategory(String subcategoryName) {
        for (WebElement subcategory : getAllSubcategories()) {
            if (getElementText(subcategory).equals(subcategoryName)) {
                click(subcategory);
                break;
            }
        }
    }

    public String getSubcategoryName(WebElement subcategory) {
        return getElementText(subcategory);
    }

    public void returnToCategory() {
        returnToPreviousPage();
    }
}