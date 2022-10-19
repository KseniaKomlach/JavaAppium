package src.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import src.lib.Platform;
import src.lib.ui.NavigationUI;
import src.lib.ui.android.AndroidNavigationUI;
import src.lib.ui.ios.iOSNavigationUI;

public class NavigationUIFactory {
    public static NavigationUI get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(driver);
        } else {
            return new iOSNavigationUI(driver);
        }
    }
}
