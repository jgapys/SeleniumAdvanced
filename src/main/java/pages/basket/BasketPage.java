package pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class BasketPage extends BasePage {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".checkout .btn-primary")
    private WebElement proceedToCheckoutBTn;

    public void clickProceedToCheckoutBtn() {
        click(proceedToCheckoutBTn);
    }
}
