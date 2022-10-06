package pages.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

public class LoginAndCartMenuPage extends BasePage {
    public LoginAndCartMenuPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(LoginAndCartMenuPage.class);

    @FindBy(className = "cart-products-count")
    private WebElement cartProductsCount;

    @FindBy(className = "user-info")
    private WebElement signInBtn;

    @FindBy(className = "account")
    private WebElement accountBtn;

    public int getCartBtnQuantity() {
        String cartProductsCount = getElementText(this.cartProductsCount);
        String cartBtnQuantity = cartProductsCount.substring(1, cartProductsCount.length() - 1);
        logger.info(cartBtnQuantity);
        return Integer.parseInt(cartBtnQuantity);
    }

    public void clickSignInBtn() {
        click(signInBtn);
    }

    public void clickAccountBtn() {
        click(accountBtn);
    }
}
