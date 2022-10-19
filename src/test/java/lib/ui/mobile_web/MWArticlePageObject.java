package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        MORE_OPTIONS_BUTTON = ""; //xpath://android.widget.ImageView[@content-desc='More options']";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_LIST_BUTTON = "xpath://*[@id='ca-watch'][@title='Watch']";
        ADD_TO_NEW_LIST_OVERLAY = ""; //xpath://XCUIElementTypeImage/following-sibling::XCUIElementTypeStaticText[contains(@name,'to a reading list?')]";
        LIST_NAME_INPUT = ""; //xpath://XCUIElementTypeStaticText[@name='Reading list name']/following-sibling:XCUIElementTypeTextField";
        LIST_OK_BUTTON = ""; //xpath://XCUIElementTypeButton[@name='Create reading list']";
        CLOSE_ARTICLE_BUTTON = "";
        FOLDER_BY_NAME_TPL = ""; //id:{FOLDER_NAME}",
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://*[@id='ca-watch'][@title='Remove this page from your watchlist']";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
