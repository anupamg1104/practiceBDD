package com.framework.stepdefinitions;


import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.framework.utils.APIUtils;
import com.framework.utils.CSVUtils;
import com.framework.utils.DBUtils;
import com.framework.utils.LoggerUtility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataDrivenSteps {
	Logger logger = LoggerUtility.getLogger(DataDrivenSteps.class);
	List<String> urls;
	List<String> failedUrls = new ArrayList<>();


	@Given("User is on the login page")
	public void user_is_on_login_page() {
		logger.info("Navigating to login page");
	}

	@When("User enters username {string} and password {string}")
	public void user_enters_credentials(String username, String password) {
		logger.info("Entered username: " + username + " and password: " + password);
	}

	@Then("Login should be {string}")
	public void login_should_be(String expectedStatus) {
		logger.info("Login expected result: " + expectedStatus);
		Assert.assertTrue(true); // Replace with actual validation
	}

	@Given("User loads URLs from CSV file")
	public void user_loads_urls_from_csv() {
		urls = CSVUtils.readURLs("src/main/resources/testdata/urls.csv");
		urls.forEach(url -> logger.info("Read from CSV: " + url));
	}

	@Given("User connects to database and fetches article URLs")
	public void user_fetches_urls_from_db() {
		List<String> dbUrls = DBUtils.fetchArticleURLs();
		dbUrls.forEach(url -> logger.info("Fetched from DB: " + url));
	}

	@Then("User validates status code is 200")
	public void user_validates_status_code_is() {
		SoftAssert softAssert = new SoftAssert();

		for (String url : urls) {
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				int responseCode = connection.getResponseCode();

				System.out.println("🔗 " + url + " => Status: " + responseCode);
				softAssert.assertEquals(responseCode, 201, "❌ URL failed: " + url);

			} catch (Exception e) {
				softAssert.fail("❌ Exception while validating URL: " + url + " => " + e.getMessage());
			}
		}

//		CSVUtils.writeFailedUrls(failedUrls, "target/failed_urls.csv");
		// Collate and report all failures
		softAssert.assertAll();
		CSVUtils.writeFailedUrls(failedUrls, "target/failed_urls.csv");
	}
}

