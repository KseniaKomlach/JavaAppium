package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            REMOVE_FROM_SAVED_BUTTON,
            ARTICLE_BY_TITLE_TPL;

    public MyListsPageObject(RemoteWebDriver driver){super(driver);}

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    @Step("Opening folder by name '{name_of_folder}'")
    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresentAndClick(
                folder_name_xpath,
                "Cannot find and click list with name " + name_of_folder,
                10
        );
    }
    @Step("Swiping article for delete by name '{name_of_article}'")
    public void swipeByArticleToDelete(String name_of_article){
        String article_title = getArticleXpathByTitle(name_of_article);
        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())){
            this.swipeElementToLeft(
                    article_title,
                    "Cannot find and delete article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementPresentAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }
        if (Platform.getInstance().isIOS()){
                this.clickElementToTheRightUpperCorner();
        }
        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
    }
    @Step("Waiting article to disappear by its title '{article_title}'")
    public void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                10
        );
    }
    @Step("Waiting article to appear by its title '{article_title}'")
    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find article by title " + article_title,
                10
        );
    }
    @Step("Closing pop up window (only for ios)")
    public void closePopUpWindow(){
        screenshot(this.takeScreenshot("pop_up"));
        this.waitForElementPresentAndClick(
                "xpath://XCUIElementTypeButton[@name='Close']",
                "Cannot find close button",
                20
        );
    }
}

