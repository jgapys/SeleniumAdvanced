package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    protected WebElement getRandomElement(List<WebElement> webElements) {
        Random random = new Random();
        int randomElementIndex = random.nextInt(webElements.size());
        return webElements.get(randomElementIndex);
    }

    protected void sendKeys(WebElement element, String text) {
        logger.info("Typing: {}", text);
        element.sendKeys(text);
    }

    protected void click(WebElement element) {
        logger.info("Clicking on: " + element.getText());
        element.click();
    }

    protected String getElementText(WebElement element) {
        String text = element.getText();
        logger.info(text);
        return text;
    }

    protected void waitToBeInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void returnToPreviousPage() {
        driver.navigate().back();
    }

    protected String removeDollarSignFromPrice(String price) {
        return price.replaceAll("\\$", "");
    }

    protected double getPrice(WebElement price) {
        return Double.parseDouble(removeDollarSignFromPrice(getElementText(price)));
    }

    protected double getPriceAndLog(WebElement price) {
        double productPrice = getPrice(price);
        logger.info(String.valueOf(productPrice));
        return productPrice;
    }

    protected void clearInput(WebElement element) {
        element.clear();
    }
}