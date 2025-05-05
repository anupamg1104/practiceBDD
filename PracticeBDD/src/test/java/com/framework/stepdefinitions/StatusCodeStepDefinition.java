package com.framework.stepdefinitions;

import org.testng.Assert;

import com.framework.utils.APIUtils;

import io.cucumber.java.en.Then;

public class StatusCodeStepDefinition {

    @Then("I verify the status code of URL {string} is ")
    public void iVerifyTheStatusCode(String url, int expectedStatusCode) {
        int actualStatusCode = APIUtils.getStatusCode(url);
        Assert.assertEquals(expectedStatusCode, actualStatusCode, "Status code mismatch for URL: " + url);
    }
}
