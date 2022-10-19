package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{
    private static final String
        STEP_LEARN_MORE_LINK = "xpath://*[@name='Learn more about Wikipedia']",
        STEP_NEW_WAY_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_SEARCH_IN_OVER_300_LANGUAGES_TEXT = "id:Search in over 300 languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT = "xpath://*[@name='Learn more about data collected']",
        NEXT_LINK = "xpath://*[@name='Next']",
        GET_STARTED_BUTTON = "xpath://*[@name='Get started']",
        SKIP_LINK = "xpath://XCUIElementTypeStaticText[@name='Skip']";



    public WelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Waiting for 'learn more' link")
    public void waitForLearnMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 15);
        screenshot(this.takeScreenshot("welcome_page"));
    }
    @Step("Waiting for 'new way to explore' text")
    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(STEP_NEW_WAY_TO_EXPLORE_TEXT, "Cannot find 'New way to explore' text", 15);
    }
    @Step("Waiting for 'search in over 300 languages' text")
    public void waitForSearchInOver300LanguagesText(){
        this.waitForElementPresent(STEP_SEARCH_IN_OVER_300_LANGUAGES_TEXT, "Cannot find 'Search in over 300 languages' text", 15);
    }
    @Step("Waiting for 'learn more about data collected' text")
    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT, "Cannot find 'Learn more about data collected' link", 15);
    }
    @Step("Clicking next button")
    public void clickNextButton(){
        this.waitForElementPresentAndClick(NEXT_LINK, "Cannot find 'Next' button", 15);
    }
    public void clickGetStartedButton(){
        this.waitForElementPresentAndClick(GET_STARTED_BUTTON, "Cannot find 'Get started' button", 15);
    }
    @Step("Clicking skip welcome page")
    public void clickSkip(){
        this.waitForElementPresentAndClick(SKIP_LINK, "Cannot find and click skip welcome page link");
    }
}
