package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class OrderDetails extends BasePage {
    public OrderDetails(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-history td")
    private List<WebElement> dateAndStatus;

    @FindBy(css = ".line-total td")
    private List<WebElement> totalPriceDetails;

    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address address")
    private WebElement invoiceAddress;

    public String getOrderDate() {
        return getElementText(dateAndStatus.get(0));
    }

    public String getOrderStatus() {
        return getElementText(dateAndStatus.get(1));
    }

    public double getOrderTotalPrice() {
        return getPrice(totalPriceDetails.get(1));
    }

    public String getOrderDeliveryAddress() {
        return getElementText(deliveryAddress);
    }

    public String getOrderInvoiceAddress() {
        return getElementText(invoiceAddress);
    }
}
