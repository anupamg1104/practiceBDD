package com.framework.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    // Thread-safe WebDriver
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // Set up driver based on browser name
    public static void setDriver(String browser) {
        if (browser == null) {
            throw new IllegalArgumentException("Browser name is null");
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                tlDriver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                tlDriver.set(new FirefoxDriver(firefoxOptions));
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    // Get the driver
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // Quit driver and remove from thread
    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
