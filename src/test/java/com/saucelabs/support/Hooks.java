package com.saucelabs.support;

import com.saucelabs.pages.PagesFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class Hooks {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public void setUp(Scenario scenario) throws MalformedURLException {
        // variables de entorno
        String username = "username";
        String accessKey = "******";

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("build", "1.0.0-b2");
        sauceOptions.setCapability("name", scenario.getName());

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:app", "storage:filename=rva-1.0.0-b2.apk");
        caps.setCapability("sauce:options", sauceOptions);

        URL sauceUrl = new URL("https://" + username + ":" + accessKey + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        driver = new AndroidDriver<>(sauceUrl, caps);
        wait = new WebDriverWait(driver, 5);

        PagesFactory.start(driver);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

}
