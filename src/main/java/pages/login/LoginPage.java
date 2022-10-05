package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input.form-control[type='email']")
    private WebElement emailInput;

    @FindBy(css = "input.form-control[type='password']")
    private WebElement passwordInput;

    @FindBy(id = "submit-login")
    private WebElement signInBtn;


    public void logInUserToAccount(String userEmail, String userPassword) {
        sendKeys(emailInput, userEmail);
        sendKeys(passwordInput, userPassword);
        click(signInBtn);
    }
}
