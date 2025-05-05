package com.framework.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    private static String url = "jdbc:mysql://localhost:3306/your_database";
    private static String username = "root";
    private static String password = "your_password";

    public static List<String> getUrlsFromDatabase() {
        List<String> urlList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT url FROM urls_table");

            while (rs.next()) {
                urlList.add(rs.getString("url"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlList;
    }
}
