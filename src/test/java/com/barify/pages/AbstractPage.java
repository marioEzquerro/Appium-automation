package com.barify.pages;

import com.barify.utils.MyFluentWait;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractPage {
    protected Wait<WebDriver> wait;
    private final WebDriver driver;

    AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new MyFluentWait<>(driver)
                .withTimeout(60, ChronoUnit.SECONDS)
                .pollingEvery(2, ChronoUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public abstract WebElement getPageLoadedTestElement();

    protected WebDriver getDriver() {
        return driver;
    }

    protected Wait<WebDriver> getWait() {
        return wait;
    }

    protected void setWait(Wait<WebDriver> wait) {
        this.wait = wait;
    }

    public void waitForPageLoad() {
        WebElement testElement = getPageLoadedTestElement();
        wait.until(ExpectedConditions.visibilityOf(testElement));
    }

    protected void moveTo(WebElement elem) {
        if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equals("firefox")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
        } else {
            Actions actions = new Actions(driver);
            actions.moveToElement(elem).build().perform();
        }
    }


    /* TODO implementar para scroll con 1 parametro de distancia
            int start_x = (int) (dimension.width * 0.5);
            int start_y = (int) (dimension.height * 0.2);

            int end_x = (int) (dimension.width * 0.5);
            int end_y = (int) (dimension.height * 0.6);*/
    protected void scroll(int startX, int startY, int endX, int endY) {
        getDriver().manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        TouchAction action = new TouchAction((AndroidDriver) getDriver());

        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }

   /* DEPRECADO O NO
   protected void scrollTop() {
        getDriver().manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        Dimension dimension = getDriver().manage().window().getSize();

        int start_x = (int) (dimension.width * 0.5);
        int start_y = (int) (dimension.height * 0.2);

        int end_x = (int) (dimension.width * 0.5);
        int end_y = (int) (dimension.height * 0.6);

        TouchAction action = new TouchAction((AndroidDriver) getDriver());

        action.press(PointOption.point(start_x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(end_x, end_y)).release().perform();
    }
    protected void scrollBottom() {
        getDriver().manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        Dimension dimension = getDriver().manage().window().getSize();

        int start_x = (int) (dimension.width * 0.5);
        int start_y = (int) (dimension.height * 0.6);

        int end_x = (int) (dimension.width * 0.5);
        int end_y = (int) (dimension.height * 0.2);

        TouchAction action = new TouchAction((AndroidDriver) getDriver());

        action.press(PointOption.point(start_x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(end_x, end_y)).release().perform();
    }*/

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
        WebDriver driver = getDriver();

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
