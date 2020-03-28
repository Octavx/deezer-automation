package pages;

import models.ChannelsEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MusicChannelPage extends BasePage {

    @FindBy(xpath = "//h1[@class='heading-1']")
    private WebElement heading;

    @FindAll({@FindBy( xpath = "//figure[@class='thumbnail']")})
    private List<WebElement> playlistList;

    String playlistPlayButton = ".//button[@aria-label='Pause']";


    @Override
    public boolean isAt() {
        String channelName = seleniumUtils.getElementText(heading);

        for( ChannelsEnum channel : ChannelsEnum.values() ) {
            if(channel.getChannelName().equals(channelName)) {
                return true;
            }
        }

        return false;
    }

    public List <WebElement> getPlaylistList() {
        return playlistList;
    }

    public void startPlaylistElementByNumber(int num) {
        seleniumUtils.waitForAndClick( playlistList.get(num-1).findElement(By.xpath(playlistPlayButton)) );
    }
}
