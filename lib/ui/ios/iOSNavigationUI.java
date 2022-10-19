package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI{

        static {
            MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
        }
        public iOSNavigationUI(AppiumDriver driver){
            super(driver);
        }
}
