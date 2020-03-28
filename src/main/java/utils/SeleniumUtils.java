//
//  Some useful methods to work with Selenium elements
//

package utils;

import configuration.TestConfigurationSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

public class SeleniumUtils {

    private final int DEFAULT_TIMEOUT = 30;  // seconds
    //https://stackoverflow.com/questions/47191185/how-to-explicitly-wait-while-using-page-factory-in-selenium

    private TestConfigurationSingleton testConfigurationSingleton = TestConfigurationSingleton.getInstance();
    public Logger log = LogManager.getLogger(this.getClass().getName());

    public void explicitWaitForElement(WebElement locator) {
        if(locator == null) {
            log.warn("Waiting for missing element" + locator);
            return;
        }

        WebDriverWait wait = new WebDriverWait(testConfigurationSingleton.getDriver(), DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void explicitWaitForElement(WebElement locator, int timeout) {
        if(locator == null) {
            log.warn("Waiting for missing element" + locator);
            return;
        }

        WebDriverWait wait = new WebDriverWait(testConfigurationSingleton.getDriver(), timeout);
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public boolean isElementVisible(WebElement locator) {
        try {
            explicitWaitForElement(locator);
            locator.isDisplayed();
            return true;
        }
        catch(NoSuchElementException e){
            log.error("The element " + locator + " cannot be found.");
            return false;
        }
    }

    public boolean isElementEnabled(WebElement locator) {

        try {
            explicitWaitForElement(locator);
            locator.isEnabled();
            return true;
        }
        catch(NoSuchElementException e){
            log.error("The element " + locator + " cannot be found.");
            return false;
        }
    }

    public boolean isElementSelected(WebElement locator) {
        try{
            explicitWaitForElement(locator);
            locator.isSelected();
            return true;
        }
        catch(NoSuchElementException e){
            log.error("The element " + locator + " cannot be found.");
            return false;
        }
    }

    public boolean isElementPresent(By element) {
        try{
            testConfigurationSingleton.getDriver().findElement(element);
            return true;
        }
        catch(NoSuchElementException e){
            log.error("The element " + element + " cannot be found.");
            return false;
        }

    }

    public void waitForAndClick(WebElement locator) {
        log.info("Waiting for element to be clickable..." + locator);
        explicitWaitForElement(locator);
        locator.click();
    }

    public String getElementText(WebElement locator) {
        explicitWaitForElement(locator);
        return locator.getText();
    }

    public String getElementAttribute(WebElement locator, String attribute) {
        explicitWaitForElement(locator);
        return locator.getAttribute(attribute);
    }

    public void clearElementAndTypeText(WebElement locator, String text) {
        explicitWaitForElement(locator);
        CharSequence charText = new StringBuffer(text);
        locator.sendKeys(charText);
    }

    public WebElement searchElementWithTextFromList(List<WebElement> webElements, String text) {
        if (webElements.size() == 0) {
            log.info("Cannot search in an empty list of elements...");
            return null;
        }

        return webElements.stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + text));
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(testConfigurationSingleton.getDriver());
        actions.moveToElement(element);
    }
}
