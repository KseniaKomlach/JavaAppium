package tests;

import lib.ui.*;
import org.junit.Test;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class MyListsTests extends CoreTestCase {
    private static final String login = "Ksuharik2000";
    private static final String password = "Ksuharik2000!";
    //@Test
    public void testSaveFirstArticleToMyList() {
        String search_line = "Kingdom";
        String substring = "Video game series";
        String name_of_folder = "Folder";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring);

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        } else {
            MyListPageObject.closePopUpWindow();
        }
        MyListPageObject.waitForArticleToAppearByTitle(search_line);
        MyListPageObject.swipeByArticleToDelete(search_line);
        MyListPageObject.waitForArticleToDisappearByTitle(search_line);
    }
    @Test
    public void testSavingTwoArticles() throws InterruptedException {
        String search_line = "Kingdom";
        String substring = "Video game series";

        String search_line_second = "Elizabeth II";
        String substring_second = "Queen of the United Kingdom";

        String name_of_folder = "Folder";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring);

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()){
            substring = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToMyNewList(name_of_folder);
        } else if (Platform.getInstance().isIOS()){
            ArticlePageObject.addArticleToMySaved();
        } else {
            ArticlePageObject.addArticleToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_second);
        SearchPageObject.clickByArticleWithSubstring(substring_second);

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()){
            substring_second = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToMyNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()){
            MyListPageObject.closePopUpWindow();
        }
        MyListPageObject.swipeByArticleToDelete(search_line_second);
        MyListPageObject.waitForArticleToDisappearByTitle(search_line_second);
        MyListPageObject.waitForArticleToAppearByTitle(search_line);
    }
}
