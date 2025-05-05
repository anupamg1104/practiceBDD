package com.framework.base;

import com.framework.factory.BrowserFactory;
import com.framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Load configuration properties
        ConfigReader.loadConfig();

        // Get browser name from config
        String browser = ConfigReader.getProperty("browser");

        // Initialize WebDriver via factory
        BrowserFactory.setDriver(browser);
        driver = BrowserFactory.getDriver();

        if (driver != null) {
            driver.manage().window().maximize();
        } else {
            throw new RuntimeException("WebDriver is null! Please check browser setup.");
        }
    }

    @AfterMethod
    public void tearDown() {
        // Clean up driver
        BrowserFactory.quitDriver();
    }
}
