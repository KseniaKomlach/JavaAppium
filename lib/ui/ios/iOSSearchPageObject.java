package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[contains(@name,'Search Wikipedia')]";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "id:Clear text";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]" +
                "/following-sibling::XCUIElementTypeStaticText[contains(@name,'{DESCRIPTION}')]";
        EMPTY_RESULTS_LABEL = "id:No results found";
        TITLE_OF_SEARCH_RESULT = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther";
    }

    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
