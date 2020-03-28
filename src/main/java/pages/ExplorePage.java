package pages;

import models.ChannelsEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class ExplorePage extends BasePage {

    @FindBy(xpath = "//h1[@class='heading-1']")
    private WebElement heading;

    @FindBy(xpath = "//a[@href='/en/channels/rap']")
    private WebElement rapChannel;

    @FindAll({@FindBy( xpath = "//a[@class='thumbnail thumbnail-rectangle thumbnail-channel']")})
    private List<WebElement> channelsList;

    String channelTextLocator = ".//p[@class='title-text']";

    public ExplorePage() {
        super();
    }

    @Override
    public boolean isAt() {
        return seleniumUtils.getElementText(heading).equals("All Channels");
    }

    public void selectChannel(ChannelsEnum channel) {
        WebElement selectedChannel = channelsList.stream()
                .filter(p -> p.getText().equals(channel.getChannelName()))
                .findFirst()
                .orElseThrow(() -> new ElementNotVisibleException("Element containing text " + channel.getChannelName() + " cannot be found"));

        seleniumUtils.waitForAndClick(selectedChannel);
        log.info("Channel " + channel.getChannelName() + " selected");
    }

    public void selectRapChannel() {
        seleniumUtils.waitForAndClick(rapChannel);
    }
}
