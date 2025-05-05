package com.framework.stepdefinitions;

import com.framework.factory.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class StepDefinitions {

    WebDriver driver;

    @Before
    public void setUp() {
        BrowserFactory.setDriver("chrome");
        driver = BrowserFactory.getDriver();
    }

    @Given("User navigates to {string}")
    public void userNavigatesTo(String url) {
        driver.get(url);
    }

    @When("User performs some actions")
    public void userPerformsSomeActions() {
        // Add actions
    }

    @Then("User should see expected result")
    public void userShouldSeeExpectedResult() {
        // Add validations
    }

    @After
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}
