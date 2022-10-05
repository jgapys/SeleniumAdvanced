package pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.List;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(CheckoutPage.class);

    @FindBy(css = ".add-address a")
    private WebElement addNewAddressBtn;

    @FindBy(css = "input.form-control[name='address1']")
    private WebElement addressInput;

    @FindBy(css = "input.form-control[name='postcode']")
    private WebElement zipOrPostalCodeInput;

    @FindBy(css = "input.form-control[name='city']")
    private WebElement cityInput;

    @FindBy(css = "select.form-control[name='id_country']")
    private WebElement countryDropdown;

    @FindBy(css = ".form-footer button.continue[type='submit']")
    private WebElement addressContinueBtn;

    @FindBy(css = ".delivery-option input[type='radio']")
    private List<WebElement> deliveryOptionsRadioBtn;

    @FindBy(className = "carrier-name")
    private List<WebElement> deliveryOptionsNames;

    @FindBy(css = "button[name='confirmDeliveryOption']")
    private WebElement shippingContinueBtn;

    @FindBy(css = "input[name='payment-option']")
    private List<WebElement> paymentOptionRadioBtn;

    @FindBy(css = ".payment-option label")
    private List<WebElement> paymentOptionNames;

    @FindBy(css = "input[name='conditions_to_approve[terms-and-conditions]']")
    private WebElement acceptConditionsCheckbox;

    @FindBy(css = "#payment-confirmation button")
    private WebElement placeOrderBtn;

    public CheckoutPage clickAddNewAddressBtn() {
        click(addNewAddressBtn);
        return this;
    }

    public CheckoutPage fillFormWithNewAddress(String userAddress, String userZipOrPostalCode, String userCity) {
        sendKeys(addressInput, userAddress);
        sendKeys(zipOrPostalCodeInput, userZipOrPostalCode);
        sendKeys(cityInput, userCity);
        sendKeys(countryDropdown, "Poland");
        click(addressContinueBtn);
        return this;

    }

    public CheckoutPage chooseShippingMethod(String shippingOption) {
        for (int i = 0; i < deliveryOptionsNames.size(); i++) {
            if (getElementText(deliveryOptionsNames.get(i)).equals(shippingOption)) {
                deliveryOptionsRadioBtn.get(i).click();
                logger.info("Clicking on: radio button for {}", shippingOption);
                break;
            }
        }
        click(shippingContinueBtn);
        return this;
    }

    public CheckoutPage choosePaymentMethod(String paymentOption) {
        for (int i = 0; i < paymentOptionNames.size(); i++) {
            if (getElementText(paymentOptionNames.get(i)).equals(paymentOption)) {
                paymentOptionRadioBtn.get(i).click();
                logger.info("Clicking on: radio button for {}", paymentOption);
                break;
            }
        }
        return this;
    }

    public CheckoutPage acceptTermsOfService() {
        acceptConditionsCheckbox.click();
        logger.info("Clicking on: checkbox for accept terms of service");
        return this;
    }

    public CheckoutPage clickPlaceOrderBtn() {
        click(placeOrderBtn);
        return this;
    }
}
