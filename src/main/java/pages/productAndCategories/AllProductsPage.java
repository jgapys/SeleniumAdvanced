package pages.productAndCategories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class AllProductsPage extends BasePage {

    public AllProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "product")
    private List<WebElement> products;

    @FindBy(css = ".product-title a")
    private List<WebElement> productsTitles;

    public WebElement getRandomProduct() {
        return getRandomElement(products);
    }

    public List<WebElement> getAllProducts() {
        return products;
    }

    public List<WebElement> getAllProductsTitles() {
        return productsTitles;
    }

    public int getProductsQuantity() {
        return getAllProducts().size();
    }

    public List<Double> getAllProductsPrices(double productPrice) {
        List<Double> productsPrices = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productsPrices.add(productPrice);
        }
        return productsPrices;
    }

    public void clickInChosenProduct(String productName) {
        for (WebElement productTitle : getAllProductsTitles()) {
            if (getElementText(productTitle).equals(productName)) {
                click(productTitle);
                break;
            }
        }
    }

    public void clickInRandomProduct() {
        click(getRandomProduct());
    }
}