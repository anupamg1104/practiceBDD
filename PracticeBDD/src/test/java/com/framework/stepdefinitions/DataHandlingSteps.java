package com.framework.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.framework.driver.DriverFactory;
import com.framework.utils.APIUtils;
import com.framework.utils.CSVUtils;
import com.framework.utils.DBUtils;
import com.framework.utils.SEOValidator;

import java.util.List;

public class DataHandlingSteps {

    WebDriver driver = DriverFactory.getDriver();
    List<String> failedUrls;

    @Given("the browser is launched and navigated to the base URL")
    public void launchBrowser() {
        driver.get(System.getProperty("base.url"));
    }

    @When("user opens the page with URL {string}")
    public void openPage(String url) {
        driver.get(url);
    }

    @Then("the page should have valid SEO tags like title and meta description")
    public void validateSEOTags() {
        Assert.assertTrue(SEOValidator.isTitlePresent(driver));
        Assert.assertTrue(SEOValidator.isMetaDescriptionPresent(driver));
    }

    @Then("status code of the page should be 200")
    public void validateStatusCode() {
        String url = driver.getCurrentUrl();
        int code = APIUtils.getStatusCode(url);
        Assert.assertEquals(code, 200);
    }

    @Given("user fetches URLs from the database")
    public void fetchURLsFromDB() {
        DBUtils.connectToDB();
    }

    @When("user opens each URL")
    public void openEachURL() {
        failedUrls = DBUtils.validateAllUrls(driver);
    }

    @Then("the title and canonical should be present")
    public void validateSEOForAll() {
        // This happens in DBUtils.validateAllUrls()
    }

    @And("invalid URLs should be written to CSV")
    public void writeFailedURLsToCSV() {
        CSVUtils.writeToCsv(failedUrls);
    }

    @Given("user fetches article list from API")
    public void fetchArticleListFromAPI() {
        APIUtils.fetchArticles();
    }

    @When("user navigates to each article on UI")
    public void navigateToArticleUI() {
        APIUtils.openArticlesOnUI(driver);
    }

    @Then("article headline from API should match with UI")
    public void validateHeadlineConsistency() {
        APIUtils.compareHeadlines();
    }

    @Given("user loads URLs from a CSV file")
    public void loadURLsFromCSV() {
        CSVUtils.loadUrls();
    }

    @Then("the response code should be 200")
    public void validateResponseFromCSV() {
        CSVUtils.validateUrlsFromCsv();
    }

    @And("log any URL failing validation to CSV")
    public void logFailingUrlsToCSV() {
        CSVUtils.logFailures();
    }
}
