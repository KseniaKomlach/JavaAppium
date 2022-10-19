package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.Platform;
import org.junit.Assert;
import org.junit.Test;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Swipe article to the footer")
    @Description("Init search input, search 'Appium', open article with description 'Automation for Apps and swipe up to footer'")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
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
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Compare article title with expected one")
    @Description("Open 'Java Object-oriented programming language' article and make sure the title is expected")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "We see unexpected title!",
                "JavaScript",
                article_title
        );
    }
}
