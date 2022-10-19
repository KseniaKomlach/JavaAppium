package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
    }
    public iOSMyListsPageObject (AppiumDriver driver){
        super(driver);
    }
}
