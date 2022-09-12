package com.saucelabs.pages;

import com.saucelabs.utils.MyFluentWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractPage {
    protected MyFluentWait<AndroidDriver> wait;
    private final AndroidDriver driver;

    AbstractPage(AndroidDriver driver) {
        this.driver = driver;
        wait = new MyFluentWait<>(driver)
                .withTimeout(20, ChronoUnit.SECONDS)
                .pollingEvery(2, ChronoUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public abstract WebElement getPageLoadedTestElement();

    protected AndroidDriver getDriver() {
        return driver;
    }

    protected Wait<AndroidDriver> getWait() {
        return wait;
    }

    protected void setWait(MyFluentWait<AndroidDriver> wait) {
        this.wait = wait;
    }

    public void waitForPageLoad() {
        WebElement testElement = getPageLoadedTestElement();
        wait.until(ExpectedConditions.visibilityOf(testElement));
    }

//    protected void moveTo(WebElement elem) {
//        if (driver.getCapabilities().getBrowserName().equals("firefox")) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
//        } else {
//            Actions actions = new Actions(driver);
//            actions.moveToElement(elem).build().perform();
//        }
//    }

    protected void scroll(int startX, int startY, int endX, int endY) {
        getDriver().manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        TouchAction action = new TouchAction((PerformsTouchActions) getDriver());

        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }

    protected boolean isPageLoaded(WebElement elem) {
        boolean isLoaded = false;

        try {
            isLoaded = elem.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return isLoaded;
    }

    public void navigateTo(String url) {
        AppiumDriver driver = getDriver();

        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            if (e instanceof TimeoutException) {
                log.info("Timeout loading home page");
            } else if (e instanceof ScriptTimeoutException) {
                log.info("Script Timeout loading home page");
            } else {
                log.error(e.getMessage());
            }
        }
    }
}
