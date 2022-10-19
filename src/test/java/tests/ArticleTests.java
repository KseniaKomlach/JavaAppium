package tests;

import org.junit.Test;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testSwipeArticle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
    @Test
    public void testAssertTitleOfArticlePresent(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Appium";
        String substring = "Automation for Apps";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring);
        ArticlePageObject.waitForTitleElement();
    }
}
