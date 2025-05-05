package com.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class SEOValidator {

    public static Map<String, String> validateSEOElements(WebDriver driver) {
        Map<String, String> seoData = new HashMap<>();

        try {
            String title = driver.getTitle();
            String metaDescription = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
            String canonical = driver.findElement(By.xpath("//link[@rel='canonical']")).getAttribute("href");

            seoData.put("title", title);
            seoData.put("metaDescription", metaDescription);
            seoData.put("canonical", canonical);

        } catch (Exception e) {
            seoData.put("error", "Missing SEO element: " + e.getMessage());
        }

        return seoData;
    }
    
    

    /**
     * Checks if the <title> tag is present and not empty.
     *
     * @param driver The WebDriver instance
     * @return true if title is present and not empty, false otherwise
     */
    public static boolean isTitlePresent(WebDriver driver) {
        try {
            String title = driver.getTitle();
            return title != null && !title.trim().isEmpty();
        } catch (Exception e) {
            System.err.println("Error while checking <title>: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the <meta name="description"> tag is present and not empty.
     *
     * @param driver The WebDriver instance
     * @return true if meta description is present and not empty, false otherwise
     */
    public static boolean isMetaDescriptionPresent(WebDriver driver) {
        try {
            WebElement metaDescription = driver.findElement(By.xpath("//meta[@name='description']"));
            String content = metaDescription.getAttribute("content");
            return content != null && !content.trim().isEmpty();
        } catch (Exception e) {
            System.err.println("Error while checking <meta name=\"description\">: " + e.getMessage());
            return false;
        }
    }
}
