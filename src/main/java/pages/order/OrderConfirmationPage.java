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

    private static final Logger logger = LoggerFactory.getLogger(OrderConfirmationPage.class);

    @FindBy(css = ".details span")
    private WebElement confirmProductName;

    @FindBy(css = ".qty .col-xs-4")
    private List<WebElement> priceAndQuantityDetails;

    @FindBy(css = "tr td")
    private List<WebElement> totalPriceAndShippingCells;

    @FindBy(css = "#order-details li")
    private List<WebElement> orderDetails;

    @FindBy(css = ".card-block li strong")
    private List<WebElement> checkDetails;

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

    public String getOrderReferenceNumber() {
        String refNumber = getElementText(orderDetails.get(0)).replace("Order reference: ", "");
        logger.info("Order reference number: {}", refNumber);
        return refNumber;
    }

    public String getConfirmPaymentOption() {
        return getElementText(orderDetails.get(1));
    }

    public String getConfirmShippingOption() {
        return getElementText(orderDetails.get(2));
    }

    public String getConfirmCheckPayee() {
        return getElementText(checkDetails.get(1));
    }

    public String getConfirmCheckAddress() {
        return getElementText(checkDetails.get(2));
    }
}
