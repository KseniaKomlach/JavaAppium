package tests;

import org.junit.Test;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;

public class GetStartedTest extends CoreTestCase {
    @Test
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
