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


@Epic("Tests with change app condition")
public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Check search article in background")
    @Description("Search article, open it, remove the app to the background, open it and check that article not change")
    @Step("Starting testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckSearchArticleInBackground(){
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Change rotation of app")
    @Description("Search article, open it, change rotation to landscape? then change rotation to portrait")
    @Step("Starting testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeScreenOrientationOnSearchResults(){
        if ((Platform.getInstance().isMW()) || (Platform.getInstance().isIOS())) {
            return;
        }
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
            String title_before_rotation = ArticlePageObject.getArticleTitle();
            this.rotateScreenLandscape();
            String title_after_rotation = ArticlePageObject.getArticleTitle();
            Assert.assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_rotation
            );
            this.rotateScreenPortrait();
            String title_after_second_rotation = ArticlePageObject.getArticleTitle();
            Assert.assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_second_rotation
            );
    }
}
