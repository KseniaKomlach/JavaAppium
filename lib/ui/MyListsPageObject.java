package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import src.lib.Platform;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL;

    public MyListsPageObject(AppiumDriver driver){super(driver);}

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresentAndClick(
                folder_name_xpath,
                "Cannot find and click list with name " + name_of_folder,
                10
        );
    }
    public void swipeByArticleToDelete(String name_of_article){
        String article_title = getArticleXpathByTitle(name_of_article);
        this.swipeElementToLeft(
                article_title,
                "Cannot find and delete article"
        );
        if (Platform.getInstance().isIOS()){
                this.clickElementToTheRightUpperCorner();
        }
    }
    public void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                10
        );
    }
    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find article by title " + article_title,
                10
        );
    }
    public void closePopUpWindow(){
        this.waitForElementPresentAndClick(
                "xpath://XCUIElementTypeButton[@name='Close']",
                "Cannot find close button",
                20
        );
    }
}

