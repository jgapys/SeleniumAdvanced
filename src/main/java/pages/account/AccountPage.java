package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "history-link")
    private WebElement orderHistoryBtn;

    public void clickOrderHistoryBtn() {
        click(orderHistoryBtn);
    }
}
