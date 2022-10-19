package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
                TITLE = "id:org.wikipedia:id/view_page_title_text";
                MORE_OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
                FOOTER_ELEMENT = "id:org.wikipedia:id/page_license_text";
                OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
                OPTIONS_ADD_TO_LIST_BUTTON = "xpath://android.widget.TextView[@text='Add to reading list']";
                ADD_TO_NEW_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
                LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
                LIST_OK_BUTTON = "xpath://android.widget.Button[@text='OK']";
                CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']";
    }
    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
