package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    }

    public WebDriver driver;
    public WebDriverWait wait;

    protected WebElement getRandomElement(List<WebElement> webElements) {
        Random random = new Random();
        int randomElementIndex = random.nextInt(webElements.size());
        return webElements.get(randomElementIndex);
    }

    protected void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
        logger.info("Typing: {}", text);
    }

    protected void click(WebElement element) {
        element.click();
        logger.info("Clicking on: " + element.getText());
    }

    protected String getElementText(WebElement element) {
        logger.info("Element text: {}", element.getText());
        return element.getText();
    }

    protected void waitToBeVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
