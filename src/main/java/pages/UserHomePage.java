package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class UserHomePage extends BasePage {

    @FindBy(xpath = "//h2[@class='heading-2']")
    private WebElement heading;

    @FindBy(xpath = "//a[@href='/en/']//span[@class='sidebar-nav-label']")
    private WebElement musicSection;

    @FindBy(xpath = "//a[@href='/en/channels/explore']//span[@class='sidebar-nav-label']")
    private WebElement exploreSection;

    @FindAll({@FindBy( xpath = "//span[@class='sidebar-nav-label']")})
    private List<WebElement> sections;

    @FindBy(xpath = "//div[@class='alert-wrapper']")
    private WebElement alertWrapper;

    @FindBy(xpath = "//div[@class='alert-wrapper']//button[@type='button']")
    private WebElement alertWrapperCloseButton;

    @FindBy(xpath = "//li[@class='thumbnail-col flow']//button[@class='action-item-btn action-force']")
    private WebElement playFlowThumbnail;

    @FindBy(xpath = "//li[@class='thumbnail-col smarttracklist']//button[@class='action-item-btn action-force']")
    private WebElement playFirstSuggestion;

    @FindBy(xpath = "//div[@class='player-controls']//button[@aria-label='Play']")
    private WebElement buttonCtrlPlay;

    @FindBy(xpath = "//div[@class='player-controls']//button[@aria-label='Pause']")
    private WebElement buttonCtrlPause;

    @FindBy(xpath = "//input[@id='topbar-search']")
    private WebElement searchMusicBox;

    @FindBy(xpath = "//button[@class='topbar-search-submit']")
    private WebElement searchButton;

    @FindAll({@FindBy( xpath = "//a[@class='navbar-link']")})
    private List<WebElement> navbarSearchElementsList;

    @FindAll({@FindBy( xpath = "//li[@class='thumbnail-col']//button[@aria-label='Pause']")})
    private List<WebElement> navbarSearchResultsPlay;

//    private final String ALERT_WRAPPER_TEXT = "Get the full Deezer experience with our desktop app!";

    public boolean isAt() {
        return seleniumUtils.getElementText(heading).equals("Made for you");
    }

    public UserHomePage() {
        super();
    }

    public void goToMusicSection() {
        seleniumUtils.waitForAndClick(musicSection);
        log.info("Going to Music section.");
    }

    public void goToExploreSection() {
        seleniumUtils.waitForAndClick(exploreSection);
        log.info("Going to Explore section.");
    }

    public void playFlow() {
        seleniumUtils.waitForAndClick(playFlowThumbnail);
        log.info(">>>>>>> Started playing flow >>>>>>>.");
    }

    public boolean isMusicPlaying() {
        boolean playing = seleniumUtils.isElementVisible(buttonCtrlPause);

        if (playing) {
            log.info("Music is playing!");
            return true;
        }
        else {
            log.info("Music is NOT playing!");
            return false;
        }
    }

    public void searchInMusic(String search) {
        seleniumUtils.clearElementAndTypeText(searchMusicBox, search);
        searchButton.click();
        log.info("Searching for" + search + " .");
    }

    public void selectSection(String section) {
        seleniumUtils.waitForAndClick(seleniumUtils.searchElementWithTextFromList(Objects.requireNonNull(navbarSearchElementsList), section));
        log.info("Section " + section + " selected");
    }

    public void playSearchResultNumber(int number) {
        navbarSearchResultsPlay.get( number-1 ).click();
        log.info("Playing search result number " + number);
    }

    public void closeAlertTopbar() {
        try {
            seleniumUtils.waitForAndClick(alertWrapperCloseButton);
        } catch (NoSuchElementException e) {
            log.info("Alert topbar not present, moving on...");
        }

    }

}
