package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;

@Epic("Tests for welcome page on iOS")
public class GetStartedTest extends CoreTestCase {
    @Test
    @Feature(value = "Welcome page")
    @DisplayName("Pass through welcome page")
    @Description("Wait more link, click next button and etc")
    @Step("Starting testPassThroughWelcome")
    @Severity(value = SeverityLevel.MINOR)
    public void testPassThroughWelcome(){
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())){
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForSearchInOver300LanguagesText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }
}
