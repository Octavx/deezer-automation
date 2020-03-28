package pages;

import configuration.TestConfigurationSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumUtils;

public abstract class BasePage {


    protected TestConfigurationSingleton testConfigurationSingleton = TestConfigurationSingleton.getInstance();
    protected SeleniumUtils seleniumUtils = new SeleniumUtils();
    public Logger log = LogManager.getLogger(this.getClass().getName());

    protected BasePage() {
        PageFactory.initElements(testConfigurationSingleton.getDriver(), this);
        log.info("Page factory elements for page " + this.toString() + " initialized but not gathered...");
    }

    public abstract boolean isAt();

}
