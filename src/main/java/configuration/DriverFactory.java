package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import models.BrowserName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private WebDriver driver;

    public WebDriver getDriver(BrowserName browserName) {
        logger.info("Chosen browser name: {}", browserName);
        switch (browserName) {
            case CHROME -> getChromeDriver();
            case FIREFOX -> getFirefoxDriver();
            case IE -> getInternetExplorerDriver();
            case EDGE -> getEdgeDriver();
        }
        return this.driver;
    }

    private void getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
    }

    private void getFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        firefoxOptions.addArguments("start-maximized");
        driver = new FirefoxDriver(firefoxOptions);
    }

    private void getInternetExplorerDriver() {
        InternetExplorerOptions explorerOptions = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver(explorerOptions);
    }

    private void getEdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        edgeOptions.addArguments("start-maximized");
        driver = new EdgeDriver(edgeOptions);
    }
}
