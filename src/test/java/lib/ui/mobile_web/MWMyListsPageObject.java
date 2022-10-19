package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'page-list')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[@title='Elizabeth II']//a[@aria-controls='mw-watchlink-notification']";
    }
    public MWMyListsPageObject (RemoteWebDriver driver){
        super(driver);
    }
}
