package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.taskdefs.Java;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    public Boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by =  this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void assertElementHasText(String locator, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(locator, "Cannot find element " + locator);
        String actual_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_text,
                actual_text
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by =  this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, long timeoutInSeconds) {
        By by =  this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 10);
    }

    public void waitForElementPresentAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
    }

    public void waitForElementPresentAndClick(String locator, String error_message) {
        waitForElementPresentAndClick(locator, error_message, 5);
    }

    public void waitForElementPresentAndSendKeys(String locator, String error_message, String keys, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(keys);
    }

    public void waitForElementPresentAndSendKeys(String locator, String error_message, String keys) {
        waitForElementPresentAndSendKeys(locator, error_message, keys, 5);
    }

    public void waitForElementPresentAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
    }

    public void waitForElementPresentAndClear(String locator, String error_message) {
        waitForElementPresentAndClear(locator, error_message, 5);
    }

    public void checkAllElementsByXpathContains(String xpath, String word) {
        List titles = driver.findElementsByXPath(xpath);
        titles.stream().forEach(
                (element) -> {
                    WebElement title = (WebElement) element;
                    org.junit.Assert.assertTrue("At least one title does not contain '" + word + "'", title.getAttribute("text").contains(word));
                });
    }
    @Step("Scrolling web page up (only for mobile web)")
    public void scrollWebPageUp(){
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message);
        while (!this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped>max_swipes){
                org.junit.Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }
    @Step("Swiping up")
    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release().perform();
        } else {
            System.out.println("Method swipeUp(int timeOfSwipe) does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Swiping up to find element by locator '{locator}', max swipes = '{max_swipes}'")
    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by =  this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped >= max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUp(2000);
            ++already_swiped;
        }
    }
    @Step("Swiping up till element appear")
    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)){
            if (already_swiped>max_swipes){
                org.junit.Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUp(2000);
            ++already_swiped;
        }
    }
    @Step("Is element located on the screen")
    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 1)
                .getLocation().getY();
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor)driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }
    @Step("Swiping quick to find element by locator '{locator}'")
    public void swipeUpToFindElementQuick(String locator, String error_message, int max_swipes) {
        By by =  this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped >= max_swipes) {
                waitForElementPresent(
                        locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUp(200);
            ++already_swiped;
        }
    }
    @Step("Swiping element to left for delete")
    public void swipeElementToLeft(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, error_message, 10);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int middle_y = element.getLocation().getY() + element.getSize().getHeight() / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(PointOption.point(right_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)));
            if (Platform.getInstance().isAndroid()) {
                action.moveTo(PointOption.point(left_x, middle_y));
            } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(PointOption.point(offset_x, 0));
            }
            action.release();
            action.perform();
        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Clicking element to the right upper corner")
    public void clickElementToTheRightUpperCorner(){
        this.waitForElementPresentAndClick(
                "xpath://XCUIElementTypeButton[contains(@name,'swipe action delete')]",
                "Cannot find recycle bin",
                10
        );
    }
    @Step("Getting amount of elements by locator '{locator}'")
    public int getAmountOfElements(String locator){
        By by =  this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    @Step("Checking that element with locator '{locator}' is present")
    public boolean isElementPresent(String locator){
        return getAmountOfElements(locator) > 0;
    }
    @Step("Try click element with few attempts by locator '{locator}'")
    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts){
            try {
                this.waitForElementPresentAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e){
                if (current_attempts>amount_of_attempts){
                this.waitForElementPresentAndClick(locator, error_message, 1);}
            }
        }
        ++current_attempts;
    }
    @Step("Make sure that elment with locator '{locator} is not present'")
    public void assertElementNotPresent(String locator, String error_message){
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements>0){
            String default_message = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(default_message + ". " + error_message);
        }
    }
    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    @Step("Make sure that element with locator '{locator} is present'")
    public void assertElementPresent(String locator, String error_message){
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements==0){
            String default_message = "An element '" + locator + "' not present";
            throw new AssertionError(default_message + ". " + error_message);
        }
    }
    @Step("Getting locator by type '{locator_with_type}'")
    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return  By.xpath(locator);
        } else if (by_type.equals("id")){
            return By.id(locator);
        } else if (by_type.equals("css")){
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get typ of locator. Locator: " + locator_with_type);
        }
    }

    @Step("Taking screenshot with name '{name}'")
    public String takeScreenshot(String name){
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+ "/" +name+ "_screenshot.png";
        System.out.println(System.getProperty("user.dir"));
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: "+path);
        } catch (Exception e){
            System.out.println("Cannot take screenshot. Error: "+e.getMessage());
        }
        return path;
    }
    @Attachment
    public static byte[] screenshot(String path){
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e){
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }
}
