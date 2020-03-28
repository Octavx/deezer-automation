package steps;

import configuration.TestConfigurationSingleton;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.ChannelsEnum;
import org.junit.Assert;
import pages.*;

public class SmokeSteps {

    // initialize classes - elements are instantiated when accessed!
    TestConfigurationSingleton testConfigurationSingleton = TestConfigurationSingleton.getInstance();

    // pages
    MainPage mainPage = new MainPage();
    UserHomePage userHomePage = new UserHomePage();
    LoginPage loginPage = new LoginPage();
    ExplorePage explorePage = new ExplorePage();
    MusicChannelPage musicChannelPage = new MusicChannelPage();


    @Given("^I am on Deezer main page$")
    public void iAmOnDeezerMainPage() {
        Assert.assertTrue("Deezer main page is not open!", mainPage.isAt());
        System.out.println("Main page open!");
    }


    @When("^I login with email address credentials saved in properties file$")
    public void iLoginWithEmailAddressCredentialsSavedInPropertiesFile() {
        mainPage.clickOnLogin();
        loginPage.loginWithCredentials();
    }


    @Then("^Deezer user homepage is displayed$")
    public void deezerUserHomepageIsDisplayed() {
        Assert.assertTrue("Deezer user homepage is not open!", userHomePage.isAt());
        userHomePage.closeAlertTopbar();
    }

    @When("^I go to Music section, expecting page to open$")
    public void iGoToMusicSection() {
        userHomePage.goToMusicSection();
        Assert.assertTrue("Music section is not open!", userHomePage.isAt());
    }

    @And("^I start to play flow selection$")
    public void iStartToPlayFlowSelection() {
        userHomePage.playFlow();
    }

    @Then("^Music starts playing$")
    public void musicStartsPlaying() {
        Assert.assertTrue("The music did not start playing! ", userHomePage.isMusicPlaying());
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchText) {
        userHomePage.searchInMusic(searchText);
    }

    @And("^I select the \"([^\"]*)\" section$")
    public void iSelectTheSection(String section) {
        userHomePage.selectSection(section);
    }

    @And("^I start to play result number ([^\"]*)$")
    public void iStartToPlayResultNumber(int number) {
        userHomePage.playSearchResultNumber(number);
    }

    @When("^I go to Explore section, expecting page to open$")
    public void iGoToExploreSection() {
        userHomePage.goToExploreSection();
        Assert.assertTrue("Explore section is not open!", explorePage.isAt());
    }

    @And("^I choose ([^\"]*) channel, expecting page to open$")
    public void iChooseChannel(ChannelsEnum channel) {
        explorePage.selectChannel(channel);
        Assert.assertTrue("Explore section is not open!", musicChannelPage.isAt());
    }

    @Then("^Several playlists are displayed$")
    public void severalPlaylistsAreDisplayed() {
        Assert.assertTrue("There are no playlists displayed!", musicChannelPage.getPlaylistList().size() > 0 );
    }

    @When("^I start to play the playlist number ([^\"]*)$")
    public void iStartToPlayThePlaylistNumber(int order) {
        musicChannelPage.startPlaylistElementByNumber( order );
    }
}
