package src.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import src.lib.Platform;
import src.lib.ui.SearchPageObject;
import src.lib.ui.android.AndroidSearchPageObject;
import src.lib.ui.ios.iOSSearchPageObject;

public class SearchPageObjectFactory {
    public static SearchPageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return  new AndroidSearchPageObject(driver);
        } else {
            return new iOSSearchPageObject(driver);
        }
    }
}
