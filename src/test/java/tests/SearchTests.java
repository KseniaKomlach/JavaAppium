package tests;

import org.junit.Test;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;

public class SearchTests extends CoreTestCase {
    //Ex3
    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Kingdom");
        SearchPageObject.waitForSearchResult("ideo game series");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Linkin Park discography");
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        /*assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );*/
    }
    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("hjghkjgk");
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.asserThereIsNoResultOfSearch();
    }
    @Test
    public void testCheckResultsOfSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Kingdom");
        SearchPageObject.waitForSearchResult("Video game series");
        SearchPageObject.checkAllResultsOfSearchContains("Kingdom");
    }
    //Ex12
    @Test
    public void testFind3ArticlesByTitleAndDescription(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Kingdom");
        SearchPageObject.waitForElementByTitleAndDescription("Kingdom Hearts", "Video game series");
        SearchPageObject.waitForElementByTitleAndDescription("Kingdom of Great Britain", "Constitutional monarchy in Western Europe (1707–1800)");
        SearchPageObject.waitForElementByTitleAndDescription("Kingdom of Yugoslavia", "Country in southeastern Europe, 1918–1941");
    }
}
