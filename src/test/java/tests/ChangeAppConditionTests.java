package tests;

import lib.Platform;
import org.junit.Test;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;



public class ChangeAppConditionTests extends CoreTestCase {
    @Test
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
            assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_rotation
            );
            this.rotateScreenPortrait();
            String title_after_second_rotation = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_second_rotation
            );
    }
}
