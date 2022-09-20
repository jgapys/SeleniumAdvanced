package base;

import configuration.AppProperties;
import configuration.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

public class TestBase {
    public WebDriver driver;
    protected static DriverFactory driverFactory;
    private static AppProperties appProperties;
    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeAll
    static void setDriver() {
        appProperties=AppProperties.getInstance();
        driverFactory = new DriverFactory();
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
    }

    @BeforeEach
    void setUp() {
        driver = driverFactory.getDriver();
        logger.info("New ChromeDriver initialization");
        driver.get(System.getProperty("appURL"));

    }

    @AfterEach
    void tearDown() {
        try {
            driver.quit();
            logger.info("Driver process completed successfully");
        } catch (Exception ex) {
            logger.error("Error occurred: {}", ex);
        }
    }
}