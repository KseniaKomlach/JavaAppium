package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {
    //Ex3
    @Test
    @Feature(value="Search")
    @DisplayName("Test cancel search")
    @Description("Search article, cancel search")
    @Step("Starting testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
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
    @Feature(value="Search")
    @DisplayName("Test amount of not empty search")
    @Description("Search article, check that articles are found")
    @Step("Starting testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Linkin Park discography");
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }
    @Test
    @Feature(value="Search")
    @DisplayName("Test amount of empty search")
    @Description("Search article by text 'hjghkjgk', check that articles not found")
    @Step("Starting testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("hjghkjgk");
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.asserThereIsNoResultOfSearch();
    }
    @Test
    @Feature(value="Search")
    @DisplayName("Test check results of search")
    @Description("Search by text Kingdom, check that all results contains this word")
    @Step("Starting testCheckResultsOfSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckResultsOfSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Kingdom");
        SearchPageObject.waitForSearchResult("Video game series");
        SearchPageObject.checkAllResultsOfSearchContains("Kingdom");
    }
    //Ex12
    @Test
    @Feature(value="Search")
    @DisplayName("Test find 3 articles")
    @Description("Search by text and description and find 3 articles, check that title and description correct")
    @Step("Starting testCheckResultsOfSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testFind3ArticlesByTitleAndDescription(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Kingdom");
        SearchPageObject.waitForElementByTitleAndDescription("Kingdom Hearts", "Video game series");
        SearchPageObject.waitForElementByTitleAndDescription("Kingdom of Great Britain", "Constitutional monarchy in Western Europe (1707–1800)");
        SearchPageObject.waitForElementByTitleAndDescription("Kingdom of Yugoslavia", "Country in southeastern Europe, 1918–1941");
    }
}
