package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.lib.Platform;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
            MORE_OPTIONS_BUTTON,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_LIST_BUTTON,
            ADD_TO_NEW_LIST_OVERLAY,
            LIST_NAME_INPUT,
            LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            FOLDER_BY_NAME_TPL;

    public ArticlePageObject(AppiumDriver driver){
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
        return title_element.getAttribute("text");
    }
    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 20);
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
        this.waitForElementPresentAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X or back button",
                15
        );
    }
    public void addArticleToMySaved(){
        this.waitForElementPresentAndClick(OPTIONS_ADD_TO_LIST_BUTTON, "Cannot find button to add article to saved", 15);
    }
}
