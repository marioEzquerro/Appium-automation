package com.saucelabs.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class PagesFactory {

    private static PagesFactory pagesFactories;
    private final AndroidDriver driver;
    private LoginPage loginPage;

    public PagesFactory(AndroidDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public static void start(AndroidDriver driver) {
        pagesFactories = new PagesFactory(driver);
    }

    public static PagesFactory getInstance() {
        return pagesFactories;
    }


}
