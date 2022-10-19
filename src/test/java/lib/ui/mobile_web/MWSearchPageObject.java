package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.clear";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(text(),'{SUBSTRING}')]"; //xpath://div[contains(@class, 'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://a[@data-title='{TITLE}']//*[contains(text(),'{SUBSTRING}')]";
                //"xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]" +
                //"/following-sibling::XCUIElementTypeStaticText[contains(@name,'{DESCRIPTION}')]";
        EMPTY_RESULTS_LABEL = "css:p.without-results";
        TITLE_OF_SEARCH_RESULT = "xpath://a[@data-title='{TITLE']"; //xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
