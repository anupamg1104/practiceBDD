package com.framework.stepdefinitions;

import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.framework.driver.DriverFactory;

public class PageLoadStepDefinition {

    @Then("I verify that the page load time for URL {string} is less than  milliseconds")
    public void iVerifyPageLoadTime(String url, long maxLoadTime) {
        DriverFactory.getDriver().get(url);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        long loadTime = (Long) js.executeScript(
                "return window.performance.timing.loadEventEnd - window.performance.timing.navigationStart;");

        System.out.println("Load time for URL: " + url + " is: " + loadTime + " ms");
        Assert.assertTrue(loadTime <= maxLoadTime , "Page load time exceeded for URL: " + url);
    }
}
