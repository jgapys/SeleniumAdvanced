package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody th")
    private List<WebElement> orderReferenceNumbersList;

    @FindBy(css = ".order-actions a[data-link-action='view-order-details']")
    private List<WebElement> detailsBtnsList;

    public List<String> getOrderReferenceNumbersList() {
        List<String> refNumbers = new ArrayList<>();
        for (WebElement number : orderReferenceNumbersList) {
            refNumbers.add(getElementText(number));
        }
        return refNumbers;
    }

    public void getOrderByReferenceNumber(String referenceNumber) {
        List<String> refNumbers = getOrderReferenceNumbersList();
        for (int i = 0; i < refNumbers.size(); i++) {
            if (refNumbers.get(i).equals(referenceNumber)) {
                click(detailsBtnsList.get(i));
                break;
            }
        }
    }
}
