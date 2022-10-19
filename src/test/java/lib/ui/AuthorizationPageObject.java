package lib.ui;


import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BUTTON = "css:div.drawer a.mw-ui-button",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt",
            MOBILE_VIEW_LINK = "css:a.stopMobileRedirectToggle";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

     public void clickAuthButton(){
        tryClickElementWithFewAttempts(LOGIN_BUTTON, "Cannot find and click auth button", 20);
     }
     public void enterLoginData(String login, String password){
        this.waitForElementPresentAndSendKeys(LOGIN_INPUT, "Cannot find and put a login to the login input", login);
        this.waitForElementPresentAndSendKeys(PASSWORD_INPUT,"Cannot find and put a password to the password input", password);
     }
     public void submitForm(){
        this.waitForElementPresentAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button");
        this.waitForElementPresentAndClick(MOBILE_VIEW_LINK, "Cannot find and click link for reload to mobile version");
     }
}
