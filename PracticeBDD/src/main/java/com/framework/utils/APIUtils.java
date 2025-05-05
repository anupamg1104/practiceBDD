package com.framework.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIUtils {


	private static List<String> apiArticleUrls = new ArrayList<>();
	private static List<String> apiArticleHeadlines = new ArrayList<>();
	private static List<String> uiArticleHeadlines = new ArrayList<>();

	public static int getStatusCode(String url) {
		try {
			Response response = RestAssured.get(url);
			return response.getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static String getResponseBody(String url) {
		try {
			Response response = RestAssured.get(url);
			return response.getBody().asString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static Response getApiResponse(String endpoint) {
		return RestAssured
				.given()
				.relaxedHTTPSValidation()
				.when()
				.get(endpoint)
				.then()
				.extract()
				.response();
	}

	public static boolean validateStatusCode(Response response, int expectedCode) {
		return response.getStatusCode() == expectedCode;
	}

	public static String getJsonValue(Response response, String jsonPath) {
		return response.jsonPath().getString(jsonPath);
	}


	/**
	 * Sends a GET request to the given API endpoint and returns the response body as a string.
	 *
	 * @param endpoint The API endpoint URL
	 * @return The response body as a string
	 */
	public static String getResponseBodyAsString(String endpoint) {
		try {
			Response response = RestAssured.get(endpoint);
			if (response.getStatusCode() == 200) {
				return response.getBody().asString();
			} else {
				System.err.println("Request failed with status code: " + response.getStatusCode());
				return null;
			}
		} catch (Exception e) {
			System.err.println("Exception while making GET request: " + e.getMessage());
			return null;
		}
	}
	
	
	 /**
     * Fetches articles from the API and stores the URLs and headlines.
     */
    public static void fetchArticles() {
        String apiEndpoint = ConfigReader.getProperty("api.baseurl") + "/articles";
        Response response = RestAssured.get(apiEndpoint);
        List<String> urls = response.jsonPath().getList("articles.url");
        List<String> headlines = response.jsonPath().getList("articles.headline");

        apiArticleUrls.clear();
        apiArticleHeadlines.clear();

        if (urls != null && headlines != null && urls.size() == headlines.size()) {
            apiArticleUrls.addAll(urls);
            apiArticleHeadlines.addAll(headlines);
        } else {
            throw new RuntimeException("API returned inconsistent or null data");
        }
    }

    /**
     * Opens each article URL on the UI and captures the headline.
     */
    public static void openArticlesOnUI(WebDriver driver) {
        uiArticleHeadlines.clear();

        for (String url : apiArticleUrls) {
            try {
                driver.navigate().to(url);
                String headline = driver.findElement(By.cssSelector("h1, h2, .article-title")).getText().trim();
                uiArticleHeadlines.add(headline);
            } catch (Exception e) {
                System.err.println("Error while opening article or extracting headline: " + url);
                uiArticleHeadlines.add("ERROR");
            }
        }
    }

    /**
     * Compares API headlines with UI headlines.
     */
    public static void compareHeadlines() {
        for (int i = 0; i < apiArticleHeadlines.size(); i++) {
            String apiHeadline = apiArticleHeadlines.get(i);
            String uiHeadline = i < uiArticleHeadlines.size() ? uiArticleHeadlines.get(i) : "MISSING";

            if (!apiHeadline.equalsIgnoreCase(uiHeadline)) {
                System.err.println("Mismatch found at index " + i);
                System.err.println("API Headline: " + apiHeadline);
                System.err.println("UI Headline: " + uiHeadline);
                throw new AssertionError("Headline mismatch for article at index " + i);
            }
        }
    }
}
