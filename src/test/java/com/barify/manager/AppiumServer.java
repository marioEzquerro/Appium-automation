package com.barify.manager;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class AppiumServer {
    private static final int PORT = 4723;
    private static final String IP_ADDRESS = "0.0.0.0";
    private static String deviceName = "AppiumEnjoyer";
    private static String appPackage = ""; // com.servipyme.barifyCliente
    private static String appActivity = ""; // com.servipyme.barifyCliente.MainActivity
    private static String platformVersion = "11.0";
    private static String udid = "f83c733f"; // f83c733f (dispositivo físico) | emulator-5554 (emulador)

    private static AppiumDriverLocalService appiumServer;
    private static AppiumServiceBuilder builder;
    private static DesiredCapabilities cap;

    public static void startServer() {
        setCapability();
        setBuilder();

        appiumServer = AppiumDriverLocalService.buildService(getBuilder());
        appiumServer.start();

        System.out.println("Appium Server already running on Port - " + PORT);
    }

    public static void stopServer() {
        appiumServer.stop();

        System.out.println("Appium Server stop at Port - " + PORT);

        Runtime runtime = Runtime.getRuntime();

        try {
            runtime.exec("taskkill /F /IM node.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setCapability() {
        cap = new DesiredCapabilities();
        // cap.setCapability("avd",deviceName); //set the AVD (Android Virtual Device) to be launched
        cap.setCapability("deviceName", deviceName); //set the name of the device to be launched (should be same as AVD)
        cap.setCapability("udid", udid);
        cap.setCapability("platformVersion", platformVersion);
        cap.setCapability("platformName", "Android");
        cap.setCapability("appPackage", appPackage);
        cap.setCapability("appActivity", appActivity);
        cap.setCapability("noReset", true);
    }

    private static void setBuilder() {
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(IP_ADDRESS);
        builder.usingPort(PORT);
        // builder.withCapabilities(cap); // Esto estaba fallando
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
    }

    public static AppiumServiceBuilder getBuilder() {
        return builder;
    }

    public static DesiredCapabilities getCapabilities() {
        return cap;
    }

    public static AppiumDriverLocalService getAppiumServer() {
        return appiumServer;
    }
}
