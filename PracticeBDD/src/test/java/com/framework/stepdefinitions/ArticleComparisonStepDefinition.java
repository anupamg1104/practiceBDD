package com.framework.stepdefinitions;

import io.cucumber.java.en.Then;

import com.framework.utils.APIUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.framework.driver.DriverFactory;

public class ArticleComparisonStepDefinition {

    @Then("I compare the article content between UI URL {string} and API endpoint {string}")
    public void iCompareArticleContent(String uiUrl, String apiEndpoint) {
    	DriverFactory.getDriver().get(uiUrl);
        String uiContent = DriverFactory.getDriver().findElement(By.tagName("body")).getText();

        String apiContent = APIUtils.getResponseBodyAsString(apiEndpoint);

        Assert.assertTrue(uiContent.contains(apiContent) || apiContent.contains(uiContent) , "Content mismatch between UI and API");
        System.out.println("UI and API article content comparison passed for URL: " + uiUrl);
    }
}
