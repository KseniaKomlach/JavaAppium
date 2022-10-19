package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
            SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]";
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{DESCRIPTION}']" +
                    "/preceding-sibling::*[@text='{TITLE}']";
            EMPTY_RESULTS_LABEL = "id:org.wikipedia:id/search_empty_view";
            TITLE_OF_SEARCH_RESULT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
            SEARCH_RESULT_ELEMENT = "xpath://android.widget.ListView[@resource-id='org.wikipedia:id/search_results_list']" +
                    "/android.widget.LinearLayout[@resource-id='org.wikipedia:id/page_list_item_container']";
    }
    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
