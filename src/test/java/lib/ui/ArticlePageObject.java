package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            MORE_OPTIONS_BUTTON,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_NEW_LIST_OVERLAY,
            LIST_NAME_INPUT,
            LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            FOLDER_BY_NAME_TPL;

    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 20);
    }
    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getText();
        }
    }
    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20
            );
        }
    }
    public void swipeQuickToFooter(){
        this.swipeUpToFindElementQuick(FOOTER_ELEMENT, "Cannot find the end of article", 20);
    }
    public void addArticleToMyNewList(String name_of_folder){
        this.waitForElementPresentAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options"
        );
        this.waitForElementPresentAndClick(
                OPTIONS_ADD_TO_LIST_BUTTON,
                "Cannot find option to add article to reading list"
        );
        this.waitForElementPresentAndClick(
                ADD_TO_NEW_LIST_OVERLAY,
                "Cannot find 'Got it' button"
        );
        this.waitForElementPresentAndClear(
                LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder"
        );
        this.waitForElementPresentAndSendKeys(
                LIST_NAME_INPUT,
                "Cannot put text into articles folder input",
                name_of_folder
        );
        this.waitForElementPresentAndClick(
                LIST_OK_BUTTON,
                "Cannot press OK button"
        );
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementPresentAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options"
        );
        this.waitForElementPresentAndClick(
                OPTIONS_ADD_TO_LIST_BUTTON,
                "Cannot find option to add article to reading list"
        );
        String folder_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresentAndClick(
                folder_xpath,
                "Cannot find and click folder with name " + name_of_folder,
                10
        );
    }

    public void closeArticle(){
        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())){
            this.waitForElementPresentAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X or back button",
                    20
            );
        } else {
            System.out.println("Method closeArticle do nothing");
        }
    }
    public void addArticleToMySaved(){
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        tryClickElementWithFewAttempts(OPTIONS_ADD_TO_LIST_BUTTON, "Cannot find button to add article to saved", 10);
    }

    public void removeArticleFromSavedIfItAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementPresentAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find and click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }
}

