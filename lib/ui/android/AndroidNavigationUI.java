package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[content-desc='My lists']";
    }
    public AndroidNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
