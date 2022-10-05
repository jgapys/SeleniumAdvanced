package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.List;

public class OrderConfirmationPage extends BasePage {
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".details span")
    private WebElement confirmProductName;

    @FindBy(css = ".qty .col-xs-4")
    private List<WebElement> priceAndQuantityDetails;

    @FindBy(css = "tr td")
    private List<WebElement> totalPriceAndShippingCells;

    public String getConfirmProductName() {
        return getElementText(confirmProductName);
    }

    public double getConfirmProductPrice() {
        return getPrice(priceAndQuantityDetails.get(0));
    }

    public double getConfirmTotalPrice() {
        return getPrice(priceAndQuantityDetails.get(2));
    }

    public double getConfirmShippingPrice() {
        return getPrice(totalPriceAndShippingCells.get(3));
    }
}
