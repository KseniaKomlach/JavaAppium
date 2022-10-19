package src.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import src.lib.Platform;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.android.AndroidArticlePageObject;
import src.lib.ui.ios.iOSArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return  new AndroidArticlePageObject(driver);
        } else {
            return new iOSArticlePageObject(driver);
        }
    }
}
