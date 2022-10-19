package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver){super(driver);}

    @Step("Opening navigation menu (only for mobile web)")
    public void openNavigation(){
        if (Platform.getInstance().isMW()){
            this.waitForElementPresentAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Click for reload to My List")
    public void clickMyLists(){
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My lists",
                    5
            );
        } else {
            this.waitForElementPresentAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My lists"
            );
        }
    }

}
