package steps;

import configuration.TestConfigurationSingleton;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;


public class Hooks {

    protected TestConfigurationSingleton testConfigurationSingleton = TestConfigurationSingleton.getInstance();
    public Logger log = LogManager.getLogger(this.getClass().getName());

    @Before
    public void initializeHook(Scenario scenario) {
        System.out.println("Starting scenario " + scenario + " .");
    }

    @After ("@web")
    public void resetSession() {
        testConfigurationSingleton.getDriver().manage().deleteAllCookies();
//        testConfigurationSingleton.getDriver().navigate().refresh();
        testConfigurationSingleton.goToWebEntry();
        testConfigurationSingleton.getDriver().switchTo().alert().accept();
        log.info("Browser session terminated.");
    }

    @After (order = 2)
    public void finalTeardown(Scenario scenario) {
        if(scenario.isFailed() || !scenario.isFailed()) {
            testConfigurationSingleton.getDriver().close();
            log.info("Scenario: " + scenario.getName() + " finished running, closing window.");
        }
    }
}
