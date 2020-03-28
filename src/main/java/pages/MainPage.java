package pages;

import configuration.TestConfigurationSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumUtils;

public class MainPage extends BasePage{

    @FindBy(xpath = "//span[@class='unlogged-btn-label']")
    private WebElement trait;

    @FindBy(xpath = "//a[@id='topbar-login-button']//span[@class='unlogged-btn-label']")
    private WebElement loginBtn;

    public MainPage() {
        super();
    }

    public boolean isAt() {
        return seleniumUtils.isElementVisible(trait);
    }

    public void clickOnLogin() {
        seleniumUtils.waitForAndClick(loginBtn);
        log.info("Logging in...");
    }
}
