package pages.productAndCategories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "product")
    private List<WebElement> products;

    public WebElement getRandomProduct() {
        return getRandomElement(products);
    }

    public List<WebElement> getAllProducts() {
        return products;
    }

    public int getProductsQuantity() {
        return getAllProducts().size();
    }

    public List<Integer> getAllProductsPrices(int productPrice) {
        List<Integer> productsPrices = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productsPrices.add(productPrice);
        }
        return productsPrices;
    }
}