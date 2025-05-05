package com.framework.stepdefinitions;

import org.testng.Assert;

import com.framework.utils.APIUtils;
import com.framework.utils.XMLUtils;

import io.cucumber.java.en.Then;

public class XmlComparisonStepDefinition {

    @Then("I compare the article details between API endpoint {string} and XML feed {string}")
    public void iCompareApiAndXml(String apiUrl, String xmlUrl) {
        String apiResponse = APIUtils.getResponseBodyAsString(apiUrl);
        String xmlResponse = XMLUtils.getXmlContentAsString(xmlUrl);

        Assert.assertTrue(apiResponse.contains(xmlResponse) || xmlResponse.contains(apiResponse), "Mismatch between API and XML content");
        System.out.println("API and XML feed comparison passed for API: " + apiUrl);
    }
}
