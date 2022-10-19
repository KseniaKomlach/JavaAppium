package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            EMPTY_RESULTS_LABEL,
            TITLE_OF_SEARCH_RESULT,
            SEARCH_RESULT_ELEMENT;


    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementByTitleAndDescription(String title, String description){
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{DESCRIPTION}", description).replace("{TITLE}", title);
    }

    public void initSearchInput(){
        this.waitForElementPresentAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element");
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input after clicking search init element");
    }
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button");
    }
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 10);
    }
    public void clickCancelSearch(){
        this.waitForElementPresentAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button");
    }
    public void typeSearchLine(String search_line){
        this.waitForElementPresentAndSendKeys(SEARCH_INPUT, "Cannot find and type into search input", search_line);
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 30);
    }
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresentAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 30);
    }
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                30
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultLabel(){
        this.waitForElementPresent(
                EMPTY_RESULTS_LABEL,
                "Cannot find empty result label by the request ",
                30
        );
    }
    public void asserThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }
    public void checkAllResultsOfSearchContains(String word) {
        List titles = driver.findElementsByXPath(TITLE_OF_SEARCH_RESULT);
        titles.stream().forEach(
                (element) -> {
                    WebElement title = (WebElement) element;
                    Assert.assertTrue("At least one title does not contain '" + word + "'", title.getAttribute("text").contains(word));
                });
    }
    public void waitForElementByTitleAndDescription(String title, String description){
        String article_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find article with title '" + title + "' and description '" + description +"'",
                30
        );
    }
    public void findForElementByTitleAndDescription(String title, String description){
        String article_xpath = getResultSearchElementByTitleAndDescription(title, description);
        driver.findElementByXPath(article_xpath);
    }
}
