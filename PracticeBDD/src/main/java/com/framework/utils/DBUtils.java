package com.framework.utils;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtils {
	private static final String CONFIG_FILE = "src/main/resources/config/application.properties";

    private static final String DB_URL = ConfigReader.getProperty("db.url");
    private static final String USERNAME = ConfigReader.getProperty("db.username");
    private static final String PASSWORD = ConfigReader.getProperty("db.password");

    private static List<String> urlsFromDB = new ArrayList<>();

    public static void connectToDB() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT url FROM url_table")) {

            urlsFromDB.clear();
            while (resultSet.next()) {
                urlsFromDB.add(resultSet.getString("url"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> validateAllUrls(WebDriver driver) {
        List<String> failedUrls = new ArrayList<>();
        for (String url : urlsFromDB) {
            try {
                driver.get(url);
                Thread.sleep(1000); // wait for loading
                String title = driver.getTitle();
                if (title == null || title.isEmpty()) {
                    failedUrls.add(url);
                }
            } catch (Exception e) {
                failedUrls.add(url);
            }
        }
        return failedUrls;
    }
    
    public static List<String> fetchArticleURLs() {
        List<String> urls = new ArrayList<>();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.load(input);
            String dbUrl = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");

            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT url FROM articles WHERE active = 1");

            while (rs.next()) {
                urls.add(rs.getString("url"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urls;
    }
}
