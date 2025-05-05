package com.framework.constants;

public class Constants {

    public static final String BASE_URL = "https://example.com";
    public static final String BROWSER = "chrome";
    public static final int TIMEOUT = 30;

    public static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    public static final String DB_USER = "your_username";
    public static final String DB_PASSWORD = "your_password";

    public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
    public static final String CSV_REPORT_PATH = System.getProperty("user.dir") + "/test-output/FailedURLs.csv";

    public static final String API_BASE_URL = "https://api.example.com/";

    private Constants() {
        // Prevent instantiation
    }
}
