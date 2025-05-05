package com.framework.stepdefinitions;


import com.framework.utils.ConfigReader;
import io.cucumber.java.en.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.framework.factory.BrowserFactory;

public class LoadTimeSteps {

    private WebDriver driver;
    private long loadTime;

    @Given("I launch the website")
    public void i_launch_the_website() {
        driver = BrowserFactory.getDriver();
        String url = ConfigReader.getProperty("base.url");
        driver.get(url);
    }

    @When("I capture load times")
    public void i_capture_load_times() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        loadTime = loadEventEnd - navigationStart;
        System.out.println("Total Page Load Time: " + loadTime + " ms");
    }

    @Then("validate load time is within threshold")
    public void validate_load_time_is_within_threshold() {
        int threshold = Integer.parseInt(ConfigReader.getProperty("page.load.timeout")) * 1000;
        System.out.println("page.load.timeout in property point: "+threshold +" ms");
        Assert.assertTrue(loadTime < threshold, "Page load time exceeded threshold! Actual: " + loadTime + "ms, Threshold: " + threshold + "ms");
    }
}
