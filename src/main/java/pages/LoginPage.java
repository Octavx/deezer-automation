package pages;

import configuration.TestConfigurationSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumUtils;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//nav[@class='topbar']")
    private WebElement trait;

    @FindBy(xpath = "//a[@id='switch-log-method-link']")
    private WebElement switchLogMethod;

    @FindBy(xpath = "//input[@id='login_mail']")
    private WebElement emailAddressBox;

    @FindBy(xpath = "//input[@id='login_password']")
    private WebElement passwordBox;

    // elements are instantiated when accessed!
    @FindBy(xpath = "//button[@id='login_form_submit']")
    private WebElement loginButton;

    public boolean isAt() {
        return seleniumUtils.isElementVisible(trait);
    }

    public LoginPage() {
        super();
    }

    public void loginWithCredentials() {
        log.info("Logging in with credentials...");
        seleniumUtils.waitForAndClick(switchLogMethod);
        seleniumUtils.clearElementAndTypeText(emailAddressBox, testConfigurationSingleton.getProp().getDeezerLogin());
        seleniumUtils.clearElementAndTypeText(passwordBox, testConfigurationSingleton.getProp().getDeezerPassword());
        seleniumUtils.waitForAndClick(loginButton);
    }
}
