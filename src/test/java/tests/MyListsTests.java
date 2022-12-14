package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.ui.*;
import org.junit.Test;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;

@Epic("Tests for saved articles to list")
public class MyListsTests extends CoreTestCase {
    private static final String login = "Ksuharik2000";
    private static final String password = "Ksuharik2000!";
    //@Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="My lists")})
    @DisplayName("Save one article to my list")
    @Description("Search article, open it, add to saved, go to my lists, delete article from my list")
    @Step("Starting testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.MINOR)
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

    //Ex17
    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="My lists")})
    @DisplayName("Save two articles to my list")
    @Description("Search article, open it, add to saved, authorization for mobile web, search second article, open it< add to saved, go to my lists, delete one article from my list")
    @Step("Starting testSavingTwoArticles")
    @Severity(value = SeverityLevel.MINOR)
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
