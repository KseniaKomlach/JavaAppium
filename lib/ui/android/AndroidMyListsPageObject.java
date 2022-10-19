package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
                FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']";
                ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }
    public AndroidMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
