package configuration;

import configuration.Types.DriverTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestConfigurationSingleton {

    private static final TestConfigurationSingleton instance = new TestConfigurationSingleton();
    public Logger log = LogManager.getLogger(this.getClass().getName());

    private WebDriver driver;
    private ProprietiesSingleton prop;


    private TestConfigurationSingleton() {
        prop = ProprietiesSingleton.getInstance();
        driver = loadDriverConfiguration(prop.getBrowserDriver());
        this.goToWebEntry();

        log.info("Proprieties and driver information initialized.");
    }


    private WebDriver loadDriverConfiguration(DriverTypes driverTypes) {

        switch(driverTypes) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                log.info(driverTypes.toString() + " driver selected!");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--window-size=1280,1024");
                return new ChromeDriver(options);

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                log.info(driverTypes.toString() + " driver selected!");
                return new FirefoxDriver();

            default: return new ChromeDriver();
        }
    }

    public static TestConfigurationSingleton getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ProprietiesSingleton getProp() {
        return prop;
    }

    public void goToWebEntry() {
        driver.get(prop.getDeezerWebentry());
    }
}
